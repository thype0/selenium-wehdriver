package test.tc07;

/*

Test Steps:
1. Go to http://live.techpanda.org/
2. Click on My Account link
3. Login in application using previously created credential
4. Click on 'My Orders'
5. Click on 'View Order'
6. Click on 'Print Order' link
Good luck!

*/

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.tc06.LoginInput;

import java.time.Duration;
import java.util.List;

@Test
public class TC07 {

    public static void testTC07() {

        WebDriver driver = DriverFactory.getDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();

            //3. Login in application using previously created credential
            new LoginInput(driver).inputEmail("nguyenthienbao86011@gmail.com")
                    .inputPassword("1234567")
                    .submit();

            //4. Click on 'My Orders'
            driver.findElement(By.cssSelector(".block-content > ul > li:nth-child(4) > a")).click();

            //5. Click on 'View Order'
            driver.findElement(By.cssSelector("#my-orders-table > tbody > tr.first.odd * .nobr > a:nth-child(1)")).click();

            //6. Click on 'Print Order' link
            driver.findElement(By.cssSelector(".page-title > a.link-print")).click();

            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
