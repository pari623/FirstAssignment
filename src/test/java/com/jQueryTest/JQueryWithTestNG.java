package com.jQueryTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class JQueryWithTestNG {

    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver");
        ChromeOptions chrome=new ChromeOptions();
        driver=new ChromeDriver(chrome);
        chrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://jqueryui.com/");
    }

    @Test(priority = 1)
    public void draggableTest()
    {
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
        actions.dragAndDropBy(source,120,70).perform();
        Assert.assertTrue(source.isDisplayed());

    }

    @Test(priority = 2)
    public void droppbale()
    {

        WebElement droppableLink=driver.findElement(By.linkText("Droppable"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
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
        Actions actions=new Actions(driver);
        actions.dragAndDrop(draggable,droppable).perform();
        sleep(2);
        WebElement confirmationMessage=driver.findElement(By.xpath(" //p[normalize-space()='Dropped!']"));
        Assert.assertTrue(confirmationMessage.isDisplayed());
    }

    @Test(priority = 3)
    public void resizable()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Actions resizeAction=new Actions(driver);
        resizeAction.clickAndHold(resizeArrow).moveByOffset(90,60).release().perform();
        Assert.assertTrue(resizeArrow.isEnabled());
    }

    @Test(priority = 4)
    public void selectSingleObject()
    {
        WebElement selectableLink=driver.findElement(By.linkText("Selectable"));
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(selectedItem.isDisplayed());
    }

    @Test(priority = 5)
    public void selectMultipleObject()
    {
        WebElement selectableLink2=driver.findElement(By.linkText("Selectable"));
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(dragFrom.isDisplayed());
    }

    @Test(priority = 6)
    public void sortable()
    {
        WebElement sortableLink=driver.findElement(By.linkText("Sortable"));
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(sortFrom.isEnabled());
    }

    @Test(priority = 7)
    public void accordion()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(expandWindow.isDisplayed());
    }

    @Test(priority = 8)
    public void autoComplete()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(selectedObj.isEnabled());
    }

    @Test(priority = 9)
    public void button()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(button.isEnabled());
    }

    @Test(priority = 10)
    public void checkBoxRadio()
    {

        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        WebElement checkBox=driver.findElement(By.xpath("//label[normalize-space()='1 King']"));
        checkBox.click();
        sleep(1);
        Assert.assertTrue(checkBox.isDisplayed());
    }

    @Test(priority = 11)
    public void controlGroup()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        WebElement controlGroupLink=driver.findElement(By.linkText("Controlgroup"));
        jse.executeScript("window.scrollBy(0,500)");
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
        WebElement bookNOwLink=driver.findElement(By.xpath("//button[contains(text(),'Book Now!')]"));
        bookNOwLink.click();
        Assert.assertTrue(bookNOwLink.isEnabled());
    }

    @Test(priority = 12)
    public void datePicker()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(datePickerBox.isDisplayed());

    }

    @Test(priority = 13)
    public void dialog()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(dialogBox.isEnabled());

    }

    @Test(priority = 14)
    public void menu()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(jazz.isDisplayed());

    }

    @Test(priority = 15)
    public void selectMenu()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(fileBox.isEnabled());

    }

    @Test(priority = 16)
    public void slider()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(slideObj.isEnabled());

    }

    @Test(priority = 17)
    public void spinner()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(toggleButton.isEnabled());

    }

    @Test(priority = 18)
    public void tabs()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(content.isDisplayed());

    }

    @Test(priority = 19 )
    public void toolTip()
    {
        JavascriptExecutor jse=(JavascriptExecutor) driver;
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
        Assert.assertTrue(ageTextBox.isDisplayed());

    }


    @AfterMethod
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }


    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
