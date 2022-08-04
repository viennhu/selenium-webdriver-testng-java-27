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
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_19_Wait_P4_ExplicitWait_Mix_ImplicitWait {
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
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_KoSetImplicitWait_Explicit_ThamsoWebElement() {
        /*By loadingIcon = By.cssSelector("#loading");*/
        By helloWorldtext = By.xpath("//h4[text()='Hello World!']");
        /*driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
        explicitWait = new WebDriverWait(driver,5);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        //Hàm find Element bên dưới chạy độc lập nên cần có implicit wait, ở đây ko set implicit wait nên case này failed
        //Chạy hết timeout implicit wait là 10s ko tìm đc element nên failed
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#loading"))));
        Assert.assertTrue(driver.findElement(helloWorldtext).isDisplayed());
    }

    @Test
    public void TC_02_KoSetImplicitWait_Explicit_ThamsoBy() {
        By loadingIcon = By.cssSelector("#loading");
        By helloWorldtext = By.xpath("//h4[text()='Hello World!']");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /*driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
        explicitWait = new WebDriverWait(driver,5);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        //Hàm find Element bên dưới wrap trong invisibilityOfElementLocated, nên ko cần set Implicit wait mà vẫn passed.
        //Bởi vì nó nhận timeout của cả 2 explicit wait và implicit wait, implicit wait = 0, nó sẽ chạy hết timeout của explicit wait là 10s nên vẫn passed
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        Assert.assertTrue(driver.findElement(helloWorldtext).isDisplayed());
    }

    @Test
    public void TC_03_SetImplicit_vaExplicitWait_KoTimThayElement() {
        By helloWorldtext = By.xpath("//h4[text()='Hello World!']");
        explicitWait = new WebDriverWait(driver,20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        //Timeout của đoạn này sẽ chỉ là 10s vì hàm find element độc lập
        System.out.println("Start" + getTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Start1']"))));
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("End" + getTimeNow());
    }
    @Test
    public void TC_02_Case12() {
        By helloWorldtext = By.xpath("//h4[text()='Hello World1!']");
        explicitWait = new WebDriverWait(driver,5);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");
        //timeout của đoạn này sẽ là khoảng 20s-21s vì hàm find element này dc wrap trong hàm visibilityOfElementLocated
        //nó sẽ lấy timeout của cả hai cái implicit và explicit wait
        //implicit wait chạy trc khoảng 0.5-1s, rồi explicit mới chạy, hai cái chạy song song
        //test case dừng lại sau khi hết timeout của cả hai
        System.out.println("Start" + getTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//button[text()='Starteqweq1']")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End" + getTimeNow());
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

    public String getTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}
