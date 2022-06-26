package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Multiple_Browsers {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @Test
    public void chrome () {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kenh14.vn");
        driver.quit();
    }

    @Test
    public void firefox () {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://kenh14.vn");
        driver.quit();
    }
    @Test
    public void edge () {
        System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://kenh14.vn");
        driver.quit();
    }
}
