package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.UserSteps;
import org.example.steps.serenity.UserSteps2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/data/login_valid_data.csv")
public class ScenarioTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public UserSteps2 user;

    private String username;
    private String password;

    @Before
    public void maximizeBrowser() {
        webdriver.manage().window().maximize();
    }

    @Test
    public void scenario_based_test_login_add_delete_logout() {
        // 1. Login valid
        user.open_login_page();
        user.login_with(username, password);
        user.login_success_should_be_visible();

        // 2. Navighează la o categorie de produse
        user.go_to_women_jackets_category();
        user.should_see_jackets_listed();

        // 3. Selectează un produs și îl accesează
        user.select_first_product();
        user.should_be_on_product_page();

        // 4. Selectează o mărime și o culoare, apoi îl adaugă în coș
        user.select_size("M");
        user.select_color("Black");
        user.add_to_cart();
        user.verify_item_added_to_cart();

        // 5. Mergi în coș și șterge produsul
        user.go_to_cart();
        user.remove_item_from_cart();
        user.verify_cart_is_empty();

        // 6. Logout
        user.logout();
        user.verify_logout_successful();
    }
}
