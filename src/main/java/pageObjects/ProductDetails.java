package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetails extends MenuAndCartPage {

    @FindBy(css = "#back-to-products")
    WebElement backToProducts;

    @FindBy(css = ".btn_inventory")
    WebElement productButton;


    public ProductDetails(WebDriver driver) {
        super(driver);
    }

    @Step("add the specific product to cart")
    public void addToCart(){
        click(productButton);
    }

    @Step("remove the specific item from cart")
    public void removeItem(){
        click(productButton);
    }

    @Step("back to main products page")
    public void backToProducts(){
        click(backToProducts);
    }
}
