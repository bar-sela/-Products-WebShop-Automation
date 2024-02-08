package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;


public class OverviewOrderPage extends MenuAndCartPage {
    public OverviewOrderPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(css = "#finish")
    WebElement completeOrderBtn;

    @FindBy(css = "#cancel")
    WebElement backToProducts;

    @FindBy(css = ".summary_total_label")
    WebElement totalCost;


    @FindBy(css = ".inventory_item_name")
    List<WebElement> productsInReceipt;

    @FindBy(css = ".summary_subtotal_label")
    WebElement priceElement;

    @Step("Back to product page")
    public void backToProductsPage() {
        click(backToProducts);
    }

    @Step("Finish the order")
    public void finishOrder() {
        click(completeOrderBtn);
    }


    /// VALIDATION functions

    // Cheaks if we are on the right page
    @Step("Check if the page that we are on is overview page on all products selected")
    public Boolean isOverviewPage() {
            return getText(totalCost).startsWith("Total");
    }



    @Step("Returns list of all ordered products name")
    public List<String> getProductsInReceipt(){
        List<String> recipitPorocutsNames = new ArrayList<>();
        for(WebElement el : productsInReceipt) {
            recipitPorocutsNames.add(getText(el));
        }
        return recipitPorocutsNames;
    }


    /// Returns the price of the order
    @Step("Get the total price of the order ")
    public double getTotalPrice(){
      //  AllureAttachment.attachElementScreenshot(priceElement, "Price");
        return Double.parseDouble(getText(priceElement).replace("Item total: $" , ""));
    }


}

