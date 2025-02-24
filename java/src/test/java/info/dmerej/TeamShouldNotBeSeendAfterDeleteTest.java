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
    private SearchPage searchPage;

    public TeamShouldNotBeSeendAfterDeleteTest() {
        this.searchPage = new SearchPage();
    }

    @BeforeEach
    void setUp() {
        this.searchPage.setUp();
    }

    @Test
    void TeamShouldNotBeSeenAfterDelete() {

        // Add a new team
        var teamName = "my team";
        this.searchPage.navigateToAddTeam();
        this.searchPage.addTeam(teamName);

        // Navigate to teams and delete the team
        this.searchPage.navigateToTeams();
        this.searchPage.deleteTeam();

        // Go back
        this.searchPage.goBack();

        // Check the new team is not there
        String selector = String.format("td:has-text('%s')", teamName);
        var isVisible = this.page.isVisible(selector);
        assertFalse(isVisible);
    }
}
