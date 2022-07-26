package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Assert {
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
