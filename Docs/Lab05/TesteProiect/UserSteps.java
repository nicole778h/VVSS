package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class UserSteps {

    private LoginPage loginPage;

    @Step("User opens login page")
    public void open_login_page() {
        loginPage.open();
    }

    @Step("User logs in with email: {0} and password: {1}")
    public void login_with(String email, String password) {
        loginPage.enter_email(email);
        loginPage.enter_password(password);
        loginPage.click_login();
    }

    @Step("User should see login result: {0}")
    public void should_see_login_result(String expectedResult) {
        if ("success".equalsIgnoreCase(expectedResult)) {
            assertTrue("Expected login success", loginPage.is_login_successful());
        } else {
            assertTrue("Expected login failure", loginPage.is_login_failed());
        }
    }

    // ✅ Metode dedicate pentru testele separate (valid/invalid)

    @Step("Login should be successful")
    public void login_success_should_be_visible() {
        assertTrue("Login was not successful!", loginPage.is_login_successful());
    }

    @Step("Login error message should be visible")
    public void login_error_should_be_visible() {
        assertTrue("Login error was expected but not shown!", loginPage.is_login_failed());
    }
}
