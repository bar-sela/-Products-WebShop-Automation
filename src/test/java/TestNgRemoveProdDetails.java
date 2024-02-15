import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.ProductDetails;
import pageObjects.ProductPage;

@Owner("Nachshon Bar-Sela")
@Epic("Shopping Cart")
@Feature("Remove Product")
@Severity(SeverityLevel.NORMAL)
@Listeners(utilities.ListenerClass.class)


public class TestNgRemoveProdDetails extends TestBaseClass {

    @Story("Remove Item from Product Details")
    @Description("Verify removing item from Product Details")
    @Test(description = "Verify removing item from Product Details")
    public void tc01_removeItem(){
        String[] products = {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket"};

        // for each item in the list :
        for(String product : products){
            // go to single representation of an item
            ProductPage productsPage = new ProductPage(driver);
            productsPage.goToItemDetails(product);

            // add this item to cart and navigate back to main products page
            ProductDetails productDetails = new ProductDetails(driver);
            productDetails.addToCart();
            productDetails.backToProducts();
        }
        // calculating num of items in cart
        ProductPage productsPage = new ProductPage(driver);
        int numOfItemsInCartBeforeRemoving = productsPage.numOfItemsInCart();

        // removing specific items from cart
        productsPage.removeItem("Sauce Labs Bolt T-Shirt");
        productsPage.removeItem("Sauce Labs Fleece Jacket");

        //make sure the cart has correct number of items
        int exeptedNumOfItemsInCartAfterRemoving = productsPage.numOfItemsInCart();
        Assert.assertEquals(numOfItemsInCartBeforeRemoving - 2, exeptedNumOfItemsInCartAfterRemoving);
    }
}
