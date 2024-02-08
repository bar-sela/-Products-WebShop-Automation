package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CartPage extends MenuAndCartPage {

    @FindBy(css = "#continue-shopping")
    WebElement continueShopingBtn;

    @FindBy(css = "#checkout")
    WebElement checkOutBtn;


    // Ctor
    public CartPage(WebDriver driver) {
        super(driver);
    }


    @Step("Go to main products page")
    public void continueShopping(){
        click(continueShopingBtn);
    }

    @Step("Go to checkout")
    public void goToCheckOut(){
        click(checkOutBtn);
    }



}