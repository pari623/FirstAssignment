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
        //initialize the browser
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Drag the object
        WebElement draggableLink=driver.findElement(By.linkText("Draggable"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                draggableLink);
        draggableLink.click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement draggablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(draggablePage);
        WebElement source= driver.findElement(By.id("draggable"));
        Actions actions=new Actions(driver);
        Point beforeDrag=source.getLocation();
        int beforeMoved=beforeDrag.getX();
        actions.dragAndDropBy(source,120,70).perform();
        sleep(2);
        //verify the element dragged
        Point afterDrag= source.getLocation();
        int afterMoved= afterDrag.getX();
        if(afterMoved>beforeMoved)
            System.out.println("Test Passed and Element dragged around");
        else
            System.out.println("Test Failed and Element is still in the same location");



        // 2.Test for droppable
          //initialize the browser
        driver.get("https://jqueryui.com/");
         sleep(1);
         //Drop the object from current place
        WebElement droppableLink=driver.findElement(By.linkText("Droppable"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                droppableLink);
        droppableLink.click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement droppablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(droppablePage);
        sleep(1);
        WebElement draggable= driver.findElement(By.id("draggable"));
        WebElement droppable= driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable,droppable).perform();
        sleep(2);
        //verification
        WebElement confirmationMessage=driver.findElement(By.xpath(" //p[normalize-space()='Dropped!']"));
        if(confirmationMessage.isDisplayed())
            System.out.println("Test passed and object dropped to the target area");
        else
            System.out.println("Test failed and object is still in the current place");



        // 3.Test for Resizable
         //initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Resize the onject
        WebElement resizableLink=driver.findElement(By.linkText("Resizable"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
               resizableLink);
        resizableLink.click();
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
        //Verification
        Point afterResize=resizeArrow.getLocation();
        int afterResized=afterResize.getX();
        System.out.println("The size after changed is: "+afterResize.getX());
        if(afterResized>beforeResized)
            System.out.println("Test passed and object resized");
        else
            System.out.println("Test failed and object is still in the same location");


        // 4.Test for single selectable
        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select for single object
        WebElement selectableLink=driver.findElement(By.linkText("Selectable"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                selectableLink);
        selectableLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectablePage=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(selectablePage);
        WebElement selectedItem=driver.findElement(By.xpath("//li[normalize-space()='Item 3']"));
        selectedItem.click();
        sleep(2);
        //verification
        if(selectedItem.isDisplayed())
            System.out.println("The item selected and test passed");
        else
            System.out.println("Test failed");



        // 5.Test for select multiple items

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select multiple objects
        WebElement selectableLink2=driver.findElement(By.linkText("Selectable"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
               selectableLink2);
        selectableLink2.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement selectablePage2=driver.findElement(By.cssSelector(" iframe[class='demo-frame']"));
        driver.switchTo().frame(selectablePage2);
        WebElement dragFrom=driver.findElement(By.xpath("//li[contains(text(),'Item 2')]"));
        WebElement dropTo= driver.findElement(By.xpath("//li[contains(text(),'Item 5')]"));
        Actions action=new Actions(driver);
        action.dragAndDrop(dragFrom,dropTo).release().build().perform();
        //Verification
        sleep(2);
        if(dragFrom.isEnabled())
            System.out.println("Test passed");
        else
            System.out.println("Test failed");

        // 5.Test for sortable
        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Sorting item on the list
        WebElement sortableLink=driver.findElement(By.linkText("Sortable"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                sortableLink);
        sortableLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement sortablePage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(sortablePage);
        WebElement sortFrom=driver.findElement(By.xpath("//li[contains(text(),'Item 1')]"));
        WebElement sortTo=driver.findElement(By.xpath("//li[contains(text(),'Item 5')]"));
        Actions sortable=new Actions(driver);
        sortable.clickAndHold(sortFrom).moveToElement(sortTo).build().perform();
        sleep(2);




        // 6.Test for accordion
        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Expand collapsed content
        WebElement accordionLink=driver.findElement(By.linkText("Accordion"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
               accordionLink);
        accordionLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement accordionPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(accordionPage);
        sleep(1);
        WebElement expandWindow=driver.findElement(By.cssSelector("h3[id='ui-id-5']"));
        expandWindow.click();
        sleep(2);
        //Verification
        if(expandWindow.isDisplayed())
            System.out.println("Window expanded and Test Passed");
        else
            System.out.println("Test Failed");


        // 7.Test for Autocomplete

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select provided suggestion
        WebElement autoComplete=driver.findElement(By.linkText("Autocomplete"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                autoComplete);
        autoComplete.click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement autocompletePage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(autocompletePage);
        WebElement tagsField=driver.findElement(By.id("tags"));
        tagsField.sendKeys("J");
        sleep(2);
        WebElement selectedObj=driver.findElement(By.xpath("//ul/li/div[text()=\"Java\"]"));
        selectedObj.click();
        sleep(1);
        //Verification
        if(tagsField.isEnabled())
            System.out.println("Test Passed");
        else
            System.out.println("Test failed");


        // 8.Test for Button

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Clickable buttons
        WebElement buttonLink=driver.findElement(By.linkText("Button"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                buttonLink);
        buttonLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement buttonsPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(buttonsPage);
        sleep(1);
        WebElement button=driver.findElement(By.cssSelector("button[class='ui-button ui-corner-all ui-widget']"));
        button.click();


        // 9.Test for CheckBox Radio

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select item by clicking checkbox radio
        WebElement checkBoxRadio=driver.findElement(By.linkText("Checkboxradio"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                checkBoxRadio);
        checkBoxRadio.click();
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

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select various criteria about a topic
        WebElement controlGroupLink=driver.findElement(By.linkText("Controlgroup"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                controlGroupLink);
        controlGroupLink.click();
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

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Selecting date by date picker
        WebElement datepickerLink=driver.findElement(By.linkText("Datepicker"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                datepickerLink);
        datepickerLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement datePickerPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(datePickerPage);
        WebElement datePickerBox=driver.findElement(By.id("datepicker"));
        datePickerBox.click();
        driver.findElement(By.xpath("//a[normalize-space()='23']")).click();
        sleep(2);
        //Verification
        if (datePickerBox.isDisplayed())
            System.out.println("Date selected and Test passed");
        else
            System.out.println("Test failed");



        // 12. Test for Dialog

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //move and close to dialog box
        WebElement dialogLink=driver.findElement(By.linkText("Dialog"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                dialogLink);
        dialogLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement dialogPage=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(dialogPage);
        WebElement dialogBox=driver.findElement(By.xpath("//span[text()='Basic dialog']"));
        Actions moveDialogBox=new Actions(driver);
        moveDialogBox.moveToElement(dialogBox,60,100);
        driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();
        sleep(2);


        // 13.Test for Menu

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select desired menu
        WebElement menuLink=driver.findElement(By.linkText("Menu"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                menuLink);
        menuLink.click();
        sleep(1);
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

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Select item on dropdown list
        WebElement selectMenuLink=driver.findElement(By.linkText("Selectmenu"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                selectMenuLink);
        selectMenuLink.click();
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
        //Verification
        if(fileBox.isEnabled())
            System.out.println("Boxes were selected and Test Passed");
        else
            System.out.println("Test Failed");


        // 15.Test for Slider

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Move the handle slider
        WebElement sliderLink=driver.findElement(By.linkText("Slider"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                sliderLink);
        sliderLink.click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement sliderPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(sliderPage);
        WebElement slideObj=driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
        Actions slide=new Actions(driver);
        slide.dragAndDropBy(slideObj,250,300).perform();
        sleep(2);


        // 16.Test for Spinner

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Use up/down arrows to select the desired value
        WebElement spinnerLink=driver.findElement(By.linkText("Spinner"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                spinnerLink);
        spinnerLink.click();
        sleep(1);
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
        //Verification
        if(toggleButton.isEnabled())
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");



        driver.findElement(By.id("getvalue")).click();
        sleep(1);

        // 17.Test for Tabs

        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Content separate by tabs
        WebElement tabsLink=driver.findElement(By.linkText("Tabs"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                tabsLink);
        tabsLink.click();
        jse.executeScript("window.scrollBy(0,300)");
        WebElement tabsPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(tabsPage);
        sleep(1);
        WebElement content=driver.findElement(By.xpath("//a[text()='Proin dolor']"));
        content.click();
        sleep(1);
        //Verification
        if(content.isDisplayed())
            System.out.println("Content displayed and Test Passed");
        else
            System.out.println("Test Failed");


        // 18.Test for Tooltip
        //Initialize the browser
        driver.get("https://jqueryui.com/");
        sleep(1);
        //Using customizable tooltip
        WebElement tooltipLink=driver.findElement(By.linkText("Tooltip"));
        jse.executeScript("window.scrollBy(0,300)");
        sleep(1);
        jse.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 3px solid blue;');",
                tooltipLink);
        tooltipLink.click();
        sleep(1);
        jse.executeScript("window.scrollBy(0,300)");
        WebElement tooltipPage=driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(tooltipPage);
        WebElement ageTextBox=driver.findElement(By.id("age"));
        ageTextBox.click();
        sleep(2);
        //Verification
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
