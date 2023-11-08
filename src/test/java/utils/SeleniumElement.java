package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SeleniumElement {

    private WebDriver driver;

    public SeleniumElement(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(String selector) {
        return driver.findElement(By.cssSelector(selector));
    }

    public List<WebElement> findElements(String selector) {
        return driver.findElements(By.cssSelector(selector));
    }

    public void enterText(String selector, String text) {
        WebElement element = findElement(selector);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public void selectOption(String selector, String value) {
        Select dropdown = new Select(findElement(selector));
        dropdown.selectByValue(value);
    }

    public void selectOptionText(String selector, String value) {
        Select dropdown = new Select(findElement(selector));
        dropdown.selectByVisibleText(value);
    }

    public void selectRadio(String selector) {
        findElement(selector).click();
    }

    public String findElementText(String selector) {
        return findElement(selector).getText();
    }

    public void clickElement(String selector) {
        findElement(selector).click();
    }

    public void hoverElement(String selector) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(selector)).perform();
    }

    public void screenshot(String path) {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(path);
        try {
            FileHandler.copy(screenshotFile, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}