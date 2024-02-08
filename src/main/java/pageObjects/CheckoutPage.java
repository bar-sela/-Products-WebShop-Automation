package pageObjects;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.AllureAttachment;


public class CheckoutPage extends MenuAndCartPage{

    @FindBy(css = "#first-name")
    WebElement firstNameInput;

    @FindBy(css = "#last-name")
    WebElement lastNameInput;

    @FindBy(css = "#postal-code")
    WebElement zipInput;

    @FindBy(css = "#continue")
    WebElement continueBtn;

    @FindBy(css = "#cancel")
    WebElement cancelBtn;

    @FindBy(css = "[data-test='error']")
    WebElement errorMsg;




    public CheckoutPage(WebDriver driver)  {
        super(driver);
    }


    @Step("Entered First name :{fname} , Last name: {lname} , Zip number: {zipName} at Checkout stage   ")
    public void checkout(String fname , String lname , String zipNum){
        fillText(firstNameInput,fname);
        fillText(lastNameInput,lname);
        fillText(zipInput, zipNum);
        click(continueBtn);
    }


    /// VALIDATION functions
    @Step("return the ErrorMsg in the checkout page")
    public String getErrorMsg(){
        AllureAttachment.attachElementScreenshot(errorMsg, "errorMsg");
        return getText(errorMsg);
    }








}
