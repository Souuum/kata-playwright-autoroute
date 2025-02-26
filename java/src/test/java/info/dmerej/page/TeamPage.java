package info.dmerej.page;

import com.microsoft.playwright.Page;

public class TeamPage extends PageTest {

    public TeamPage(Page page) {
        super(page);
    }

    public void deleteTeam(String teamName) {
        this.getPage().click("text='Delete'");
        this.getPage().click("text='Proceed'");
    }
}
