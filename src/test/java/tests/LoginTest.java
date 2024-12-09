package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest {

    @Epic("Блок авторизации онлайн магазина")
    @Feature("TMS-56")
    @Story("TNS-56.67")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("")
    @TmsLink("")
    @Issue("")
    @Description("Проверка входа в онлайн магазин")
    @Flaky
    @Test(description = "Авторизация с корректными данными")
    public void correctLogin() {
        loginPage.open();
        loginPage.login(user, password);
        takeScreenshot(driver);
        assertTrue(productsPage.isDisplayed(), "");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", password, "Epic sadface: Sorry, this user has been locked out."},
                {user, "", "Epic sadface: Password is required"},
                {"", password, "Epic sadface: Username is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginWrongData(String user, String pass, String errorMsg) {
        loginPage.open();
        loginPage.login(user, pass);
        assertEquals(loginPage.getErrorMessage(), errorMsg);
    }
}