package info.dmerej;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class AddEmployeePage {
    private Page page;

    public AddEmployeePage() {
        this.page = page;
    }

    public void setUp() {
        var playwright = Playwright.create();
        var launchOptions = new BrowserType.LaunchOptions().setHeadless(false)
                .setSlowMo(1000); // Remove this when you're done debugging
        var browser = playwright.chromium().launch(launchOptions);

        // Set base URL for the new context
        var contextOptions = new Browser.NewContextOptions();
        // TODO: fix the URL
        contextOptions.setBaseURL("https://a.lsi2.hr.dmerej.info");
        var context = browser.newContext(contextOptions);

        this.page = context.newPage();

        this.page.navigate("/reset_db");
        var proceedButton = page.locator("button:has-text('proceed')");
        proceedButton.click();
        this.page.navigate("/teams");
    }

    public void navigate(String url) {
        this.page.navigate(url);
    }

    public void goBack() {
        this.page.goBack();
    }

    public void inputName(String employeeName) {
        page.getByPlaceholder("Name").fill(employeeName);
    }

    public void inputEmail(String employeeEmail) {
        page.getByPlaceholder("Email").fill(employeeEmail);
    }

    public void inputAddressLine1(String employeeAddressLine1) {
        page.locator("#id_address_line1").fill(employeeAddressLine1);
    }

    public void inputCity(String employeeCity) {
        page.getByPlaceholder("City").fill(employeeCity);
    }

    public void inputZipCode(String employeeZipCode) {
        page.getByPlaceholder("Zip code").fill(employeeZipCode);
    }

    public void inputHiringDate(String employeeHiringDate) {
        page.getByPlaceholder("Hiring date").fill(employeeHiringDate);
    }

    public void inputJobTitle(String employeeJobTitle) {
        page.getByPlaceholder("Job title").fill(employeeJobTitle);;
    }

    public void addEmployee(String employeeName, String employeeEmail, String employeeAddressLine1, String employeeCity, String employeeZipCode, String employeeHiringDate, String employeeJobTitle) {
        this.inputName(employeeName);
        this.inputEmail(employeeEmail);
        this.inputAddressLine1(employeeAddressLine1);
        this.inputCity(employeeCity);
        this.inputZipCode(employeeZipCode);
        this.inputHiringDate(employeeHiringDate);
        this.inputJobTitle(employeeJobTitle);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
    }

    public Locator selectTable(){
        return this.page.locator("tbody");
    }
}
