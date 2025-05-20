package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class HomePage extends PageObject {

    @FindBy(css = ".customer-name")
    private WebElementFacade accountDropdown;

    @FindBy(css = ".authorization-link a")
    private WebElementFacade signOutLink;

    public void logout() {
        if (!signOutLink.isVisible()) {
            accountDropdown.waitUntilClickable().click();
        }
        signOutLink.waitUntilClickable().click();
    }

    public boolean is_on_home_page() {
        waitABit(5000);
        return getDriver().getCurrentUrl().equals("https://magento.softwaretestingboard.com/");
    }

}
