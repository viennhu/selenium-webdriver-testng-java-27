package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_12_PopUp {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_PopUp_InDorm() {
        driver.get("https://ngoaingu24h.vn/");
        sleepInSecond(5);
        WebElement popup = driver.findElement(By.cssSelector("div[id='modal-login-v1'][class='modal fade']"));
        Assert.assertFalse(popup.isDisplayed());
        driver.findElement(By.className("login_")).click();
        sleepInSecond(5);
        Assert.assertTrue(popup.isDisplayed());
        driver.findElement(By.cssSelector("div[class='modal fade in'][style] input[name='account-input']")).sendKeys("dbsb@gmail.com");
        driver.findElement(By.cssSelector("div[class='modal fade in'][style] input[name='password-input']")).sendKeys("dbsb@gmail.com");
        driver.findElement(By.cssSelector("button[class='btn-v1 btn-login-v1 buttonLoading'][data-text='Đăng nhập']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='modal fade in'][style] .row.error-login-panel")).getText(),"Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[class='modal fade in'][style] button[class='close']")).click();
        Assert.assertFalse(popup.isDisplayed());
    }
    @Test
    public void TC_02_Fixed_PopUp_NotInDorm() {
        driver.get("https://tiki.vn/");
        List<WebElement> popup = driver.findElements(By.className("ReactModal__Content"));
        Assert.assertEquals(popup.size(),0);
        driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
        sleepInSecond(2);
        popup = driver.findElements(By.className("ReactModal__Content"));
        Assert.assertEquals(popup.size(),1);
        Assert.assertTrue(popup.get(0).isDisplayed());
        driver.findElement(By.className("close-img")).click();
        sleepInSecond(2);
        popup = driver.findElements(By.className("ReactModal__Content"));
        Assert.assertEquals(popup.size(),0);
    }

    @Test
    public void TC_03_Random_PopUp_InDorm() {
        driver.get("https://kmplayer.com/home");
        sleepInSecond(5);
        WebElement popup = driver.findElement(By.className("pop-container"));
        if (popup.isDisplayed()) {
            jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("area#btn-r")));
            sleepInSecond(1);
        }
        driver.findElement(By.xpath("//a[text()='MOBILE ']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://kmplayer.com/mobile");
    }

    @Test
    public void TC_04_Random_PopUp_NotInDorm() {
        driver.get("https://jtexpress.vn/vi");
        List<WebElement> popup = driver.findElements(By.cssSelector(".sourceList.popupUrl"));
        if (popup.size() > 0) {
            driver.findElement(By.cssSelector(".w-auto.mx-auto.relative button")).click();
            sleepInSecond(2);
            popup = driver.findElements(By.cssSelector(".sourceList.popupUrl"));
            Assert.assertEquals(popup.size(), 0);
        }
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[name='billcode']")));
        driver.findElement(By.cssSelector("input[name='billcode']")).sendKeys("00004444999924");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(), 'Tìm kiếm')]")));
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector(".tab-content span")).getText().contains("Không tìm thấy dữ liệu về vận đơn. Bạn vui lòng kiểm tra lại mã vận đơn !"));
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
