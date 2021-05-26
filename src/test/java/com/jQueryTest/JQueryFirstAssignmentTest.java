package com.jQueryTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class JQueryFirstAssignmentTest {
    public static void main(String[] args)  {

        //1.Test for Draggable
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/Users/guzhanuerxilili/Downloads/FirstAssignment/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Draggable")).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");
        WebElement draggablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(draggablePage);
        WebElement source= driver.findElement(By.id("draggable"));
        Actions actions=new Actions(driver);
        Point beforeDrag=source.getLocation();
        int beforeMoved=beforeDrag.getX();
        actions.dragAndDropBy(source,120,70).perform();
        sleep(2);
        Point afterDrag= source.getLocation();
        int afterMoved= afterDrag.getX();
        if(afterMoved>beforeMoved)
            System.out.println("Test Passed and Element dragged around");
        else
            System.out.println("Test Failed and Element is still in the same location");



        // 2.Test for droppable

        driver.get("https://jqueryui.com/");
         sleep(1);
        driver.findElement(By.linkText("Droppable")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement droppablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(droppablePage);
        sleep(1);
        WebElement draggable= driver.findElement(By.id("draggable"));
        WebElement droppable= driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).perform();
        sleep(2);
        WebElement confirmationMessage=driver.findElement(By.xpath(" //p[normalize-space()='Dropped!']"));
        if(confirmationMessage.isDisplayed())
            System.out.println("Test passed and object dropped to the target area");
        else
            System.out.println("Test failed and object is still in the current place");



        // 3.Test for Resizable

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Resizable")).click();
        jse.executeScript("window.scrollBy(0,300)");
         sleep(1);
        WebElement resizablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(resizablePage);
        WebElement resizeArrow=driver.findElement
                (By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
        Point beforeResize=resizeArrow.getLocation();
        int beforeResized=beforeResize.getX();
        System.out.println("The size before change is: "+beforeResize.getX());
        Actions resizeAction=new Actions(driver);
        resizeAction.clickAndHold(resizeArrow).moveByOffset(90,60).release().perform();
        sleep(2);
        Point afterResize=resizeArrow.getLocation();
        int afterResized=afterResize.getX();
        System.out.println("The size after changed is: "+afterResize.getX());
        if(afterResized>beforeResized)
            System.out.println("Test passed and object resized");
        else
            System.out.println("Test failed and object is still in the same location");


        // 4.Test for single selectable

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Selectable")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(selectablePage);
        WebElement selectedItem=driver.findElement(By.xpath("//li[normalize-space()='Item 3']"));
        selectedItem.click();
        sleep(2);
        if(selectedItem.isDisplayed())
            System.out.println("The item selected and test passed");
        else
            System.out.println("Test failed");



        // 5.Test for select multiple items


        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Selectable")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectablePage2=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(selectablePage2);
        WebElement dragFrom=driver.findElement(By.xpath("//li[contains(text(),'Item 2')]"));
        WebElement dropTo= driver.findElement(By.xpath("//li[contains(text(),'Item 5')]"));
        Actions action=new Actions(driver);
        action.dragAndDrop(dragFrom,dropTo).release().build().perform();
        sleep(2);
        if(dragFrom.isEnabled())
            System.out.println("Test passed");
        else
            System.out.println("Test failed");

        // 5.Test for sortable

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Sortable")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement sortablePage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(sortablePage);
        WebElement sortFrom=driver.findElement(By.xpath("//li[contains(text(),'Item 1')]"));
        WebElement sortTo=driver.findElement(By.xpath("//li[contains(text(),'Item 5')]"));
        Actions sortable=new Actions(driver);
        sortable.clickAndHold(sortFrom).moveToElement(sortTo).build().perform();
        sleep(2);




        // 6.Test for accordion

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Accordion")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement accordionPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(accordionPage);
        sleep(1);
        WebElement expandWindow=driver.findElement(By.cssSelector("h3[id='ui-id-5']"));
        expandWindow.click();
        sleep(2);
        if(expandWindow.isDisplayed())
            System.out.println("Window expanded and Test Passed");
        else
            System.out.println("Test Failed");


        // 7.Test for Autocomplete


        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Autocomplete")).click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement autocompletePage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(autocompletePage);
        WebElement tagsField=driver.findElement(By.id("tags"));
        tagsField.sendKeys("J");
        sleep(2);
        WebElement selectedObj=driver.findElement(By.xpath("//ul/li/div[text()=\"Java\"]"));
        selectedObj.click();
        //WebElement selectedObj=driver.findElement(By.xpath("//div[@tabindex='-1'][@class='ui-menu-item-wrapper'][text()='Java']"));
        //selectedObj.click();
        // WebElement selectObj= driver.findElement(By.xpath("//div[text()='Java']//parent::li[@class='ui-menu-item']//preceding-sibling::li[@class='ui-menu-item']"));
        // selectObj.click();
        sleep(1);
        if(tagsField.isEnabled())
            System.out.println("Test Passed");
        else
            System.out.println("Test failed");


        // 8.Test for Button

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Button")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement buttonsPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(buttonsPage);
        sleep(1);
        WebElement button=driver.findElement(By.cssSelector("button[class='ui-button ui-corner-all ui-widget']"));
        button.click();


        // 9.Test for CheckBox Radio

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Checkboxradio")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement checkBoxPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(checkBoxPage);
        driver.findElement(By.cssSelector("label[class='ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-radio-label']")).click();
        sleep(1);
        driver.findElement(By.xpath("//label[normalize-space()='5 Star']")).click();
        sleep(1);
        driver.findElement(By.xpath("//label[normalize-space()='1 King']")).click();
        sleep(1);



        // 10.Test for Control Group
        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Controlgroup")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement ControlGroupPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(ControlGroupPage);
        WebElement dropdownList=driver.findElement(By.xpath("//span[@id='car-type-button']"));
        dropdownList.click();
        sleep(1);
        driver.findElement(By.xpath("//div[contains(text(),'Luxury')]")).click();
        sleep(1);
        driver.findElement(By.xpath("//label[@for='transmission-automatic']")).click();
        sleep(1);
        driver.findElement(By.xpath("//label[@for='insurance-v']")).click();
        sleep(1);
        driver.findElement(By.id("horizontal-spinner")).sendKeys("1");
        sleep(1);
        driver.findElement(By.xpath("//button[contains(text(),'Book Now!')]")).click();


        // 11.Test for Date picker

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Datepicker")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement datePickerPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(datePickerPage);
        WebElement datePickerBox=driver.findElement(By.id("datepicker"));
        datePickerBox.click();
        driver.findElement(By.xpath("//a[normalize-space()='23']")).click();
        sleep(2);
        if (datePickerBox.isDisplayed())
            System.out.println("Date selected and Test passed");
        else
            System.out.println("Test failed");



        // 12. Test for Dialog

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Dialog")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement dialogPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(dialogPage);
        WebElement dialogBox=driver.findElement(By.xpath("//span[text()='Basic dialog']"));
        Actions moveDialogBox=new Actions(driver);
        moveDialogBox.moveToElement(dialogBox,60,100);
        driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
        sleep(2);


        // 13.Test for Menu

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Menu")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement menuPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(menuPage);
        WebElement musicMenu=driver.findElement(By.xpath("//div[(text()='Music')]"));
        Actions menuActions=new Actions(driver);
        menuActions.moveToElement(musicMenu).build().perform();
        sleep(1);
        WebElement jazz=driver.findElement(By.xpath("//div[(text()='Jazz')]"));
        menuActions.moveToElement(jazz).build().perform();
        sleep(1);
        driver.findElement(By.xpath("//div[(text()='Modern')]")).click();



        // 14.Test for Select Menu

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Selectmenu")).click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectMenuPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(selectMenuPage);
        driver.findElement(By.xpath("//span[@id='speed-button']" +
                "//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']")).click();
        sleep(1);
        driver.findElement(By.xpath("//div/ul/li/div[text()='Fast']")).click();
        driver.findElement(By.xpath("//span[@id='files-button']" +
                "//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']")).click();
        sleep(1);
        WebElement fileBox=driver.findElement(By.xpath("//div[text()='ui.jQuery.js']"));
        fileBox.click();
        sleep(2);
        if(fileBox.isEnabled())
            System.out.println("Boxes were selected and Test Passed");
        else
            System.out.println("Test Failed");


        // 15.Test for Slider

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Slider")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement sliderPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(sliderPage);
        WebElement slideObj=driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
        Actions slide=new Actions(driver);
        slide.dragAndDropBy(slideObj,250,300).perform();
        sleep(2);


        // 16.Test for Spinner

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Spinner")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement spinnerPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(spinnerPage);
        sleep(1);
        WebElement upArrow=driver.findElement(By.cssSelector("span[class='ui-button-icon ui-icon ui-icon-triangle-1-n']"));
        Actions clickArrow=new Actions(driver);
        clickArrow.doubleClick(upArrow).perform();
        sleep(1);
        WebElement toggleButton=driver.findElement(By.xpath("//button[text()='Toggle widget']"));
        toggleButton.click();
        sleep(1);
        if(toggleButton.isEnabled())
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");



        driver.findElement(By.id("getvalue")).click();
        sleep(1);

        // 17.Test for Tabs

        driver.get("https://jqueryui.com/");
        sleep(1);
        driver.findElement(By.linkText("Tabs")).click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement tabsPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(tabsPage);
        sleep(1);
        WebElement content=driver.findElement(By.xpath("//a[text()='Proin dolor']"));
        content.click();
        sleep(1);
        if(content.isDisplayed())
            System.out.println("Content displayed and Test Passed");
        else
            System.out.println("Test Failed");


        // 18.Test for Tooltip

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
