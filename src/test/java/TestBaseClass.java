// Importing necessary libraries
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Link;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pageObjects.LoginPage;
import utilities.PropertyReader;


// Adding a link annotation for Allure report
@Listeners(utilities.ListenerClass.class)
@Link("https://www.saucedemo.com")

public class TestBaseClass {

    WebDriver driver;



    @Parameters({"browser"})
    @BeforeClass
    protected void setup(ITestContext testContext , @Optional("Chrome") String browser){

        switch (browser) {
            // Setting up Chrome WebDriver
            case "Chrome" -> {
                driver = new ChromeDriver();
            }
            // Setting up Firefox WebDriver
            case "Firefox" -> {
                driver = new FirefoxDriver();
            }
            // Setting up Edge WebDriver
            case "Edge" -> {
                driver = new EdgeDriver();
            }
            // invalid browser parameter
            default -> throw new IllegalArgumentException("no such browser " + browser);
        }

        testContext.setAttribute("WebDriver", this.driver);
        driver.manage().window().maximize();
        String URL = PropertyReader.readProperty("url");
        driver.get(URL);

        login();

    }

    public void login(){


        LoginPage loginPage = new LoginPage(driver);
        String correctUserName = PropertyReader.readProperty("userName");
        String correctPassword = PropertyReader.readProperty("password");
        loginPage.logIn(correctUserName, correctPassword);

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
