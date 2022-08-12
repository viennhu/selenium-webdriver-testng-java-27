package Listener;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Listeners(ListenerTestNG.class)
public class Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String logInURL, email, userID, password;
    JavascriptExecutor jsExecutor;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        logInURL = "https://demo.guru99.com/v4/";
        email = "vivian" + generateRandomNumber() + "@gmail.com";
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register() {
        driver.get(logInURL);
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
        Assert.assertFalse(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
    }
    public int generateRandomNumber () {
        Random rand = new Random();
        return rand.nextInt(99999);
    }
    public  WebDriver getDriver () {
       return this.driver;
    }
}
