package info.dmerej;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class PostalCodeShouldNotBeNegativeTest {
    private Page page;
    @BeforeEach
    void setUp() {
        var playwright = Playwright.create();
        var launchOptions = new BrowserType.LaunchOptions().setHeadless(false)
                .setSlowMo(1000); // Remove this when you're done debugging
        var browser = playwright.chromium().launch(launchOptions);

        // Set base URL for the new context
        var contextOptions = new Browser.NewContextOptions();
        // TODO: fix the URL
        contextOptions.setBaseURL("https://a.lsi2.hr.dmerej.info");
        var context = browser.newContext(contextOptions);

        this.page = context.newPage();

        // Reset database
        page.navigate("/reset_db");
        var proceedButton = page.locator("button:has-text('proceed')");
        proceedButton.click();
        page.navigate("/");
    }

    @Test
    void PostalCodeShouldNotBeNegative() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add new employee")).click();
        page.getByPlaceholder("Name").fill("TestName");
        page.getByPlaceholder("Email").fill("test.name@gmail.com");
        page.locator("#id_address_line1").fill("34 rue poirreau");
        page.getByPlaceholder("City").fill("Paris");
        page.getByPlaceholder("Zip code").fill("-1");
        page.getByPlaceholder("Hiring date").fill("2025-02-27");
        page.getByPlaceholder("Job title").fill("dev");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
        assertThat(page.locator("tbody")).isEmpty();
    }
}
