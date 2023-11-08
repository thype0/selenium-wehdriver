package test.tc01;

/*

Test Steps
Step 1. Go to http://live.techpanda.org/
Step 2. Verify Title of the page
Step 3. Click on -> MOBILE -> menu
Step 4. In the list of all mobile , select SORT BY -> dropdown as name
Step 5. Verify all products are sorted by name

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
public class TC01 {

    public static void testTC01() {

        WebDriver driver = DriverFactory.getDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Step 2. Verify Title of the page
            Assert.assertEquals(driver.getTitle(), "Home page");

            //Step 3. Click on -> MOBILE -> menu
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement mobileBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".level0"))));
            mobileBtn.click();

            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            By selSelector = By.cssSelector(".sort-by > select");
            WebElement sortBtn = driver.findElement(selSelector);
            sortBtn.click();

            WebElement sortNameBtn = sortBtn.findElement(By.cssSelector("option:nth-child(2)"));
            sortNameBtn.click();

            //Step 5. Verify all products are sorted by name
            List<WebElement> names = driver.findElements(By.cssSelector(".product-name > a"));
            Assert.assertTrue(isSorted(names));

            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }

    public static boolean isSorted(List<WebElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            String name1 = elements.get(i).getText();
            String name2 = elements.get(i + 1).getText();

            if (name1.compareTo(name2) > 0) {
                return false;
            }
        }
        return true;
    }
}
