import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import utilities.PropertyReader;

// Test class for login functionality

@Owner("Nachshon Bar-Sela")
@Epic("User Authentication")
@Feature("Login")
@Severity(SeverityLevel.CRITICAL)
@Listeners(utilities.ListenerClass.class)

public class TestNglogIn extends TestBaseClass {

	@BeforeClass
	@Override
	public void login() {
	}

	@Story("Failed Login Scenarios")
	@Description("Verify Appropriate message for applying only password")
	@Test(description = "Verify Appropriate message for applying only password")
	public void tc01_failedTestScenario(){
		// log in
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logIn("", "secret_sauce1");

		// make sure the correct errorMsg pops up
		String supposedMsg = "Epic sadface: Username is required";
		Assert.assertEquals( loginPage.getErrorMsg(),supposedMsg);
	}

	@Story("Failed Login Scenariosa")
	@Description("Verify Appropriate message for applying only userName")
	@Test(description = "Verify Appropriate message for applying only userName")
	public void tc02_failedTestScenario(){
		// log in
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logIn("standard_user", "");

		// make sure the correct errorMsg pops up
		String supposedMsg = "Epic sadface: Password is required";
		Assert.assertEquals(loginPage.getErrorMsg(),supposedMsg);
	}


	@Story("Failed Login Scenarios")
	@Description("Verify Appropriate message for applying wrong email or username")
	@Test(dataProvider = "getData", description = "Verify Appropriate message for applying wrong email or username")
	public void tc03_failedTestScenario(String userName, String password) {

		// log in
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logIn(userName, password);


		String supposedMsg = "Epic sadface: Username and password do not match any user in this service";
		// Assert the error message displayed for failed login attempts
		Assert.assertEquals(loginPage.getErrorMsg(),supposedMsg);
	}



	@Story("Successful Login")
	@Description("Verify successful login with correct login information")
	@Test(description = "Verify successful login with correct login information")
	public void tc04_successLogin() {
		// log in
		LoginPage loginPage = new LoginPage(driver);
		String correctUserName = PropertyReader.readProperty("userName");
		String correctPassword = PropertyReader.readProperty("password");
		loginPage.logIn(correctUserName, correctPassword);

		// Assert if product page is displayed after successful login
		ProductPage productsPage = new ProductPage(driver);
		Assert.assertTrue(productsPage.isProductPage());

	}


	@DataProvider
	public Object[][] getData() {
		return new Object[][]{
				{"user1", "123"},
				{"gal", "123456878"},
		};
	}
}
