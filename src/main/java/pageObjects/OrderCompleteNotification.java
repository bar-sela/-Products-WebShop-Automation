package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.AllureAttachment;

public class OrderCompleteNotification extends MenuAndCartPage{

    @FindBy(css = "#back-to-products")
    WebElement backToCart;

    @FindBy(css = "#header_container .title")
    WebElement completeOrderMsg;


    public OrderCompleteNotification(WebDriver driver) {
        super(driver);

    }

    @Step("Back to cart")
    public void setBackToCart(){
        click(backToCart);
    }

    /// VALIDATION functions:
    @Step("Check if the page that we are on is order complete page ")
    public boolean isInOrderCompleteNotification() {
        AllureAttachment.attachElementScreenshot(completeOrderMsg,"CompleteOrderMsg");
        return getText(completeOrderMsg).endsWith("Complete!");
    }

}
