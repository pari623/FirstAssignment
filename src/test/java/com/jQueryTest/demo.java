package com.jQueryTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class demo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/Users/guzhanuerxilili/Downloads/FirstAssignment/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Tooltip")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement tooltipPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(tooltipPage);
        WebElement ageTextBox=driver.findElement(By.id("age"));
        ageTextBox.click();
        sleep(2);
        if(ageTextBox.isDisplayed())
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");

        driver.close();
        driver.quit();









    }
    public static void waitForElement(WebDriver driver, WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
