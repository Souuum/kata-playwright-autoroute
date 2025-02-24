package info.dmerej;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserShouldBeAbleToModifyDateOfContractTest {
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
    void UserShouldBeAbleToModifyDateOfContract(){
        this.page.navigate("/add_employee");
        this.page.fill("input[name=\"name\"]", "Test");
        this.page.fill("input[name=\"email\"]", "Test@test.test");
        this.page.fill("#id_address_line1", "Test");
        this.page.fill("#id_address_line2", "Test");
        this.page.fill("input[name=\"city\"]", "Test");
        this.page.fill("input[name=\"zip_code\"]", "1000");
        this.page.fill("input[name=\"hiring_date\"]", "2025-02-24");
        this.page.fill("input[name=\"job_title\"]", "Test");
        this.page.click("text='Add'");

        this.page.click("text='Edit'");
        this.page.click("text='Update contract'");

        // Check if input is editable
        var inputDate = this.page.locator("input[name=\"hiring_date\"]");
        assertTrue(inputDate.isEditable());
    }
}
