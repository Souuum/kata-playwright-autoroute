package info.dmerej;

import info.dmerej.page.AddTeamPage;
import info.dmerej.page.HomePage;
import info.dmerej.page.TeamPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TeamShouldNotBeSeendAfterDeleteTest {
    private TeamPage teamPage;
    private AddTeamPage addTeamPage;
    private HomePage homePage;
    public TeamShouldNotBeSeendAfterDeleteTest() {
        this.teamPage = new TeamPage();
        this.addTeamPage = new AddTeamPage();
        this.homePage = new HomePage();
    }

    @BeforeEach
    void setUp() {
        this.teamPage.setUp("https://a.lsi2.hr.dmerej.info/teams");
        this.addTeamPage.setUp("https://a.lsi2.hr.dmerej.info/add_team");
        this.homePage.setUp("https://a.lsi2.hr.dmerej.info");
        this.homePage.resetDB();
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
