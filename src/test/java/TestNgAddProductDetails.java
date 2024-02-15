    import io.qameta.allure.*;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Listeners;
    import org.testng.annotations.Test;
    import pageObjects.ProductDetails;
    import pageObjects.ProductPage;

    @Owner("Nachshon Bar-Sela")
    @Epic("Shopping Cart")
    @Feature("Add To Cart Through Product Window")
    @Severity(SeverityLevel.NORMAL)
    @Listeners(utilities.ListenerClass.class)


    public class TestNgAddProductDetails extends TestBaseClass {



        @Story("Add Product")
        @Description("Verify adding a product to the cart through the product window")
        @Test(description = "Verify adding a product to the cart through the product window")
        public void tc01_addProduct() {
            // go to product page
            ProductPage productsPage = new ProductPage(driver);
            String itemName = "Sauce Labs Bike Light";
            productsPage.goToItemDetails(itemName);

            // go to representation of a single item
            ProductDetails productDetails = new ProductDetails(driver);
            productDetails.addToCart();

            /// make sure there was exacly on product added
            Assert.assertEquals(1, productDetails.numOfItemsInCart());

            productDetails.cleanCart();     /// clean cart from products
            productDetails.backToProducts(); // back to the main product page
        }

        @Story("Add Multiple Products")
        @Description("Verify adding a many products to the cart through the product window")
        @Test(dataProvider = "getProductsName" , description = "Verify adding a many products to the cart through the product window")

        public void tc02_addProducts(String... products) {
            int itemsCounter = 0;
            for (String product : products) {
                // go to single representation of a specific product
                ProductPage pg = new ProductPage(driver);
                pg.goToItemDetails(product);

                // add the item to cart
                ProductDetails pd = new ProductDetails(driver);
                pd.addToCart();
                itemsCounter++;


                // assert the amount of products we added and the number of items written next to the cart logo

                int exeptedNumOfItems = pd.numOfItemsInCart();
                Assert.assertEquals(itemsCounter,exeptedNumOfItems );
                pd.backToProducts();   //**************** back to the main product page********************
            }
        }
        @DataProvider
        public Object[][] getProductsName() {
            return new Object[][]{
                    {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light", "Sauce Labs Onesie"},
            };
        }
    }







