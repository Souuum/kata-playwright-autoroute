package info.dmerej.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class AddEmployeePage extends PageTest {

    public AddEmployeePage(Page page) {
        super(page);
    }

    public void inputName(String employeeName) {
        this.getPage().getByPlaceholder("Name").fill(employeeName);
    }

    public void inputEmail(String employeeEmail) {
        this.getPage().getByPlaceholder("Email").fill(employeeEmail);
    }

    public void inputAddressLine1(String employeeAddressLine1) {
        this.getPage().locator("#id_address_line1").fill(employeeAddressLine1);
    }

    public void inputCity(String employeeCity) {
        this.getPage().getByPlaceholder("City").fill(employeeCity);
    }

    public void inputZipCode(String employeeZipCode) {
        this.getPage().getByPlaceholder("Zip code").fill(employeeZipCode);
    }

    public void inputHiringDate(String employeeHiringDate) {
        this.getPage().getByPlaceholder("Hiring date").fill(employeeHiringDate);
    }

    public void inputJobTitle(String employeeJobTitle) {
        this.getPage().getByPlaceholder("Job title").fill(employeeJobTitle);;
    }

    public void addEmployee(String employeeName, String employeeEmail, String employeeAddressLine1, String employeeCity, String employeeZipCode, String employeeHiringDate, String employeeJobTitle) {
        this.inputName(employeeName);
        this.inputEmail(employeeEmail);
        this.inputAddressLine1(employeeAddressLine1);
        this.inputCity(employeeCity);
        this.inputZipCode(employeeZipCode);
        this.inputHiringDate(employeeHiringDate);
        this.inputJobTitle(employeeJobTitle);
        this.getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
    }

    public Locator selectTable(){
        return this.getPage().locator("tbody");
    }
}
