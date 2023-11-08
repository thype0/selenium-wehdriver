package test.tc06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginInput {

    private final WebDriver driver;

    public LoginInput(WebDriver driver) {
        this.driver = driver;
    }

    public LoginInput inputEmail(String email) {
        sendElementKeys("email", email);
        return this;
    }

    public LoginInput inputPassword(String password) {
        sendElementKeys("pass", password);
        return this;
    }

    public void submit() {
        driver.findElement(By.cssSelector(".buttons-set > #send2")).click();
    }

    public WebElement findElement(String id) {
        return driver.findElement(By.cssSelector(".input-box > input[id=" + id + "]"));
    }

    public void sendElementKeys(String id, String input) {
        findElement(id).sendKeys(input);
    }
}
