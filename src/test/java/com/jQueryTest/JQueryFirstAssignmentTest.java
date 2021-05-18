package com.jQueryTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class JQueryFirstAssignmentTest {
    public static void main(String[] args) throws InterruptedException {

        //Test for Draggable
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/Users/guzhanuerxilili/Downloads/FirstAssignment/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://jqueryui.com/");
        driver.findElement(By.linkText("Draggable")).click();
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");
        WebElement iframe=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(iframe);
        WebElement source= driver.findElement(By.id("draggable"));
        Actions actions=new Actions(driver);
        Point beforeDrag=source.getLocation();
        int beforeMoved=beforeDrag.getX();
        actions.dragAndDropBy(source,120,70).perform();
        Thread.sleep(2000);
        Point afterDrag= source.getLocation();
        int afterMoved= afterDrag.getX();
        if(afterMoved>beforeMoved)
            System.out.println("Test Passed and Element dragged around");
        else
            System.out.println("Test Failed and Element is still in the same location");



        // Test for droppable

        driver.get("https://jqueryui.com/");
        driver.findElement(By.linkText("Droppable")).click();
        jse.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        WebElement droppablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(droppablePage);
        WebElement draggable= driver.findElement(By.id("draggable"));
        WebElement droppable= driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).perform();
        WebElement confirmationMessage=driver.findElement(By.xpath(" //p[normalize-space()='Dropped!']"));
        if(confirmationMessage.isDisplayed())
            System.out.println("Test passed and object dropped to the target area");
        else
            System.out.println("Test failed and object is still in the current place");



        //Test for Resizable

        driver.get("https://jqueryui.com/");
        driver.findElement(By.linkText("Resizable")).click();
        jse.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        WebElement iframe2=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(iframe2);
        WebElement resizeArrow=driver.findElement
                (By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
        Point beforeResize=resizeArrow.getLocation();
        int beforeResized=beforeResize.getX();
        System.out.println("The size before change is: "+beforeResize.getX());
        Actions resizeAction=new Actions(driver);
        resizeAction.clickAndHold(resizeArrow).moveByOffset(90,60).release().perform();
        Thread.sleep(2000);
        Point afterResize=resizeArrow.getLocation();
        int afterResized=afterResize.getX();
        System.out.println("The size after changed is: "+afterResize.getX());
        if(afterResized>beforeResized)
            System.out.println("Test passed and object resized");
        else
            System.out.println("Test failed and object is still in the same location");

        driver.close();
        driver.quit();







    }
}
