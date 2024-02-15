import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.OverviewOrderPage;
import pageObjects.ProductPage;

@Owner("Nachshon Bar-Sela")
@Epic("Checkout")
@Feature("User Checkout Functionality")
@Severity(SeverityLevel.CRITICAL)
@Listeners(utilities.ListenerClass.class)

public class TestNgCheckOutFunctionality extends TestBaseClass{

    // Add product to cart before the checkout tests
    @BeforeClass
    public void createCartWithProducts(){

        // go to product page
        ProductPage pg = new ProductPage(driver);
        pg.addToCart("Sauce Labs Bike Light");
        pg.watchCart();

        // go to cart
        CartPage cg = new CartPage(driver);
        cg.goToCheckOut();
    }

    @Story("No First Name")
    @Description("User cannot proceed with checkout without providing a First Name")
    @Test(description = "User cannot proceed with checkout without providing a First Name")
    public void tc01_wrongCheckout(){
        // commit checkout
        CheckoutPage cp = new CheckoutPage(driver);
        cp.checkout("" , "Bar", "Jerusalem");
        String errorMsg = cp.getErrorMsg();

        Assert.assertEquals(errorMsg, "Error: First Name is required");
    }

    @Story("No Last Name")
    @Description("User cannot proceed with checkout without providing a Last Name")
    @Test(description = "User cannot proceed with checkout without providing a Last Name")
    public void tc02_wrongCheckout(){
        CheckoutPage cp = new CheckoutPage(driver);
        cp.checkout("Nachshon" ,"" , "Jerusalem");

        // make sure the correct error msg pops
        String errorMsg = cp.getErrorMsg();
        String expectedErrorMsg = "Error: Last Name is required";
        Assert.assertEquals(errorMsg, expectedErrorMsg);
    }

    @Story("No Zip")
    @Description("User cannot proceed with checkout without providing a Zip Code")
    @Test(description ="User cannot proceed with checkout without providing a Zip Code")
    public void tc03_wrongCheckout(){
        // commit checkout
        CheckoutPage cp = new CheckoutPage(driver);
        cp.checkout("Nachshon" ,"Bar" , "");

        // make sure the correct error msg pops
        String errorMsg = cp.getErrorMsg();
        String expectedErrorMsg ="Error: Postal Code is required";
        Assert.assertEquals(errorMsg, expectedErrorMsg);
    }

    @Story("Correct Checkout")
    @Description("User successfully completes the checkout process")
    @Test(description = "User successfully completes the checkout process")
    public void tc04_correctCheckout(){
        // commit checkout
        CheckoutPage cp = new CheckoutPage(driver);
        cp.checkout("Nachshon" ,"Bar" , "Jerusalem");

        // make sure the driver navigated to the OverviewPage
        OverviewOrderPage op = new OverviewOrderPage(driver);
        Assert.assertTrue(op.isOverviewPage());

        op.goBack();  // back to Product Page
    }
}
