package test.tc02;

/*

Test Steps:
1. Goto http://live.techpanda.org/
2. Click on �MOBILE� menu
3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
4. Click on Sony Xperia mobile
5. Read the Sony Xperia mobile from detail page.
6. Compare Product value in list and details page should be equal ($100).

*/

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Test
public class TC02 {

    public static void testTC02() {

        WebDriver driver = DriverFactory.getDriver();
        try {

            //1. Goto http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on �MOBILE� menu
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement mobileBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".level0"))));
            mobileBtn.click();

            List<WebElement> productInfoElements = driver.findElements(By.cssSelector(".products-grid > li"));

            WebElement productInfo = null;
            String titlePrice = null;

            //3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

            for (WebElement productInfoElement : productInfoElements) {
                WebElement productNameElement = productInfoElement.findElement(By.cssSelector(".product-name"));
                String productName = productNameElement.getText();
                if (productName.equalsIgnoreCase("Sony Xperia")) {
                    productInfo = productInfoElement;
                    titlePrice = productInfoElement.findElement(By.cssSelector("span.price")).getText();
                    break;
                }
            }

            Assert.assertNotNull(productInfo);
            Assert.assertNotNull(titlePrice);
            Assert.assertEquals(titlePrice, "$100.00");

            //4. Click on Sony Xperia mobile
            WebElement productImage = productInfo.findElement(By.cssSelector(".product-image"));
            productImage.click();

            //5. Read the Sony Xperia mobile from detail page.
            String actualTitlePrice = driver.findElement(By.cssSelector(".product-shop * .regular-price > .price")).getText();

            //6. Compare Product value in list and details page should be equal ($100).
            Assert.assertEquals(titlePrice, actualTitlePrice);

            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
