package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_19_Wait_P3_ExplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;
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
    public void TC_01_Practice_01() {
        By loadingIcon = By.cssSelector("#loading");
        By helloWorldtext = By.xpath("//h4[text()='Hello World!']");
        explicitWait = new WebDriverWait(driver,5);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        Assert.assertTrue(driver.findElement(helloWorldtext).isDisplayed());
    }

    @Test
    public void TC_02_Practice_02() {
        By loadingIcon = By.cssSelector("#loading");
        By helloWorldtext = By.xpath("//h4[text()='Hello World!']");
        explicitWait = new WebDriverWait(driver,5);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldtext));
        Assert.assertTrue(driver.findElement(helloWorldtext).isDisplayed());
    }

    @Test
    public void TC_03_Practice_03() {
        By dateTime = By.cssSelector("#ctl00_ContentPlaceholder1_Panel1");
        By selectedDateText = By.cssSelector("#ctl00_ContentPlaceholder1_Label1");
        By dateToSelect = By.cssSelector("td[title='Thursday, July 14, 2022']");
        By loadingIcon = By.cssSelector(".raDiv");
        By selectedDate = By.cssSelector("[class='rcSelected'][title='Thursday, July 14, 2022']");
        explicitWait = new WebDriverWait(driver,10);
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(dateTime));
        System.out.println(driver.findElement(selectedDateText).getText());
        driver.findElement(dateToSelect).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(selectedDate));
        selectedDateText = By.cssSelector("#ctl00_ContentPlaceholder1_Label1");
        Assert.assertEquals(driver.findElement(selectedDateText).getText(),"Thursday, July 14, 2022");
    }
    @Test
    public void TC_04_Practice_04() {
        String projectPath = System.getProperty("user.dir");
        String folderPathName = projectPath + File.separator + "picforuploading" + File.separator;
        String rabbit = "rabbit.jpg";
        String hippo = "hippo.jfif";
        String tiger = "tiger.jfif";
        String rabbitPath = folderPathName + rabbit;
        String hippotPath = folderPathName + hippo;
        String tigerPath = folderPathName + tiger;
        explicitWait = new WebDriverWait(driver,60);
        driver.get("https://gofile.io/uploadFiles");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary.btn-lg.mb-1.uploadButton")));
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(rabbitPath + "\n" + hippotPath + "\n" + tigerPath);
        List<WebElement> loadingBar = driver.findElements(By.cssSelector(".progress-bar.bg-primary"));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(loadingBar));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
        Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
        driver.findElement(By.cssSelector("#rowUploadSuccess-downloadPage")).click();
        String currentID = driver.getWindowHandle();
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if(!id.equals(currentID)) {
                driver.switchTo().window(id);
            }
        }
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + hippo + "']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + rabbit + "']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + tiger + "']")));
        WebElement hippoDownload = driver.findElement(By.xpath("//span[text()='" + hippo + "']//parent::a/parent::div//following-sibling::div[contains(@class,'col-md text-center')]//span[text()='Download']"));
        WebElement rabbitDownload = driver.findElement(By.xpath("//span[text()='" + rabbit + "']//parent::a/parent::div//following-sibling::div[contains(@class,'col-md text-center')]//span[text()='Download']"));
        WebElement tigerDownload = driver.findElement(By.xpath("//span[text()='" + tiger + "']//parent::a/parent::div//following-sibling::div[contains(@class,'col-md text-center')]//span[text()='Download']"));
        Assert.assertTrue(hippoDownload.isDisplayed());
        Assert.assertTrue(rabbitDownload.isDisplayed());
        Assert.assertTrue(tigerDownload.isDisplayed());
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
