package test.tc06;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartCheckoutPage;

import java.util.List;

/* Verify user is able to purchase product using registered email id (USE CHROME BROWSER)

Test Steps:
1. Go to http://live.techpanda.org/
2. Click on my account link
3. Login in application using previously created credential
4. Click on MY WISHLIST link
5. In next page, Click ADD TO CART link
6. Enter general shipping country, state/province and zip for the shipping cost estimate
7. Click Estimate
8. Verify Shipping cost generated
9. Select Shipping Cost, Update Total
10. Verify shipping cost is added to total
11. Click "Proceed to Checkout"
12a. Enter Billing Information, and click Continue
12b. Enter Shipping Information, and click Continue
13. In Shipping Method, Click Continue
14. In Payment Information select 'Check/Money Order' radio button. Click Continue
15. Click 'PLACE ORDER' button
16. Verify Oder is generated. Note the order number
*/

@Test
public class TC06 {

    public static void testTC05() {

        WebDriver driver = DriverFactory.getDriver();

        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();

            //3. Login in application using previously created credential
            new LoginInput(driver).inputEmail("nguyenthienbao86011@gmail.com")
                    .inputPassword("1234567")
                    .submit();

            //4. Click on MY WISHLIST link
            driver.findElement(By.cssSelector(".block-content > ul > li:nth-child(8) > a")).click();

            //5. In next page, Click ADD TO CART link
            driver.findElement(By.cssSelector("#wishlist-table > tbody > .first * .btn-cart")).click();

            //6. Enter general shipping country, state/province and zip for the shipping cost estimate
            CartPage cartPage = new CartPage(driver)
                    .inputCountry("US")
                    .inputState("26")
                    .inputZip("74500");

            //7. Click Estimate
            driver.findElement(By.cssSelector("#shipping-zip-form > div > button")).click();

            //8. Verify Shipping cost generated
            Assert.assertTrue(cartPage.verifyPriceGenerated());

            //9. Select Shipping Cost, Update Total
            cartPage.selectFeeAndUpdate();

            //10. Verify shipping cost is added to total
            Assert.assertEquals(cartPage.getFeeGenerated(), "$5.00");

            //11. Click "Proceed to Checkout"
            cartPage.proceedToCheckout();

            //12a. Enter Billing Information, and click Continue
            CartCheckoutPage ccp = new CartCheckoutPage(driver);

            ccp.selectNewBillingAddress();
            ccp.inputBillingCompany("Wicky")
                    .inputBillingAddress1("02, 138")
                    .inputBillingAddress2("02, 139 Street")
                    .inputBillingCity("Texas")
                    .inputBillingState("5")
                    .inputBillingZip("7500")
                    .inputBillingTelephone("0888888888")
                    .shipToDifferentAddress()
                    .continueBillingProcess();

            //12b. Enter Shipping Information, and click Continue
            ccp.selectNewShippingAddress()
                    .inputShippingCompany("Wicky")
                    .inputShippingAddress1("01, 138")
                    .inputShippingAddress2("01, 139 Street")
                    .inputShippingCity("Texas")
                    .inputShippingState("5")
                    .inputShippingZip("7501")
                    .inputShippingTelephone("0555555555")
                    .continueShippingProcess();

            //13. In Shipping Method, Click Continue
            ccp.continueShippingMethod();

            //14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            ccp.continueMoneyOrder();

            //15. Click 'PLACE ORDER' button
            ccp.continuePlaceOrder();

            Thread.sleep(3000);
            //16. Verify Oder is generated. Note the order number
            String text = driver.findElement(By.cssSelector("h2.sub-title")).getText();
            Assert.assertTrue(text.equalsIgnoreCase("Thank you for your purchase!"));

            String orderID = driver.findElement(By.cssSelector("body > div > div > div.main-container.col1-layout > div > div > p:nth-child(3) > a")).getText();
            System.out.println(orderID);

            Thread.sleep(3000);
        } catch (Exception e) {
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
