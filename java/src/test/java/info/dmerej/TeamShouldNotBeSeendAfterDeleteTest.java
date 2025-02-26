package info.dmerej;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import info.dmerej.SearchPage;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TeamShouldNotBeSeendAfterDeleteTest {
    private TeamPage teamPage;
    private AddTeamPage addTeamPage;
    public TeamShouldNotBeSeendAfterDeleteTest() {
        this.teamPage = new TeamPage();
        this.addTeamPage = new AddTeamPage();
    }

    @BeforeEach
    void setUp() {
        this.teamPage.setUp();
        this.addTeamPage.setUp();
    }

    @Test
    void TeamShouldNotBeSeenAfterDelete() {

        // Add a new team
        var teamName = "my team";


        // Navigate to teams and delete the team
        this.addTeamPage.addTeam(teamName);
        this.teamPage.deleteTeam(teamName);
        // Go back
        this.teamPage.goBack();

        // Check the new team is not there
        String selector = String.format("td:has-text('%s')", teamName);
        var isVisible = this.teamPage.isVisible(selector);
        assertFalse(isVisible);
    }
}
