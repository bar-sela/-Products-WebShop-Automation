import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.ProductPage;
import utilities.ExcelReader;
import static utilities.PropertyReader.readProperty;


@Owner("Nachshon Bar-Sela")
@Epic("Shopping Cart")
@Feature("Add To Cart Through Main Products Page")
@Severity(SeverityLevel.CRITICAL)
@Listeners(utilities.ListenerClass.class)


public class TestNgAddProducts extends TestBaseClass {


    // fucntion to get data from Excel file and add it to the cart
    @Story("Buy One Product")
    @Description("Verify adding a product to the cart through main products page")
    @Test( dataProvider = "getProductNameFromExcel", description = "Verify adding a product to the cart through main products page")

    public void tc01_addOneProduct(String nameProduct) {
        // add item to cart
        ProductPage productsPage = new ProductPage(driver);
        productsPage.addToCart(nameProduct);

        /// make sure exactly one product added to cart and clean the cart after
        int exeptedNumOfItems = productsPage.numOfItemsInCart();
        Assert.assertEquals(1, exeptedNumOfItems);
        productsPage.cleanCart();
    }



    @Story("Buy Many Products")
    @Description("Verify adding many products to the cart through main products page")
    @Test(dataProvider = "getProductsName", description = "Verify adding many products to the cart through main products page")

    public void tc02_addProducts(String... products) {

        //  clean the chart before every iteration of the dataProvider
        ProductPage productsPage = new ProductPage(driver);
        int productsCounter = 0 ;

        for (String product : products) {  // all the products from data provider

            productsPage.addToCart(product);
            productsCounter++;

            // make sure all products from data provider actually added to the cart in the app
            int exeptedNumOfItems = productsPage.numOfItemsInCart();
            Assert.assertEquals(productsCounter, exeptedNumOfItems);
        }
    }

    @DataProvider
    public Object[][] getProductNameFromExcel() {
        return (ExcelReader.readExcelFile(readProperty("excelPath")));
    }

    @DataProvider
    public Object[][] getProductsName() {
        return new Object[][]{
                {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light", "Sauce Labs Onesie"},
                {"Sauce Labs Fleece Jacket","Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie", "Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Bike Light", "Sauce Labs Backpack"},
        };
    }

}