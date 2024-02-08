import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.OverviewOrderPage;
import pageObjects.ProductPage;

import java.util.Arrays;
import java.util.List;

@Owner("Nachshon Bar-Sela")
@Epic("CheckOut")
@Feature("User Checkout reliability")
@Listeners(utilities.ListenerClass.class)


public class TestNgCheckOutRelability extends TestBaseClass {
    String fname = "shon";
    String lname = "Bar";
    String zip = "Jerusalem 202121";

    @Story("Correct Products")
    @Test( dataProvider = "getProductsName", description = "Check that the products ordered are exactly the ones inside the receipt ")
    public void tc01_checkProducts(String... products) {
        ProductPage pg = new ProductPage(driver);
        for (String productName : products) {
            pg.addToCart(productName);
        }
        pg.watchCart();

        CartPage cg = new CartPage(driver);
        cg.goToCheckOut();

        CheckoutPage cp = new CheckoutPage(driver);

        cp.checkout(this.fname , this.lname , this.zip);

        OverviewOrderPage op = new OverviewOrderPage(driver);

        List<String> productsChoosedList = Arrays.asList(products);
        List<String> productsInReceipt = op.getProductsInReceipt();

        Assert.assertEquals(productsInReceipt, productsChoosedList);

        op.backToProductsPage();
    }

    @Story("Correct Price")
    @Test( description = "Check that the final cost is correct")
    public void tc02_checkPrice(){
        ProductPage pg = new ProductPage(driver);
        pg.watchCart();
        double calculated_price_of_product = pg.totalCostOfProducts();

        CartPage cg = new CartPage(driver);
        cg.goToCheckOut();

        CheckoutPage cp = new CheckoutPage(driver);
        cp.checkout(this.fname , this.lname , this.zip);

        OverviewOrderPage op = new OverviewOrderPage(driver);
        double writtenCost = op.getTotalPrice();

        Assert.assertEquals(writtenCost, calculated_price_of_product );
    }

    @DataProvider
    public Object[][] getProductsName() {
        return new Object[][]{
                {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light", "Sauce Labs Onesie"},
        };
    }
}
