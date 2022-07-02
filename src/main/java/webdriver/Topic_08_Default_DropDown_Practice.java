package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Default_DropDown_Practice {
    WebDriver driver;
    Select select;
    String projectPath = System.getProperty("user.dir");
    String firstName, lastName, day, month, year, email, company, password;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
        firstName = "Nhu";
        lastName = "Le";
        day = "20";
        month = "January";
        year = "1994";
        email = "vinu" + generateRandomNumber() + "@gmail.com";
        company = "Cong ty ACB";
        password = "1234567890";
    }

    @Test
    public void TC_01_() {
        //Action
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(32,select.getOptions().size());
        select.selectByVisibleText(day);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(13,select.getOptions().size());
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(112,select.getOptions().size());
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(company);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.className("result")).getText(),"Your registration completed");
        driver.findElement(By.className("ico-account")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),email);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),company);
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
