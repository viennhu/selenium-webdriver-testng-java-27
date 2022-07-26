package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_17_Wait_P1_Element_Coditions_Status {
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
        explicitWait = new WebDriverWait (driver,15);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://facebook.com");
    }

    @Test
    public void TC_01_Visible() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email")));
    }

    @Test
    public void TC_02_InvisibleInDorm() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_03_InvisibleNotInDorm() {
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(2);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    }

    @Test
    public void TC_02_Presence() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email")));
    }

    @Test
    public void TC_02_Staleness() {
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        explicitWait.until(ExpectedConditions.stalenessOf(emailTextbox));
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
