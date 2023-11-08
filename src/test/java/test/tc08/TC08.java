package test.tc08;

/*

 *  Test Data = QTY = 10

Test Steps:

1. Go to http://live.techpanda.org/
2. Click on my account link
3. Login in application using previously created credential
4. Click on 'REORDER' link, change QTY & click Update
5. Verify Grand Total is changed
6. Complete Billing & Shipping Information
7. Verify order is generated and note the order number

Expected outcomes:
1) Grand Total is Changed
2) Order number is generated

*/

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartCheckoutPage;
import test.tc06.LoginInput;

@Test
public class TC08 {

    public static void testTC08() {

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

            //3.5. Click on 'My Orders'
            driver.findElement(By.cssSelector(".block-content > ul > li:nth-child(4) > a")).click();

            //4. Click on 'REORDER' link, change QTY & click Update
            driver.findElement(By.cssSelector("#my-orders-table > tbody > tr.first.odd * .nobr > .link-reorder")).click();

            //4.1: Grab the grand total before update
            String initPrice = driver.findElement(By.cssSelector("#shopping-cart-totals-table * .price")).getText();

            WebElement orderRow = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]"));
            WebElement inputQty = orderRow.findElement(By.cssSelector("input"));
            inputQty.sendKeys(Keys.CONTROL + "a");
            inputQty.sendKeys(Keys.DELETE);
            inputQty.sendKeys("2");

            //4.2: Click the Update button
            orderRow.findElement(By.cssSelector(".btn-update")).click();

            //5. Verify Grand Total is changed
            String updatedPrice = driver.findElement(By.cssSelector("#shopping-cart-totals-table * .price")).getText();
            Assert.assertFalse(updatedPrice.equalsIgnoreCase(initPrice));

            //6. Complete Billing & Shipping Information
            //6.1. Redirect to Address input page
            driver.findElement(By.cssSelector("button.btn-proceed-checkout")).click();

            CartCheckoutPage ccp = new CartCheckoutPage(driver);
            ccp.selectNewBillingAddress();
            ccp.inputBillingCompany("Bao Tu")
                    .inputBillingAddress1("02, 138")
                    .inputBillingAddress2("02, 139 Street")
                    .inputBillingCity("Texas")
                    .inputBillingState("5")
                    .inputBillingZip("7500")
                    .inputBillingTelephone("0888888888")
                    .shipToDifferentAddress()
                    .continueBillingProcess();

            //6.2. Enter Shipping Information, and click Continue
            ccp.selectNewShippingAddress()
                    .inputShippingCompany("Bao Tu")
                    .inputShippingAddress1("01, 138")
                    .inputShippingAddress2("01, 139 Street")
                    .inputShippingCity("Texas")
                    .inputShippingState("5")
                    .inputShippingZip("7501")
                    .inputShippingTelephone("0555555555")
                    .continueShippingProcess();

            //6.3. In Shipping Method, Click Continue
            ccp.continueShippingMethod();

            //6.4. In Payment Information select 'Check/Money Order' radio button. Click Continue
            ccp.continueMoneyOrder();

            //6.5. Click 'PLACE ORDER' button
            ccp.continuePlaceOrder();

            //6.6: Redirection to success page
            Thread.sleep(3000);
            String text = driver.findElement(By.cssSelector("h2.sub-title")).getText();
            Assert.assertTrue(text.equalsIgnoreCase("Thank you for your purchase!"));

            //7. Verify order is generated and note the order number
            String orderID = driver.findElement(By.cssSelector("body > div > div > div.main-container.col1-layout > div > div > p:nth-child(3) > a")).getText();
            Assert.assertNotNull(orderID);

            System.out.println("Generated ID: #" + orderID);

            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
