package org.prog.testng;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;
public class HomeWorkNG {

    private static final String PRODUCT_PRICE_XPATH = "//div[contains(@class, 'v-pb__cur')]//span[contains(@class, 'sum')]";

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {

        driver = new ChromeDriver();
    }
    @Test
    public void testAlloSearch() {
        try {

            driver.get("https://allo.ua/");

            Thread.sleep(4000);

            WebElement searchBox = driver.findElement (By.id("search-form__input"));;
            searchBox.sendKeys("iPhone 15");
            searchBox.sendKeys(Keys.ENTER);

            Thread.sleep(8000);

            List<WebElement> productPrices = driver.findElements(By.xpath(PRODUCT_PRICE_XPATH));
            if (!productPrices.isEmpty()) {
                String firstProductPrice = productPrices.get(0).getText();
                System.out.println("Price of the first product: " + firstProductPrice);
            } else {
                System.out.println("No products found or prices not visible.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterSuite
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}