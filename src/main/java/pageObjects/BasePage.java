package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

     WebDriver driver;
     Actions actions ;
     WebDriverWait wait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForPageToBeFullyLoaded();
    }

    public void clear(WebElement el) {
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }

    public void fillText(WebElement el, String text) {
        clear(el);
        if (! text.equals("")) {
            highlightElement(el, "orange" , "black");
            el.sendKeys(text);

        }
    }

    public void click(WebElement el) {
        highlightElement(el, "yellow" , "black");
        el.click();
    }

    public void hover(WebElement element){
        actions.moveToElement(element).build().perform();
    }
    public void submit(WebElement el){
        el.submit();
    }
    public String getText(WebElement el) {
        return el.getText();
    }

    public String getButtonText(WebElement btn){
        return btn.getAttribute("value");
    }


    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void alertOK(String text) {
        sleep(1500);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        alert.accept();
    }

    public void goBack(){

        driver.navigate().back();
    }

    /// MOUSE

    public void dragAndDrop(WebElement element , WebElement position){
        actions.clickAndHold(element).build().perform();
        sleep(200);
        actions.moveToElement(position).build().perform();
        sleep(500);
        actions.release().build().perform();
    }

    public void setZoomLevel( double zoomFactor) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "document.body.style.transform = 'scale(" + zoomFactor + ")';";
        js.executeScript(script);
    }
    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ")");
    }
    /// WAIT OPERATIONS

    public void waitForPageToBeFullyLoaded(){
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForElementToBeClickble(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
    public void waitForVisabilityOfElement(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }


    //////// color the elements

    private void highlightElement(WebElement element, String backGroundColor, String borderColor) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color: "+backGroundColor+";border: 1px solid " + borderColor + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few miliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);

    }

    }

