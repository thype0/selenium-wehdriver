package test.tc09;

/*  Verify Discount Coupon works correctly

Test Case Description:

1. Go to http://live.techpanda.org/
2. Go to Mobile and add IPHONE to cart
3. Enter Coupon Code
4. Verify the discount generated

TestData:  Coupon Code: GURU50

Expected result:
1) Price is discounted by 5%

*/

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartCheckoutPage;
import test.tc06.LoginInput;

import java.time.Duration;
import java.util.List;

@Test
public class TC09 {

    public static void testTC09() {

        WebDriver driver = DriverFactory.getDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Go to Mobile and add IPHONE to cart
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement mobileBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".level0"))));
            mobileBtn.click();

            WebElement piIphone = findProductElement(driver, "IPhone");
            Assert.assertNotNull(piIphone);

            piIphone.findElement(By.cssSelector(".btn-cart")).click();

            //3. Enter Coupon Code (GURU50)
            DiscountPage discountPage = new DiscountPage(driver);
            discountPage.applyCoupon();

            //4. Verify the discount generated (discount by 5%)
            discountPage.verifyDiscount();

            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }

    public static WebElement findProductElement(WebDriver driver, String product) {
        List<WebElement> productInfoElements = driver.findElements(By.cssSelector(".products-grid > li"));

        for (WebElement productInfoElement : productInfoElements) {
            WebElement productNameElement = productInfoElement.findElement(By.cssSelector(".product-name"));
            String productName = productNameElement.getText();
            if (productName.equalsIgnoreCase(product)) {
                return productInfoElement;
            }
        }
        return null;
    }
}
