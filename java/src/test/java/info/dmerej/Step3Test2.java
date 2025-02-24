package info.dmerej;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Step3Test2 {
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
    void TeamShouldNotBeSeenAfterDelete() {

        // Add a new team
        this.page.navigate("/add_team");
        var nameInput = this.page.locator("input[name=\"name\"]");
        var teamName = "my team";
        nameInput.fill(teamName);
        this.page.click("text='Add'");

        // Check that the team has been added
        this.page.navigate("/teams");

        this.page.click("text='Delete'");
        this.page.click("text='Proceed'");

        // Go back
        this.page.goBack();

        // Check the new team is not there
        String selector = String.format("td:has-text('%s')", teamName);
        var isVisible = this.page.isVisible(selector);
        assertFalse(isVisible);
    }
}
