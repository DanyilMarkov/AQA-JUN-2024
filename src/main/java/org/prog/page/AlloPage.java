package org.prog.page;

public class AlloPage {
    package org.prog.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

    public class AlloPage extends AbstractPage {
        private final static String URL = "https://allo.ua/";
        public AlloPage(WebDriver driver) {
            super(driver, URL);
        }
        public void setSearchValue(String value) {
            WebElement searchBox = driver.findElement(By.id("search-form__input"));
            searchBox.sendKeys(value);
        }

        public void executeSearch() {
            WebElement searchBox = driver.findElement(By.id("search-form__input"));
            searchBox.sendKeys(Keys.ENTER);
        }

        public List<WebElement> getSearchHeaders() {
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));
            return driverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("products-layout__item"), 2));
        }


    }
}
