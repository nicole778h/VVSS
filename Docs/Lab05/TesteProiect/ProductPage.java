package org.example.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class ProductPage extends PageObject {

    public boolean is_on_product_page() {
        return getTitle().toLowerCase().contains("jacket");
    }

    public void select_size(String size) {
        WebElementFacade sizeOption = find(By.xpath("//div[@option-label='" + size + "']"));
        sizeOption.click();
    }

    public void select_color(String color) {
        WebElementFacade colorOption = find(By.xpath("//div[@option-label='" + color + "']"));
        colorOption.click();
    }

    public void click_add_to_cart() {
        WebElementFacade addToCartButton = find(By.id("product-addtocart-button"));
        addToCartButton.click();
    }

    public boolean success_message_displayed() {
        return containsText("You added") || find(By.cssSelector(".message-success")).isDisplayed();
    }
}
