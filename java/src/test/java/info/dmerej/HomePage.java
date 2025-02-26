package info.dmerej;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
public class HomePage {
    private Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void setUp() {
        try {
            var playwright = Playwright.create();
            var launchOptions = new BrowserType.LaunchOptions().setHeadless(false)
                    .setSlowMo(1000); // Remove this when you're done debugging
            var browser = playwright.chromium().launch(launchOptions);
            var contextOptions = new Browser.NewContextOptions();
            contextOptions.setBaseURL("https://a.lsi2.hr.dmerej.info");
            var context = browser.newContext(contextOptions);

            this.page = context.newPage();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void resetDB() {
        this.page.navigate("/reset_db");
        var proceedButton = page.locator("button:has-text('proceed')");
        proceedButton.click();
        this.page.navigate("/");
    }
    
}
