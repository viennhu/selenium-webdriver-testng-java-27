package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_TC01_TC06_Login {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String firstName, lastName, emailAddress, password, fullName;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        firstName = "vinu02";
        lastName = "vinu03";
        emailAddress = "vinu" +generateRandomNumber() + "@gmail.com";
        password = "0123456";
        fullName = firstName + " " + lastName;
    }

    @Test
    public void TC_01_NoData() {
       driver.get("http://live.techpanda.org");
       driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
       driver.findElement(By.cssSelector("input[name='login[username]']")).clear();
       driver.findElement(By.cssSelector("input[title='Password']")).clear();
       driver.findElement(By.cssSelector("button[title='Login']")).click();
       String actualResult1 = driver.findElement(By.xpath("//input[@name='login[username]']/following-sibling::div[text()='This is a required field.']")).getText();
       Assert.assertEquals(actualResult1,"This is a required field.");
       String actualResult2 = driver.findElement(By.xpath("//input[@name='login[password]']/following-sibling::div[text()='This is a required field.']")).getText();
       Assert.assertEquals(actualResult2,"This is a required field.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("123@456.789");
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("1234567");
        driver.findElement(By.cssSelector("button[title='Login']")).click();
        driver.findElement(By.cssSelector(".validation-advice")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector(".validation-advice")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void TC_03_Password_less_than_6_characters() {
        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("vinu@gmail.com");
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("123");
        driver.findElement(By.cssSelector("button[title='Login']")).click();
        driver.findElement(By.cssSelector(".validation-advice")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector(".validation-advice")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Incorrect_email_and_password() {
        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("vinu@gmail.com");
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("1234567");
        driver.findElement(By.cssSelector("button[title='Login']")).click();
        driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
        Assert.assertEquals( driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(),"Invalid login or password.");
    }

    @Test
    public void TC_05_Create_New_Account() {
        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText();
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText(),"Thank you for registering with Main Website Store.");
        String contactInfo = driver.findElement(By.xpath("//h2[text()='Account Information']/parent::div//following-sibling::div//p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();


    }

    @Test
    public void TC_06_Invalid_PhoneNumber() {
        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Login']")).click();
        String contactInfo = driver.findElement(By.xpath("//h2[text()='Account Information']/parent::div//following-sibling::div//p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

    }
    @Test
    public void TC_01_UPPERCASE () {
        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector(".footer a[title='My Account']")).click();
        String actualResult = driver.findElement(By.xpath("//h2[text()='Already registered?']")).getText();
        Assert.assertEquals(actualResult,"ALREADY REGISTERED?");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateRandomNumber () {
        Random rand = new Random();
        return rand.nextInt(99999);
    }
}
