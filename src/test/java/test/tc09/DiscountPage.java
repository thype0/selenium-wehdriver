package test.tc09;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DiscountPage {

    private final WebDriver driver;

    public DiscountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void applyCoupon() {
        WebElement discountForm = driver.findElement(By.className("discount-form"));
        discountForm.findElement(By.cssSelector("input#coupon_code"))
                .sendKeys("GURU50");

        discountForm.findElement(By.cssSelector("button[title=Apply]")).click();
    }

    public void verifyDiscount() {
        String subTotal = driver.findElement(By.cssSelector("#shopping-cart-totals-table > tbody > tr:nth-child(1) > td:nth-child(2) > span")).getText();
        String discounted = driver.findElement(By.cssSelector("#shopping-cart-totals-table > tbody > tr:nth-child(2) > td:nth-child(2) > span")).getText();

        System.out.println("Subtotal: " + subTotal);
        System.out.println("Discount: " + discounted);
        Assert.assertTrue(verifyDiscount(
                subTotal, discounted, 5));
    }

    private static boolean verifyDiscount(String originalPriceStr, String discountAmountStr, double discountPercentage) {
        // Remove the "$" symbol and any other non-numeric characters
        originalPriceStr = originalPriceStr.replaceAll("[^0-9.]", "");
        discountAmountStr = discountAmountStr.replaceAll("[^0-9.]", "");

        double originalPrice = Double.parseDouble(originalPriceStr);
        double discountAmount = Double.parseDouble(discountAmountStr);
        double expectedDiscount = originalPrice * discountPercentage / 100.0;
        return Math.abs(discountAmount) == expectedDiscount;
    }
}
