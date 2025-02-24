package info.dmerej;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class Step2Test {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://a.lsi2.hr.dmerej.info/");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reset database")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create new team")).click();
            page.getByPlaceholder("Name").click();
            page.getByPlaceholder("Name").fill("TestTeam");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add new employee")).click();
            page.getByPlaceholder("Name").click();
            page.getByPlaceholder("Name").fill("Test");
            page.getByPlaceholder("Name").press("Tab");
            page.getByPlaceholder("Email").fill("Test@test.test");
            page.getByPlaceholder("Email").press("Tab");
            page.locator("#id_address_line1").fill("Test");
            page.locator("#id_address_line1").press("Tab");
            page.locator("#id_address_line2").press("Tab");
            page.getByPlaceholder("City").fill("Test");
            page.getByPlaceholder("City").press("Tab");
            page.getByPlaceholder("Zip code").fill("1000");
            page.getByPlaceholder("Hiring date").fill("2025-02-24");                page.getByPlaceholder("Job title").click();
            page.getByPlaceholder("Job title").fill("Test");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add to team")).click();
            page.getByLabel("Team").click();
            page.keyboard().type("TestTeam");
            page.getByLabel("Team").press("Enter");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("List teams")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Delete")).nth(0).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
            assertThat(page.getByText("Home Teams No teams yet")).isVisible();
        }
    }
}