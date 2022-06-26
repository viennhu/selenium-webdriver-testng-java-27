package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Textbox_Textarea_Practice_2 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String id, firstname, lastname, newFirstName, newLastname;
    WebElement firstNameTextBox, lastNameTextBox, idTextbox;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
    public void TC_01_AddEmployee() {
        firstname = "Nhu" + generateRandomNumber();
        lastname = "Le" + generateRandomNumber();
        id = "1" + generateRandomNumber();
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
        sleepInSecond(2);
        driver.findElement(By.name("firstName")).sendKeys(firstname);
        driver.findElement(By.name("lastName")).sendKeys(lastname);
        driver.findElement(By.name("employeeId")).clear();
        driver.findElement(By.name("employeeId")).sendKeys(id);
        driver.findElement(By.id("btnSave")).click();
        sleepInSecond(2);
        //Verify giá trị đúng với giá trị đã nhập
        setTextBoxValue();
        Assert.assertEquals(firstNameTextBox.getAttribute("value"),firstname);
        Assert.assertEquals(lastNameTextBox.getAttribute("value"),lastname);
        Assert.assertEquals(idTextbox.getAttribute("value"),id);
        Assert.assertFalse(firstNameTextBox.isEnabled());
        Assert.assertFalse(lastNameTextBox.isEnabled());
        Assert.assertFalse(idTextbox.isEnabled());
    }
    @Test
    public void TC_02_Edit() {
        newFirstName = "Nhu1" + generateRandomNumber();
        newLastname = "Le1" + generateRandomNumber();
        driver.findElement(By.cssSelector("input[value='Edit']")).click();
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(newFirstName);
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(newLastname);
        Assert.assertTrue(firstNameTextBox.isEnabled());
        Assert.assertTrue(lastNameTextBox.isEnabled());
        driver.findElement(By.cssSelector("input[value='Save']")).click();
        sleepInSecond(2);
        //Verify giá trị đúng với giá trị đã update
        setTextBoxValue();
        Assert.assertEquals(firstNameTextBox.getAttribute("value"),newFirstName);
        Assert.assertEquals(lastNameTextBox.getAttribute("value"),newLastname);
        Assert.assertFalse(firstNameTextBox.isEnabled());
        Assert.assertFalse(lastNameTextBox.isEnabled());
        Assert.assertFalse(idTextbox.isEnabled());
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
    private void setTextBoxValue() {
        firstNameTextBox = driver.findElement(By.cssSelector("input[title='First Name']"));
        lastNameTextBox = driver.findElement(By.cssSelector("input[title='Last Name']"));
        idTextbox = driver.findElement(By.id("personal_txtEmployeeId"));
    }
}
