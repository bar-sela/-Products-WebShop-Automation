package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.AllureAttachment;

public class MenuAndCartPage extends BasePage{
    /// chart


    @FindBy(css = "#shopping_cart_container")
    WebElement cartBtn;
    /// menu
    @FindBy(css = "#react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(css = "#react-burger-cross-btn")
    WebElement closeMenuBtn;

    @FindBy(css = "#inventory_sidebar_link")
    WebElement allItemsBtn;

    @FindBy(css = "#reset_sidebar_link")
    WebElement cleanChartBtn;
    @FindBy(css = "#logout_sidebar_link")
    WebElement logOutBtn;
    public MenuAndCartPage(WebDriver driver) {
        super(driver);
    }


    private void closeMenu(){
        click(closeMenuBtn);
    }
    @Step("Go to menu")
    private void watchMenu(){
        click(menuBtn);
    }

    @Step("Go to cart")
    public void watchCart(){
        click(cartBtn);
    }

    // Menu functions:

    // LogOut from app through menu
    @Step("Log out")
    public void logOut(){
        watchMenu();
        waitForElementToBeClickble(logOutBtn);
        click(logOutBtn);
    }

    /// Navigate to main products page through menu
    @Step("Go to Products Page")
    public void gotoAllItems(){
        watchMenu();
        waitForElementToBeClickble(logOutBtn);
        click(allItemsBtn);
    }

    /// Clean cart from items through menu
    @Step("Clean cart from products")
    public void cleanCart(){
        watchMenu();
        waitForElementToBeClickble(logOutBtn);
        click(cleanChartBtn);
        exitMenu();
    }

    @Step("Exit menu")
    public void exitMenu(){
        click(closeMenuBtn);
    }
    ///// Functions for VALIDATION :

    //Returns num of items in the cart
    public int numOfItemsInCart(){
        WebElement shopping_cart_badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        AllureAttachment.attachElementScreenshot(shopping_cart_badge, "cart_budge");  /// screenshot of products number in cart

        String numOfItemsStr = getText(shopping_cart_badge);
        return Integer.parseInt(numOfItemsStr);
    }


}
