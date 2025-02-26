package info.dmerej.page;

import com.microsoft.playwright.Page;

public class HomePage extends PageTest {

    public HomePage(Page page) {
        super(page);
    }

    public void resetDB() {
        this.getPage().navigate("/reset_db");
        var proceedButton = this.getPage().locator("button:has-text('proceed')");
        proceedButton.click();
        this.getPage().navigate("/");
    }

}
