package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.UserSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/data/login_invalid_data.csv")
public class LoginTestInvalid {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public UserSteps user;

    private String username;
    private String password;

    @Before
    public void maximize() {
        webdriver.manage().window().maximize();
    }

    @Test
    public void test_invalid_login_should_fail() {
        user.open_login_page();
        user.login_with(username, password);
        user.login_error_should_be_visible();
    }
}
