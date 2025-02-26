package info.dmerej;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class PostalCodeShouldNotBeNegativeTest {

    private AddEmployeePage addEmployeePage;

    public PostalCodeShouldNotBeNegativeTest() { this.addEmployeePage = new AddEmployeePage(); }
    @BeforeEach
    void setUp() {
        this.addEmployeePage.setUp();
    }

    @Test
    void PostalCodeShouldNotBeNegative() {
        this.addEmployeePage.addEmployee("TestName", "test.name@gmail.com", "34 rue poirreau", "Paris", "-1", "2025-02-27", "dev");
        assertThat(this.page.locator("tbody")).isEmpty();
    }
}
