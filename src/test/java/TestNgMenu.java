import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductDetails;
import pageObjects.ProductPage;
import utilities.PropertyReader;


@Owner("Nachshon Bar-Sela")
@Epic("Menu")
@Severity(SeverityLevel.CRITICAL)
@Listeners(utilities.ListenerClass.class)


public class TestNgMenu extends TestBaseClass {


    @Feature("Navigation")
    @Story("Navigate from Cart to All Items")
    @Description("Verify navigating from Cart to All Items")
    @Test(description = "Verify navigating from Cart to All Items" )
    public void tc01_goToAllItemsFromCart(){
        // go to cart
        ProductPage pg = new ProductPage(driver);
        pg.watchCart();

        //  go to main products page
        CartPage cartPage = new CartPage(driver);
        cartPage.gotoAllItems();

        // make sure the driver navigated to products page
        Assert.assertTrue(pg.isProductPage());
    }


    @Feature("Navigation")
    @Story("Navigate from Product Details to All Items")
    @Description("Verify navigating from Product Details to All Items")
    @Test(description = "Verify navigating from Product Details to All Items")
    public void tc02_goToAllItemsFromProductDetails(){
        // go to representation of a single item
        ProductPage pg = new ProductPage(driver);
        pg.goToItemDetails("Sauce Labs Bolt T-Shirt");

        // go to main products page
        ProductDetails pd = new ProductDetails(driver);
        pd.gotoAllItems();

        // make sure the driver navigated to products page
        Assert.assertTrue(pg.isProductPage());
    }

    @Feature("Navigation")
    @Story("Navigate to All Items from Main Products Page")
    @Description("Verify navigating to All Items from Products")
    @Test(description = "Verify navigating to All Items from Products")
    public void tc03_goToAllItemsFromProducts(){
        /// make sure the driver stayed at the same page(main products page)
        ProductPage pg = new ProductPage(driver);
        Assert.assertTrue(pg.isProductPage());
    }


    @Feature("Clean Cart")
    @Story("Clean Cart From Cart Page")
    @Description("Verify cleaning cart from Cart Page")
    @Test(description = "Verify cleaning cart from Cart Page")
    public void tc04_cleanCartFromFromCartPage() {
        // add item to cart and go to cart
        ProductPage pg = new ProductPage(driver);
        pg.addToCart("Sauce Labs Bolt T-Shirt");
        pg.watchCart();

        // clean cart and navigate back to main product page
        CartPage cp = new CartPage(driver);
        cp.cleanCart();
        cp.continueShopping();

        // Wanted no products after cleaning the cart
        pg = new ProductPage(driver);
        Assert.assertEquals(pg.numberOfItemsSelected(), 0);
    }



    @Feature("Clean Cart")
    @Story("Clean Cart From Product Details page")
    @Description("Verify cleaning cart from Product Details")
    @Test(description = "Verify cleaning cart from Product Details")
    public void ct05_cleanCartFromProductDetails(){
        // go to representation of a single item
        ProductPage pg = new ProductPage(driver);
        pg.goToItemDetails("Sauce Labs Bolt T-Shirt");

        // add this item to cart , clean it and back to main product page
        ProductDetails pd = new ProductDetails(driver);
        pd.addToCart();
        pd.cleanCart();
        pd.backToProducts();

        // make sure no items are in the cart
        pg = new ProductPage(driver);
        Assert.assertEquals(pg.numberOfItemsSelected(), 0);   /// Wanted no products after cleaning the cart
    }

    @Feature("Navigation")
    @Story("Logout from Cart Page")
    @Description("Verify logout functionality from Cart")
    @Test(description = "Verify logout functionality from Cart")
    public void tc06_logOutFromCart(){
        // go to cart
        ProductPage pg = new ProductPage(driver);
        pg.watchCart();

        // logout
        CartPage cartPage = new CartPage(driver);
        cartPage.logOut();

        // make sure the driver navigated to sign in page
        LoginPage lg = new LoginPage(driver);
        Assert.assertTrue(lg.isLoginPage()); /// is the driver on the login Page after clicking logout
    }






    @Feature("Navigation")
    @Story("Logout from Product Details page")
    @Description("Verify logout functionality from Product Details")
    @Test(description = "Verify logout functionality from Product Details" )
    public void tc07_logOutFromProductDetails(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(PropertyReader.readProperty("userName"), PropertyReader.readProperty("password"));

        // go to representation of a single item
        ProductPage pg = new ProductPage(driver);
        pg.goToItemDetails("Sauce Labs Bolt T-Shirt");

        // logout
        ProductDetails pd = new ProductDetails(driver);
        pd.logOut();

        // make sure the driver navigated to sign in page
        LoginPage lg = new LoginPage(driver);
        Assert.assertTrue(lg.isLoginPage());   /// is the driver on the login Page after clicking logout
    }

    @Feature("Navigation")
    @Story("Logout From Main Products Page")
    @Description("Verify logout functionality from Products")
    @Test(description = "Verify logout functionality from Products")
    public void tc08_logOutFromProducts(){
        // commit login in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(PropertyReader.readProperty("userName"), PropertyReader.readProperty("password"));

        // logout
        ProductPage pg = new ProductPage(driver);
        pg.logOut();

        // make sure the driver navigated to sign in page
        LoginPage lg = new LoginPage(driver);
        Assert.assertTrue(lg.isLoginPage());
    }



}
