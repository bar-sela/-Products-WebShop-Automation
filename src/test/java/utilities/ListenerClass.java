package utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;

public class ListenerClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
        if (webDriverAttribute instanceof WebDriver) {
            AllureAttachment.attachScreenshot((WebDriver) webDriverAttribute);
            AllureAttachment.attachConsoleLogs((WebDriver) webDriverAttribute);
            AllureAttachment.attachPageSource((WebDriver) webDriverAttribute);
        }
    }
}
