package org.example.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class CartPage extends PageObject {

    public void open_cart() {
        WebElementFacade cartIcon = find(By.cssSelector("a.showcart"));
        cartIcon.click();
        WebElementFacade viewCart = find(By.cssSelector("a.action.viewcart"));
        waitFor(viewCart).click();
    }

    public void remove_first_item() {
        WebElementFacade removeButton = find(By.cssSelector("a.action-delete[title='Remove item']"));
        withAction().moveToElement(removeButton).click().perform();
        waitABit(2000); // dacă e nevoie, se poate înlocui cu un wait fluent pe confirmare/absență
    }


    public boolean cart_is_empty() {
        return find(By.cssSelector(".cart-empty")).isVisible();
    }

}
