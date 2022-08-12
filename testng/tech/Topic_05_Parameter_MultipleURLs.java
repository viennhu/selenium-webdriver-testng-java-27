package tech;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Topic_05_Parameter_MultipleURLs {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", projectPath + File.separator + "browserDrivers" + File.separator + "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Parameters ("serverName")
    @Test
    public void TC_01_Login(String serverName)  {
        serverName = multipleEnvs(serverName);
        driver.get("http://" + serverName + "/index.php/customer/account/login/");
        driver.findElement(By.id("email")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
    }
    public String multipleEnvs (String serverName) {
        switch (serverName) {
            case "DEV":
                serverName = "live.techpanda.org";
                break;
            case "QA":
                serverName = "qa.live.techpanda.org";
                break;
            default:
                System.out.println("Server name is not valid");
                break;
        }
        return serverName;
    }
}
