package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement_Practice {
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
    public void TC_01_IsDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement email = driver.findElement(By.id("mail"));
        WebElement ageUnder18 = driver.findElement(By.id("under_18"));
        WebElement education = driver.findElement(By.id("edu"));
        WebElement user5Name = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

        if (email.isDisplayed()) {
            email.sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        } else {
            System.out.println("Email is not displayed");
        }

        if (ageUnder18.isDisplayed()) {
            ageUnder18.click();
            System.out.println("ageUnder18 is displayed");
        } else {
            System.out.println("ageUnder18 is not displayed");
        }

        if (education.isDisplayed()) {
            education.sendKeys("Automation Testing");
            System.out.println("Education is displayed");
        } else {
            System.out.println("Education is not displayed");
        }

        if (user5Name.isDisplayed()) {
            System.out.println("User5 is displayed");
        } else {
            System.out.println("User5 is not displayed");
        }
    }

    @Test
    public void TC_02_Enabled_Disabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement email = driver.findElement(By.id("mail"));
        WebElement ageUnder18 = driver.findElement(By.id("under_18"));
        WebElement education = driver.findElement(By.id("edu"));
        WebElement jobRole1 = driver.findElement(By.id("job1"));
        WebElement jobRole2 = driver.findElement(By.id("job2"));
        WebElement slider01 = driver.findElement(By.id("slider-1"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement disabledRadio = driver.findElement(By.id("radio-disabled"));
        WebElement bio = driver.findElement(By.id("bio"));
        WebElement jobRole3 = driver.findElement(By.id("job3"));
        WebElement disabledCheckbox = driver.findElement(By.id("check-disbaled"));
        WebElement slider02 = driver.findElement(By.id("slider-2"));

        //Enabled
        if (email.isEnabled()) {
            System.out.println("Email is enabled");
        } else {
            System.out.println("Email is disabled");
        }
        if (ageUnder18.isEnabled()) {
            System.out.println("ageUnder18 is enabled");
        } else {
            System.out.println("ageUnder18 is disabled");
        }
        if (education.isEnabled()) {
            System.out.println("Education is enabled");
        } else {
            System.out.println("Education is disabled");
        }
        if (jobRole1.isEnabled()) {
            System.out.println("Jobrole1 is enabled");
        } else {
            System.out.println("Jobrole1 is disabled");
        }
        if (jobRole2.isEnabled()) {
            System.out.println("Jobrole2 is enabled");
        } else {
            System.out.println("Jobrole2 is disabled");
        }
        if (slider01.isEnabled()) {
            System.out.println("Slider01 is enabled");
        } else {
            System.out.println("Slider01 is disabled");
        }

        //Disabled
        if (password.isEnabled()) {
            System.out.println("Password is enabled");
        } else {
            System.out.println("Password is disabled");
        }
        if (disabledRadio.isEnabled()) {
            System.out.println("Button is enabled");
        } else {
            System.out.println("Button is disabled");
        }
        if (bio.isEnabled()) {
            System.out.println("Bio is enabled");
        } else {
            System.out.println("Bio is disabled");
        }
        if (jobRole3.isEnabled()) {
            System.out.println("Job3 is enabled");
        } else {
            System.out.println("Job3 is disabled");
        }
        if (disabledCheckbox.isEnabled()) {
            System.out.println("Checkbox is enabled");
        } else {
            System.out.println("Checkbox is disabled");
        }
        if (slider02.isEnabled()) {
            System.out.println("Slider 02 is enabled");
        } else {
            System.out.println("Slider 02 is disabled");
        }
    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement ageUnder18 = driver.findElement(By.id("under_18"));
        WebElement javaCheckbox = driver.findElement(By.id("java"));
        ageUnder18.click();
        javaCheckbox.click();
        Assert.assertTrue(ageUnder18.isSelected());
        Assert.assertTrue(javaCheckbox.isSelected());
        javaCheckbox.click();
        Assert.assertFalse(javaCheckbox.isSelected());
    }

    @Test
    public void TC_03_ChimpMail() {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement password = driver.findElement(By.id("new_password"));

        driver.findElement(By.id("email")).sendKeys("viennhu94@gmail.com");
        driver.findElement(By.id("new_username")).click();
        password.sendKeys("vinu");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='One lowercase character']//parent::li[@class='lowercase-char completed']")).isDisplayed());
        password.clear();
        password.sendKeys("VINU");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='One uppercase character']//parent::li[@class='uppercase-char completed']")).isDisplayed());
        password.clear();
        password.sendKeys("$$$");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='One special character']//parent::li[@class='special-char completed']")).isDisplayed());
        password.clear();
        password.sendKeys("123");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='One number']//parent::li[@class='number-char completed']")).isDisplayed());
        password.clear();
        password.sendKeys("aaaaaaaaaaaaa");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='8 characters minimum']//parent::li[@class='8-char completed']")).isDisplayed());
        password.clear();
        password.sendKeys("Vinu213$$$");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//h4[\"text()=Your password is secure and you're all set!\"]")).isDisplayed());
        driver.findElement(By.cssSelector("input[name='marketing_newsletter']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='marketing_newsletter']")).isSelected());
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
  }
