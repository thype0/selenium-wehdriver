package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumElement;

import java.time.Duration;

public class CartCheckoutPage {

    private final SeleniumElement element;
    private final WebDriver driver;

    public CartCheckoutPage(WebDriver driver) {
        this.element = new SeleniumElement(driver);
        this.driver = driver;
    }

    //Billing
    public void selectNewBillingAddress() {
        element.selectOptionText("#billing-address-select", "New Address");
    }

    public CartCheckoutPage inputBillingCompany(String company) {
        element.enterText("#billing\\:company", company);
        return this;
    }

    public CartCheckoutPage inputBillingAddress1(String add) {
        element.enterText("#billing\\:street1", add);
        return this;
    }

    public CartCheckoutPage inputBillingAddress2(String add) {
        element.enterText("#billing\\:street2", add);
        return this;
    }

    public CartCheckoutPage inputBillingCity(String add) {
        element.enterText("#billing\\:city", add);
        return this;
    }

    public CartCheckoutPage inputBillingState(String input) {
        element.selectOption("#billing\\:region_id", "5");
        return this;
    }

    public CartCheckoutPage inputBillingZip(String input) {
        element.enterText("#billing\\:postcode", "7500");
        return this;
    }

    public CartCheckoutPage inputBillingTelephone(String input) {
        element.enterText("#billing\\:telephone", input);
        return this;
    }

    public CartCheckoutPage shipToDifferentAddress() {
        element.selectRadio("#billing\\:use_for_shipping_no");
        return this;
    }

    public void continueBillingProcess() {
        element.clickElement("#billing-buttons-container > button");
    }

    //Shipping

    public CartCheckoutPage selectNewShippingAddress() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.cssSelector("#shipping-address-select"))));

        element.selectOptionText("#shipping-address-select", "New Address");
        return this;
    }

    public CartCheckoutPage inputShippingCompany(String company) {
        element.enterText("#shipping\\:company", company);
        return this;
    }

    public CartCheckoutPage inputShippingAddress1(String add) {
        element.enterText("#shipping\\:street1", add);
        return this;
    }

    public CartCheckoutPage inputShippingAddress2(String add) {
        element.enterText("#shipping\\:street2", add);
        return this;
    }

    public CartCheckoutPage inputShippingCity(String add) {
        element.enterText("#shipping\\:city", add);
        return this;
    }

    public CartCheckoutPage inputShippingState(String input) {
        element.selectOption("#shipping\\:region_id", "5");
        return this;
    }

    public CartCheckoutPage inputShippingZip(String input) {
        element.enterText("#shipping\\:postcode", "7500");
        return this;
    }

    public CartCheckoutPage inputShippingTelephone(String input) {
        element.enterText("#shipping\\:telephone", input);
        return this;
    }

    public void continueShippingProcess() throws InterruptedException {
        Thread.sleep(3000);
        element.clickElement("#shipping-buttons-container > button");
    }

    public void continueShippingMethod() throws InterruptedException {
        Thread.sleep(5000);
        element.clickElement("#shipping-method-buttons-container > button");
    }

    public void continueMoneyOrder() throws InterruptedException {
        Thread.sleep(5000);
        element.selectRadio("input#p_method_checkmo");
        element.clickElement("#payment-buttons-container > button");
    }

    public void continuePlaceOrder() throws InterruptedException {
        Thread.sleep(5000);
        element.clickElement("#review-buttons-container > button");
    }

}
