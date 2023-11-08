package test.tc10;

/*
1. Go to http://live.techpanda.org/index.php/backendlogin
2. Login the credentials provided
3. Go to Sales-> Orders menu
4. Input OrderId and FromDate -> ToDate
5. Click Search button
6. Screenshot capture.
Login:
id = "user01"
pass = "guru99com"
*/

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SeleniumElement;

import java.time.Duration;
import java.util.List;

@Test
public class TC10 {

    public static void testTC10() {

        WebDriver driver = DriverFactory.getDriver();
        try {
            //1. Go to http://live.techpanda.org/index.php/backendlogin
            driver.get("http://live.techpanda.org/index.php/backendlogin/");

            SeleniumElement ele = new SeleniumElement(driver);

            //2. Login the credentials provided
            ele.enterText("input#username", "user01");
            ele.enterText("input#login", "guru99com");
            ele.clickElement(".form-button");

            //3. Go to Sales-> Orders menu
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#message-popup-window > div.message-popup-head > a")));
            element.click();

            ele.hoverElement("#nav > li:nth-child(1)");
            ele.clickElement("#nav > li:nth-child(1) > ul > li:nth-child(1) > a");

            //4. Input OrderId and FromDate -> ToDate
            ele.enterText("input#sales_order_grid_filter_real_order_id", "100021076");
            ele.enterText("input[name=created_at\\[from\\]", "11/6/2023");
            ele.enterText("input[name=created_at\\[to\\]", "11/7/2023");

            //5. Click Search button
            ele.clickElement("button[title=Search]");
            Thread.sleep(5000);

            //6. Screenshot capture.
            ele.screenshot("output.png");

            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
