package test.tc05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterInput {

    private final WebDriver driver;

    public RegisterInput(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterInput inputFirstName(String name) {
        sendElementKeys("firstname", name);
        return this;
    }

    public RegisterInput inputLastName(String name) {
        sendElementKeys("lastname", name);
        return this;
    }

    public RegisterInput inputEmail(String email) {
        sendElementKeys("email", email);
        return this;
    }

    public RegisterInput inputPassword(String password) {
        sendElementKeys("password", password);
        return this;
    }

    public RegisterInput inputConfirmation(String confirm) {
        sendElementKeys("confirmation", confirm);
        return this;
    }

    public WebElement findElement(String id) {
        return driver.findElement(By.cssSelector("input[name=" + id + "]"));
    }

    public void sendElementKeys(String id, String input) {
        findElement(id).sendKeys(input);
    }
}
