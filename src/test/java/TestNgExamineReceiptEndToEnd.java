import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;

@Owner("Nachshon Bar-Sela")
@Epic("Receipt Examination")
@Feature("Buy Product End To End")
@Severity(SeverityLevel.CRITICAL)
@Listeners(utilities.ListenerClass.class)

public class TestNgExamineReceiptEndToEnd extends TestBaseClass {

    @Story("Buy Product End To End")
    @Description("Complete a full process of buying a product from start to end")
    @Test(dataProvider = "getProductsName", description = "Complete a full process of buying a product from start to end")
    public void tc01_CheakOrderFinish(String... products) {
        // add single product from the Data provider in each iteration of the loop
        ProductPage pg = new ProductPage(driver);
        for (String productName : products) {
            pg.addToCart(productName);
        }
        // navigate to cart page
        pg.watchCart();

        // navigate to checkout Page
        CartPage cg = new CartPage(driver);
        cg.goToCheckOut();

        // commit checkout Page
        CheckoutPage cp = new CheckoutPage(driver);
        cp.checkout("shon" , "bar" , "Jerusalem 314");

        //  complete order
        OverviewOrderPage op = new OverviewOrderPage(driver);
        op.finishOrder();

        OrderCompleteNotification oc = new OrderCompleteNotification(driver);
        // make sure the order is done
        Assert.assertTrue(oc.isInOrderCompleteNotification());
    }

    @DataProvider
    public Object[][] getProductsName() {
        return new Object[][]{
                {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light", "Sauce Labs Onesie"},
        };
    }

  }
