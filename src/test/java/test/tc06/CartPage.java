package test.tc06;

import org.openqa.selenium.WebDriver;
import utils.SeleniumElement;

public class CartPage {

    private final SeleniumElement element;

    public CartPage(WebDriver driver) {
        this.element = new SeleniumElement(driver);
    }

    public CartPage inputCountry(String countryCode) {
        element.selectOption("select[name=country_id]", countryCode);
        return this;
    }

    public CartPage inputState(String state) {
        element.selectOption("select[name=region_id]", state);
        return this;
    }

    public CartPage inputZip(String zip) {
        element.enterText("input[name=estimate_postcode]", zip);
        return this;
    }

    public boolean verifyPriceGenerated() {
        return !element.findElements(".cart-totals").isEmpty();
    }

    public String getFeeGenerated() {
        return element.findElementText("#shopping-cart-totals-table > tbody > tr:nth-child(2) > td:nth-child(2) > .price");
    }

    public void selectFeeAndUpdate() {
        element.clickElement("input[name=estimate_method]");
        element.clickElement("button[name=do]");
    }

    public void proceedToCheckout() {
        element.clickElement("button.btn-proceed-checkout");
    }
}
