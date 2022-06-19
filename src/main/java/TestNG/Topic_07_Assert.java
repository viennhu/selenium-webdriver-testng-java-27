package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Assert {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass () {
    System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://facebook.com");
    }

    @Test
    public void TC_01_Assert () {
        //Kiểm tra dữ liệu mình mong muốn là đúng
        Assert.assertTrue(driver.findElement(By.id("e")).isDisplayed());

        //Kiểm tra dữ liệu mình mong muốn là sai
        Assert.assertFalse(driver.findElement(By.id("e")).isDisplayed());

        //Kiểm tra dữ liệu mình mong muốn với dữ liệu thực tế bằng nhau
        Assert.assertEquals(driver.findElement(By.id("e")),"abcd");
    }
}
