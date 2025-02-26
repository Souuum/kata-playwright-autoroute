package info.dmerej.page;

import com.microsoft.playwright.Page;

public class AddTeamPage extends PageTest {


    public AddTeamPage(Page page) {
        super(page);
    }

    public void addTeam(String teamName) {
        var nameInput = this.getPage().locator("input[name=\"name\"]");
        nameInput.fill(teamName);
        this.getPage().click("text='Add'");
    }
}
