package info.dmerej.page;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PageTest {
    private Page page;

    public PageTest(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setUp(String url) {
        try{
            var playwright = Playwright.create();
            var launchOptions = new BrowserType.LaunchOptions().setHeadless(false)
                    .setSlowMo(1000); // Remove this when you're done debugging
            var browser = playwright.chromium().launch(launchOptions);
            var contextOptions = new Browser.NewContextOptions();
            contextOptions.setBaseURL(url);
            var context = browser.newContext(contextOptions);

            this.page = context.newPage();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void goBack(){
        this.page.goBack();
    }

    public boolean isVisible(String selector){
        return this.page.isVisible(selector);
    }


}
