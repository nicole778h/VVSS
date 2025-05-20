package org.example.steps.serenity;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.example.pages.*;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class UserSteps2 {

    private LoginPage loginPage;
    private CategoryPage categoryPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private HomePage homePage;

    @Step
    public void open_login_page() {
        loginPage.open();
    }

    @Step
    public void login_with(String email, String password) {
        loginPage.enter_email(email);
        loginPage.enter_password(password);
        loginPage.click_login();
    }

    @Step
    public void login_success_should_be_visible() {
        assertTrue("Login was not successful!", loginPage.is_login_successful());
    }

    @Step
    public void go_to_women_jackets_category() {
        categoryPage.open_women_jackets_page();
    }

    @Step
    public void should_see_jackets_listed() {
        assertTrue("No products listed!", categoryPage.products_are_listed());
    }

    @Step
    public void select_first_product() {
        categoryPage.click_first_product();
    }

    @Step
    public void should_be_on_product_page() {
        assertTrue("Not on product page!", productPage.is_on_product_page());
    }

    @Step
    public void select_size(String size) {
        productPage.select_size(size);
    }

    @Step
    public void select_color(String color) {
        productPage.select_color(color);
    }

    @Step
    public void add_to_cart() {
        productPage.click_add_to_cart();
    }

    @Step
    public void verify_item_added_to_cart() {
        assertTrue("Item not added to cart!", productPage.success_message_displayed());
    }

    @Step
    public void go_to_cart() {
        cartPage.open_cart();
    }

    @Step
    public void remove_item_from_cart() {
        cartPage.remove_first_item();
    }

    @Step
    public void verify_cart_is_empty() {
        assertTrue("Cart is not empty!", cartPage.cart_is_empty());
    }

    @Step
    public void logout() {
        homePage.logout();
    }


    @Step
    public void verify_logout_successful() {
        assertTrue("Logout failed! Not redirected to home page.",
                homePage.is_on_home_page());
    }

}
