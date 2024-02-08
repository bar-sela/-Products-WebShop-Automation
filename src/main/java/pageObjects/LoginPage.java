package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.AllureAttachment;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#user-name")
    WebElement userNameInput;
    @FindBy(css = "#password")
    WebElement passwordInput;
    @FindBy(css = "#login-button")
    WebElement loginBth;

    /// testing elements
    @FindBy(css = "[data-test ='error']")
    WebElement errorMsg;



    @Step("UserName enterd: {userName} , password added :{password} for Login")
    public void logIn(String userName , String password){
             fillText(userNameInput,userName);
             fillText(passwordInput, password);
             click(loginBth);
    }

    /// VALIDATION functions
    @Step("Return the ErrorMsg in the Login page")
    public String getErrorMsg(){
        AllureAttachment.attachElementScreenshot(errorMsg, "error msg"); // add screenshot of the error
        return getText(errorMsg);}

    public Boolean isLoginPage(){
            return getButtonText(loginBth).equals("Login");
    }



      }







