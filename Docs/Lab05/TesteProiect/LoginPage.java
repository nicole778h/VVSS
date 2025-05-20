package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://magento.softwaretestingboard.com/customer/account/login/")
public class LoginPage extends PageObject {

    @FindBy(id = "email")
    private WebElementFacade emailField;

    @FindBy(id = "pass")
    private WebElementFacade passwordField;

    @FindBy(id = "send2")
    private WebElementFacade loginButton;

    public void enter_email(String email) {
        waitFor(emailField).type(email);
    }

    public void enter_password(String password) {
        waitFor(passwordField).type(password);
    }

    public void click_login() {
        waitFor(loginButton).click();
    }

    public boolean is_login_successful() {
        // Verifică dacă utilizatorul a fost redirecționat către pagina de cont
        return getDriver().getCurrentUrl().contains("/customer/account") ||
                containsText("My Account");
    }

    public boolean is_login_failed() {
        // Verifică dacă există un mesaj de eroare de login pe pagină
        return findAll(By.cssSelector("div.message-error")).stream()
                .anyMatch(WebElementFacade::isVisible);
    }
    public boolean is_on_login_page() {
        return getDriver().getCurrentUrl().contains("customer/account/login");
    }

}
