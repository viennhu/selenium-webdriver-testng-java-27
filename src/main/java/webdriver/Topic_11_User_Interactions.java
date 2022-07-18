package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_11_User_Interactions {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
    Alert alert;
    Color color;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover1() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Hover2() {
        driver.get("https://www.myntra.com/");
        action.moveToElement(driver.findElement(By.cssSelector("a[data-group='women']"))).perform();
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[text()='Kurtas & Suits']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/women-kurtas-kurtis-suits");

    }

    @Test
    public void TC_03_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
        action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).release().perform();
        Assert.assertEquals(driver.findElements(By.className("ui-selected")).size(),12);
    }

    @Test
    public void TC_04_Click_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable li"));
        action.keyDown(Keys.CONTROL).perform();
        action.click(allNumbers.get(2)).click(allNumbers.get(3)).click(allNumbers.get(4)).perform();
        action.keyUp(Keys.CONTROL).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElements(By.className("ui-selected")).size(),3);
    }
    @Test
    public void TC_05_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()='Double click me']")));
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");
    }
    @Test
    public void TC_05_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.className("context-menu-one"))).perform();
        WebElement deleteButton = driver.findElement(By.className("context-menu-icon-delete"));
        action.moveToElement(deleteButton).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-delete.context-menu-hover")).isDisplayed());
        deleteButton.click();
        alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertFalse(driver.findElement(By.className("context-menu-icon-delete")).isDisplayed());
    }
    @Test
    public void TC_05_DragAndDrop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droptarget"));
        action.dragAndDrop(source,target).perform();
        Assert.assertEquals(target.getText(),"You did great!");
        Color targetColor = Color.fromString(driver.findElement(By.id("droptarget")).getCssValue("background-color"));
        Assert.assertEquals(targetColor.asHex(),"#03a9f4");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int generateRandomNumber () {
        Random rand = new Random();
        return rand.nextInt(99999);
    }
}
