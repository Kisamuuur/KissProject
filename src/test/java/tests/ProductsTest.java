package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {
    @Test
    public void addGoods() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.isOpened();
        productsPage.addToCart(1);
        productsPage.addToCart("Sauce Labs Onesie");
        productsPage.openCart();
        assertTrue(productsPage.getProductsNames().contains("Sauce Labs Onesie"));
    }
}
