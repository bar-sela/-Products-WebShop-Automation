import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.ProductPage;
import utilities.Product;

import java.util.Collections;
import java.util.List;

@Owner("Nachshon Bar-Sela")
@Epic("Enhance Shopping Experience")
@Feature("Products Management")
@Severity(SeverityLevel.MINOR)
@Listeners(utilities.ListenerClass.class)


public class TestNgSort extends TestBaseClass{


    @Story("Sort Items by Name")
    @Description("s a User when I Sort The Products By Their Names. alphbetical and reverse")
    @Test(dataProvider = "getNamesCriterion",description="As a User when I Sort The Products By Their Names. alphbetical and reverse")
    public void tc01_sortProductsByName(String criterion)  {
        // pass the sort criterion to the sortFunction
        ProductPage productsPage = new ProductPage(driver);
        productsPage.sortItems(criterion);

        // get list of all items available by their order on the web page
        List<Product> productsList = productsPage.getListOfAllProducts();

        //if the criterion is nameRev , make the list ordered by reverse
        if(criterion.equals("NameRev"))
            Collections.reverse(productsList);

        // make sure the list of items are actually ordered by their names after sorting them on the web page
        Assert.assertTrue(Product.isSortedByName(productsList));
    }


    @Story("Sort Items by Price")
    @Description("As a User when I Sort The Products By Their Price. from high lo low and from low to high")
    @Test( dataProvider = "getPriceCriterion",description="As a User when I Sort The Products By Their Price. from high lo low and from low to high")
    public void tc02_sortProductsByPrice(String criterion) {
        //sort the available items on the app by their price
        ProductPage productsPage = new ProductPage(driver);
        productsPage.sortItems(criterion); /// pass the criterion to the sortFuncion

        // get list of all items available by their order on the web page
        List<Product> productsList = productsPage.getListOfAllProducts();

        //if the criterion is PriceRev, make the list ordered by reverse it
        if(criterion.equals("PriceRev"))
            Collections.reverse(productsList);

        // make sure the list of items are actually ordered by their price after sorting them on the web page
        Assert.assertTrue(Product.isSortedByPrice(productsList));
    }


    @DataProvider
    public Object[][] getNamesCriterion() {
        return new Object[][]{
                {"Name"},
                {"NameRev"},
        };
    }
    @DataProvider
    public Object[][] getPriceCriterion() {
        return new Object[][]{
                {"Price"},
                {"PriceRev"},
        };
    }


}






