package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Radio_Checkbox {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());
        driver.findElement(By.cssSelector("#login_username")).sendKeys("viennhu.94@gmail.com");
        driver.findElement(By.cssSelector("#login_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());
        Color loginButtonBackgroundColor = Color.fromString(driver.findElement(By.cssSelector(".fhs-btn-login")).getCssValue("background-color"));
        assert loginButtonBackgroundColor.asHex().toUpperCase().equals("#C92127");
    }

    @Test
    public void TC_02_Default_Radio_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        WebElement anemia = driver.findElement(By.cssSelector("input[value='Anemia']"));
        WebElement asthma = driver.findElement(By.cssSelector("input[value='Asthma']"));
        WebElement days = driver.findElement(By.cssSelector("input[value='1-2 days']"));
        WebElement looseDiet = driver.findElement(By.cssSelector("input[value='I have a loose diet']"));
        WebElement caffein = driver.findElement(By.cssSelector("input[value='I don\\'t use caffeine']"));
        //Select
        anemia.click();
        asthma.click();
        days.click();
        looseDiet.click();
        sleepInSecond(1);
        Assert.assertTrue(anemia.isSelected());
        Assert.assertTrue(asthma.isSelected());
        Assert.assertTrue(days.isSelected());
        Assert.assertTrue(looseDiet.isSelected());
        Assert.assertFalse(caffein.isSelected());
        //Deselect
        anemia.click();
        days.click();
        sleepInSecond(1);
        Assert.assertFalse(anemia.isSelected());
        Assert.assertTrue(days.isSelected());
        //Select all
        List<WebElement> checkboxAll = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxAll) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : checkboxAll) {
            Assert.assertTrue(checkbox.isSelected());
        }
        //Deselect all
        for (WebElement checkbox : checkboxAll) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : checkboxAll) {
            Assert.assertFalse(checkbox.isSelected());
        }
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
