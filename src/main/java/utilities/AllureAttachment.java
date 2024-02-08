package utilities;


import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.*;
import java.util.stream.Collectors;

public class AllureAttachment {


    public static void attachElementScreenshot(WebElement element, String nameOfElement) {
        byte[] screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(nameOfElement, new ByteArrayInputStream(screenshot));
    }


        public static void attachScreenshot(WebDriver driver) {
            Allure.addAttachment("nameTest", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
            }



    public static void attachPageSource(WebDriver driver) {
        String pageSource = driver.getPageSource();
        Allure.addAttachment("Page Source", new ByteArrayInputStream(pageSource.getBytes()));
    }

    public static void attachConsoleLogs(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        String consoleLogs = logEntries.getAll().stream()
                .map(LogEntry::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        byte[] consoleLogsBytes = consoleLogs.getBytes();

        if (consoleLogsBytes.length > 0) {
            Allure.addAttachment("Console Logs", new ByteArrayInputStream(consoleLogsBytes));
        } else {
            Allure.addAttachment("Console Logs", new ByteArrayInputStream("No Console Logs Found".getBytes()));
        }
    }

    // public static void attachURLAsAttachment(String url) {Allure.addAttachment("URL" , url);}


}