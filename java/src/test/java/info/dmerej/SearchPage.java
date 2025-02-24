package info.dmerej;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SearchPage {
    private Page page;

    public SearchPage() {
        this.page = page;
    }

    public void setUp() {
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

        this.page.navigate("/reset_db");
        var proceedButton = page.locator("button:has-text('proceed')");
        proceedButton.click();
        this.page.navigate("/");
    }

    public void navigate(String url) {
        this.page.navigate(url);
    }

    public void navigateToTeams() {
        navigate("/teams");
    }

    public void navigateToAddTeam() {
        navigate("/add_team");
    }

    public void navigateToAddEmployee() {
        navigate("/add_employee");
    }

    public void goBack() {
        this.page.goBack();
    }

    public void addTeam(String teamName) {
        var nameInput = this.page.locator("input[name=\"name\"]");
        nameInput.fill(teamName);
        this.page.click("text='Add'");
    }

    public void deleteTeam() {
        this.page.click("text='Delete'");
        this.page.click("text='Proceed'");
    }
}
