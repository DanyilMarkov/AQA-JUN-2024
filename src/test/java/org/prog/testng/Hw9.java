package org.prog.testng;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Hw9 {
    private WebDriver driver;
    private Connection connection;
    private static final By FIRST_PRODUCT_LINK_XPATH = By.xpath("//div[@class='product-card__img']/a");
    private static final By PRODUCT_NAME_XPATH = By.xpath("//h1[contains(@class, 'product-title')]");
    private static final By PRODUCT_PRICE_XPATH = By.xpath("//div[contains(@class, 'v-pb__cur')]//span[contains(@class, 'sum')]");
    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        connectToDB();
    }

    @Test
    public void testAlloSearch() {

            driver.get("https://allo.ua/")
            WebElement searchBox = driver.findElement (By.id("search-form__input"));
            searchBox.sendKeys("iPhone 15");
            searchBox.sendKeys(Keys.ENTER);

            Thread.sleep(5000);

            List<WebElement> productPrices = driver.findElements(FIRST_PRODUCT_LINK_XPATH);
            if (!productPrices.isEmpty()) {
                WebElement firstProductElement = productLinks.get(0);
                String productUrl = firstProductElement.getAttribute("errf");
                driver.get(productUrl);

                Thread.sleep(5000);

                WebElement productNameElement = driver.findElement(PRODUCT_NAME_XPATH);
                String productName = productNameElement.getText();
                System.out.println("Product Name on the product page: " + productName);

                WebElement productPriceElement = driver.findElement(PRODUCT_PRICE_XPATH);
                String productPagePrice = productPriceElement.getText();
                System.out.println("Price on the product page: " + productPagePrice);

                saveProductDataToDB(productName, productPagePrice);
                verifyProductDataInDB(productName, productPagePrice);

            } else {
                System.out.println("Failed to find the product link on search page.");
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void saveProductDataToDB(String productName, String productPrice) throws SQLException {
    String query = "Place in ForHW9 (ProductName, ProductPrice) VALUES (?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, productName);
        pstmt.setString(2, productPrice);
        pstmt.executeUpdate();
    }
}

private void verifyProductDataInDB(String productName, String expectedPrice) throws SQLException {
    String query = "SELECT ProductPrice FROM ForHW9 WHERE ProductName = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, productName);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String price = rs.getString("ProductPrice");
                if (price.equals(expectedPrice)) {
                    System.out.println("Product data verified: " + productName + " with price: " + price);
                } else {
                    System.out.println("Price mismatch for product: " + productName + ". Expected: " + expectedPrice + ", Found: " + price);
                }
            }
        }
    }
}
private void connectToDB() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db", "user", "password");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
}

@AfterSuite
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }
    try {
        if (connection != null) {
            connection.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
  }
}




