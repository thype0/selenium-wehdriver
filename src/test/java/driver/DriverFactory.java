package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    public static WebDriver getDriver() {
        try {
            WebDriverManager.edgedriver().setup();
            WebDriver driver = new EdgeDriver();
            driver.manage().window().maximize();
            return driver;
        } catch (Exception e) {
            System.out.println("Edge Driver not available. Using Chrome Driver instead.");
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        }
    }
}
