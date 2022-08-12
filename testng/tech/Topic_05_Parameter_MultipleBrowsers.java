package tech;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Topic_05_Parameter_MultipleBrowsers {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        switch (browserName) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", projectPath + File.separator + "browserDrivers" + File.separator + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", projectPath + File.separator + "browserDrivers" + File.separator + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", projectPath + File.separator + "browserDrivers" + File.separator + "msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                break;
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Login()  {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.id("email")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
    }

}
