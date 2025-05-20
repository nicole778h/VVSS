package org.example.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html")
public class CategoryPage extends PageObject {

    public void open_women_jackets_page() {
        open();
    }

    public boolean products_are_listed() {
        return !findAll(By.cssSelector(".product-item")).isEmpty();
    }

    public void click_first_product() {
        WebElementFacade firstProduct = find(By.cssSelector(".product-item a.product-item-link"));
        firstProduct.click();
    }
}
