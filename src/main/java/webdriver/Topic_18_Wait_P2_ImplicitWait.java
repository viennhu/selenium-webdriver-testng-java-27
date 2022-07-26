package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_Wait_P2_ImplicitWait {
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
        driver.get("https://automationfc.github.io/dynamic-loading/");
    }

    @Test
    public void TC_01_Find_Element() {
        //Có duy nhất 1 element
        driver.findElement(By.xpath("//input[@name='email']"));
        //Có nhiều hơn 1 element
        driver.findElement(By.cssSelector("#pageFooterChildren a")).click();
    }

    @Test
    public void TC_02_FindElements() {
        int emailSize = driver.findElements(By.xpath("//input[@name='email']")).size();
        System.out.println(emailSize);
        int linkSize = driver.findElements(By.cssSelector("#pageFooterChildren a")).size();
        System.out.println(linkSize);
        int linkSize1 = driver.findElements(By.cssSelector("#pageFooterChildren a1")).size();
        System.out.println(linkSize1);
    }

    @Test
    public void TC_03_() {

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
