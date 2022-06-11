package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_TC01_TC06 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NoData() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtFirstname-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("nhule");
        driver.findElement(By.id("txtEmail")).sendKeys("nhule@12@32.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("nhule@12@32.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0334883488");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("nhule");
        driver.findElement(By.id("txtEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("vivian2801@protonmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0334883488");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Password_Less_Than_6Chars() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("nhule");
        driver.findElement(By.id("txtEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345");
        driver.findElement(By.id("txtPhone")).sendKeys("0334883488");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("nhule");
        driver.findElement(By.id("txtEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0334883488");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Invalid_PhoneNumber() {
        //Input Phone Number less than 11 characters
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("nhule");
        driver.findElement(By.id("txtEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("033488348");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Input Phone Number not starting with 0
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("nhule");
        driver.findElement(By.id("txtEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("vivian2801@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("12345678910");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
