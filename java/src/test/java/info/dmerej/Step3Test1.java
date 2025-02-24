package info.dmerej;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;
public class Step3Test1 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://a.lsi2.hr.dmerej.info/");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reset database")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add new employee")).click();
            page.getByPlaceholder("Name").click();
            page.getByPlaceholder("Name").fill("TestName");
            page.getByPlaceholder("Email").click();
            page.getByPlaceholder("Email").fill("test.name@gmail.com");
            page.locator("#id_address_line1").click();
            page.locator("#id_address_line1").fill("34 rue poirreau");
            page.getByPlaceholder("City").click();
            page.getByPlaceholder("City").fill("Paris");
            page.getByPlaceholder("Zip code").click();
            page.getByPlaceholder("Zip code").fill("-1");
            page.getByPlaceholder("Hiring date").fill("2025-02-27");
            page.getByPlaceholder("Job title").fill("dev");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
            assertThat(page.locator("tbody")).isEmpty();
        }
    }
}
