package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_13_Frame_Iframe {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("MAC OS")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Iframe_Kyna() {
        driver.get("https://kyna.vn/");
        driver.switchTo().frame(driver.findElement(By.cssSelector(".fanpage iframe")));
        String facebookLikes = driver.findElement(By.className("_1drq")).getText();
        Assert.assertEquals(facebookLikes,"166K likes");
        driver.switchTo().defaultContent();
        sleepInSecond(1);
        driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
        sleepInSecond(1);
        driver.findElement(By.className("border_overlay")).click();
        sleepInSecond(1);
        driver.findElement(By.className("input_name")).sendKeys("nunu");
        driver.findElement(By.className("input_phone")).sendKeys("999999333");
        new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.switchTo().defaultContent();
        driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
        driver.findElement(By.className("search-button")).click();
        sleepInSecond(2);
        List<WebElement> allTitles = driver.findElements(By.cssSelector(".content h4"));
        for (WebElement title : allTitles) {
            Assert.assertTrue(title.getText().contains("Excel"));
        }
    }

    @Test
    public void TC_02_Frame_Bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        sleepInSecond(2);
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
        sleepInSecond(2);
        driver.findElement(By.name("fldLoginUserId")).sendKeys("nunudnnunu");
        driver.findElement(By.className("login-btn")).click();
        sleepInSecond(1);
        WebElement password = driver.findElement(By.id("fldPasswordDispId"));
        Assert.assertTrue(password.isDisplayed());
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
