package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_16_UploadFile {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    //Image name
    String rabbit = "rabbit.jpg";
    String hippo = "hippo.jfif";
    String tiger = "tiger.jfif";

    //Folder Path Name
    String folderPathName = projectPath + File.separator + "picforuploading" + File.separator;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + File.separator + "browserDrivers" + File.separator + "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_UploadFile_SendKeys_1FileAtATime() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By addFilesButton = By.cssSelector(".fileinput-button input[type='file']");
        driver.findElement(addFilesButton).sendKeys(folderPathName + rabbit);
        driver.findElement(addFilesButton).sendKeys(folderPathName + tiger);
        driver.findElement(addFilesButton).sendKeys(folderPathName + hippo);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + rabbit + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + tiger + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hippo + "']")).isDisplayed());
        List<WebElement> allStartButtons = driver.findElements(By.cssSelector("tbody button.start"));
        for (WebElement button : allStartButtons) {
            button.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + rabbit + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + tiger + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hippo + "']")).isDisplayed());
    }

    @Test
    public void TC_02_UploadFile_Multiple_Sendkeys() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By addFilesButton = By.cssSelector(".fileinput-button input[type='file']");
        driver.findElement(addFilesButton).sendKeys(folderPathName + rabbit + "\n" + folderPathName + tiger + "\n" + folderPathName + hippo);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + rabbit + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + tiger + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hippo + "']")).isDisplayed());
        List<WebElement> allStartButtons = driver.findElements(By.cssSelector("tbody button.start"));
        for (WebElement button : allStartButtons) {
            button.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + rabbit + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + tiger + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hippo + "']")).isDisplayed());
    }

    @Test
    public void TC_03_() {

    }

    @AfterClass
    public void afterClass() {
       // driver.quit();
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
