package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.Product;
import java.util.ArrayList;
import java.util.List;


public class ProductPage extends MenuAndCartPage {

    @FindBy(css = ".inventory_item_description")  // full details of items
    List<WebElement> inventoryOfAllItemsList;

    @FindBy(css = ".inventory_item_price")
    List<WebElement> getProductSelectetPriceList;

    @FindBy(css = ".product_sort_container")
    WebElement productSortBtn;

    @FindBy(css = ".btn_secondary.btn_inventory")
    List<WebElement> productSelected;

    @FindBy(css = ".inventory_item_name")   // headline of items
    List<WebElement> productsTitles;

    //// for Tests :
    @FindBy(css = ".header_secondary_container .title") /// "products" title
    WebElement pageLabel;


    public ProductPage(WebDriver driver) {
        super(driver);
    }




    /// Products functions:


    @Step("Add To Cart : {productName}")
    public void addToCart(String productName) {
        for (WebElement item : inventoryOfAllItemsList) {
            String itemName = getText(item.findElement(By.cssSelector(".inventory_item_name")));
            if (itemName.equals(productName)) {
                click(item.findElement(By.cssSelector(".btn_primary")));  /// click the addToChartBtn
                break;
            }
        }
    }

    /// Removes item from cart

    @Step("Remove from cart : {productName}")
    public void removeItem(String productName) {
        for (WebElement item : inventoryOfAllItemsList) {
            String itemName = getText(item.findElement(By.cssSelector(".inventory_item_name")));
            if (itemName.equals(productName)) {
                click(item.findElement(By.cssSelector(".btn_secondary")));  /// click the addToChartBtn
                break;
            }
        }
    }

    // read about the Products specifically

        @Step("go to: {productName}")
        public void goToItemDetails(String productName) {
               for (WebElement headline : productsTitles){
                   if(getText(headline).equals(productName)) {
                       click(headline);
                       break;
                   }
               }
        }

    // Sort the items by criterion
    @Step("Sort criterion added: {criterion}")
    public void sortItems(String criterion){
        Select select = new Select(productSortBtn);
        switch (criterion) {
            case "Name" -> select.selectByValue("az");
            case "NameRev" -> select.selectByValue("za");
            case "Price" -> select.selectByValue("lohi");
            case "PriceRev" -> select.selectByValue("hilo");
        }
    }



    //Validation Functions:

    /// Returns if we are on the products page
    @Step("Check if the current page is the the main products page")
    public boolean isProductPage() {
            String currentPageLabel = getText(pageLabel);
            return currentPageLabel.equals("Products");
    }


@Step("Returns a list of Products Objects (contains name and price ) for each item added to cart")
    public List<Product> getListOfAllProducts(){
        ArrayList<Product> arrayList = new ArrayList<>();

        for(WebElement el : inventoryOfAllItemsList){
            String name = getText(el.findElement(By.cssSelector(".inventory_item_name")));
            String textPrice = getText(el.findElement(By.cssSelector(".inventory_item_price")));
            double price =  Double.parseDouble(textPrice.replace("$", "")); // remove dollar sighn to get only the price number

            Product newItem = new Product(name,price);  /// create new product object
            arrayList.add(newItem);
        }
        return arrayList;
    }

    @Step("Retunres the number of item selected")
    public int numberOfItemsSelected(){
        return productSelected.size();
    }


    /// Returns the price of all orders products
    @Step("Returnes the total cost of all products selected")
     public double totalCostOfProducts(){
        double sum = 0 ;
        for(WebElement el : getProductSelectetPriceList){
            sum += Double.parseDouble(getText(el).replace("$", ""));
        }
        return sum;
}
}




