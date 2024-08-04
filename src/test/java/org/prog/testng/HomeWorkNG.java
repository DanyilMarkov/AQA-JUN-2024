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
    private static final By FIRST_PRODUCT_LINK_XPATH = By.xpath("//div[@class='product-card__img']/a");


    private WebDriver driver;

    public class HomeWorkNG {
        @BeforeSuite
        public void setUp() {

            driver = new ChromeDriver();
        }
        @Test
        public void TestAlloSearch() {
            try {

                driver.get("https://allo.ua/");

                Thread.sleep(4000);

                WebElement searchBox = driver.findElement (By.id("search-form__input"));;
                searchBox.sendKeys("iPhone 15");
                searchBox.sendKeys(Keys.ENTER);

                Thread.sleep(8000);

                List<WebElement> productPrices = driver.findElements(PRODUCT_PRICE_XPATH);
                if (!productPrices.isEmpty()) {
                    String firstProductPrice = productPrices.get(0).getText();
                    System.out.println("Price of the first product on the search page: " + firstProductPrice);

                    WebElement firstProductElement = driver.findElements(FIRST_PRODUCT_LINK_XPATH).get(0);
                    String productUrl = firstProductElement.getAttribute("errf");
                    driver.get(productUrl);

                    Thread.sleep(2000);

                    WebElement productPagePriceElement = driver.findElement(PRODUCT_PRICE_XPATH);
                    String productPagePrice = productPagePriceElement.getText();
                    System.out.println("Price on page: " + productPagePrice);

                    if (firstProductPrice.equals(productPagePrice))
                        System.out.println("Prices same.");
                    else {
                        System.out.println("Prices dont same. Price on  search page: " + firstProductPrice + ", Price on  page: " + productPagePrice);
                    }
                } else {
                    System.out.println("Failed.");
                }
            } catch (Exception e) {
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
