package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Custom_Dropdown_Practice {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,15);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        selectValueInCusTomDropdown("span#number-button",".ui-selectmenu-menu>ul div","18");
        Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(),"18");
        selectValueInCusTomDropdown("span#number-button",".ui-selectmenu-menu>ul div","11");
        Assert.assertEquals(driver.findElement(By.cssSelector("#number-button .ui-selectmenu-text")).getText(),"11");
    }

    @Test
    public void TC_02_ReactJs() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectValueInCusTomDropdown("div[role='listbox']","div[role='option'] span","Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[role='listbox']>div")).getText(),"Elliot Fu");
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns");
        selectValueInCusTomDropdown(".dropdown-toggle",".dropdown-menu>li>a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector(".dropdown-toggle")).getText(),"First Option");
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        enterValueinDropdown("input.search","div[role='listbox']>div>span","Andorra");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[role='combobox']>div")).getText(),"Andorra");
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

    public void selectValueInCusTomDropdown (String parentLocator, String childLocator, String expectedValue) {
        driver.findElement(By.cssSelector(parentLocator)).click();
        sleepInSecond(1);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> dropDownAllValue = driver.findElements(By.cssSelector(childLocator));
        for(WebElement value : dropDownAllValue) {
            value.getText();
            System.out.println("Value=" + value.getText());
            if(value.getText().equals(expectedValue)){
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);",value);
                value.click();
                break;
            }
        }
    }
    public void enterValueinDropdown (String parentLocator, String childLocator, String expectedValue) {
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedValue);
        sleepInSecond(1);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> dropDownAllValue = driver.findElements(By.cssSelector(childLocator));
        for (WebElement value : dropDownAllValue) {
            value.getText();
            System.out.println("Value=" + value.getText());
            if (value.getText().equals(expectedValue)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);",value);
                value.click();
                break;
            }
        }
    }
}
