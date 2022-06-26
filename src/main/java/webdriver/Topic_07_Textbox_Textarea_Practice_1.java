package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Textbox_Textarea_Practice_1 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String logInURL, email, userID, password;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        logInURL = "https://demo.guru99.com/v4/";
        email = "vivian" + generateRandomNumber() + "@gmail.com";
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(logInURL);
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Log_in() {
        driver.navigate().back();
        driver.navigate().back();
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();
        driver.findElement(By.cssSelector("tr.heading3>td")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(),"Manger Id : " + userID);
    }

    @Test
    public void TC_03_Create_New_Account() {
        String customerName, gender, dobInput, dobOutput, addressInput, addressOutput, city, state, pin, mobile, emailValue, passwordValue;
        customerName = "nunununu";
        gender = "female";
        dobInput = "01/28/1994";
        dobOutput = "1994-01-28";
        addressInput = "6 Nguyen Lan\nHorua\nHanoi";
        addressOutput = "6 Nguyen Lan Horua Hanoi";
        city = "Hanoi";
        state = "Phuong Liet";
        pin = "280194";
        mobile = "0334883488";
        emailValue = "vinu" +generateRandomNumber() + "@gmail.com";
        passwordValue = "12345678";
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        //Input
        driver.findElement(By.name("name")).sendKeys(customerName);
        driver.findElement(By.cssSelector("input[value='f']")).click();
        WebElement dobTextbox = driver.findElement(By.id("dob"));
        jsExecutor.executeScript("arguments[0].removeAttribute('type')",dobTextbox);
        sleepInSecond(2);
        dobTextbox.sendKeys(dobInput);
        driver.findElement(By.name("addr")).sendKeys(addressInput);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pin);
        driver.findElement(By.name("telephoneno")).sendKeys(mobile);
        driver.findElement(By.name("emailid")).sendKeys(emailValue);
        driver.findElement(By.name("password")).sendKeys(passwordValue);
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        sleepInSecond(4);

        //Verify output
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(),customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText(),gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),dobOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),addressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(),city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),mobile);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),emailValue);
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
