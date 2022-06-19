//package webdriver;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class Topic_06_WebBrowser_and_Element_Method {
//    WebDriver driver;
//    Actions actions;
//    String projectPath = System.getProperty("user.dir");
//
//    @BeforeClass
//    public void beforeClass () {
//        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//        driver = new ChromeDriver();
//    }
//
//    @Test
//    public void TC_Browser_Method () {
//        //1 tab: close browser
//        //>1 tab: close tab đang active
//        driver.close();
//        //Đóng browser, không quan tâm có bao nhiêu tab
//        driver.quit();
//        //Mở URL
//        driver.get();
//        //Tìm element
//        driver.findElement();
//        driver.findElements();
//        //Lấy URL của page hiện tại
//        driver.getCurrentUrl();
//        //Lấy source code của page hiện tại
//        driver.getPageSource();
//        //Xử lý với window và tab
//        //Lấy ra ID của tab đang active
//        driver.getWindowHandle();
//        //Lấy ra ID của tất cả các tabs
//        driver.getWindowHandles();
//        //Cookie, lưu lại phiên đăng nhập/session/dữ liệu duyệt web...
//        driver.manage().deleteAllCookies();
//        //Framework - logs
//        driver.manage().logs();
//        //Chờ cho find Element/ find Elements
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.HOURS);
//        //Chờ Page load thành công
//        driver.manage().timeouts().pageLoadTimeout();
//        //Chờ cho 1 đoạn code JS được thực hiện thành công
//        driver.manage().timeouts().setScriptTimeout();
//        //Fullscreen window
//        driver.manage().window().fullscreen();
//        //Maximize cửa sổ
//        driver.manage().window().maximize();
//        //Framework
//        driver.switchTo().alert();
//        driver.switchTo().frame();
//        driver.switchTo().window();
//
//    }
//    @Test
//    public void Web_Element () {
//        driver.get("http://live.techpanda.org");
//        //Thao tác 1 lần lên element: không cần khai báo biến
//        driver.findElements(By.xpath(""));
//        //Thao tác hơn 1 lần lên element: nên khai báo biến
//        WebElement element = driver.findElement(By.cssSelector("a[title='Create an Account']"));
//        element.sendKeys();
//        //Xóa dữ liệu Textbox/Textarea/Editabe drop-down
//        element.clear();
//        //Nhập dữ liệu:
//        element.sendKeys();
//        //Lấy ra giá tri của attribute
//        driver.findElement(By.id()).getAttribute("");
//        //Verify trực tiếp: không cần khai báo biến
//        Assert.assertEquals(driver.findElement(By.cssSelector("a[title='Create an Account']")).getAttribute("id"), "abcd");
//        //Dùng nhiều lần: phải khai báo biến
//        String idValue = driver.findElement(By.cssSelector("a[title='Create an Account']")).getAttribute("id");
//        Assert.assertEquals(idValue,"abcd");
//        Assert.assertEquals(idValue,"zyz");
//
//        //GUI: font/size/color,etc.
//        element.getCssValue("height");
//        //Framework: add screenshot to Report HTML
//        element.getScreenshotAs(OutputType.BASE64);
//        //Lấy ra text của element hiện tại hoặc của element con bên trong
//        element.getText();
//        //Verify tương đối
//        String text = driver.findElement(By.id()).getText();
//        Assert.assertTrue(text.contains("abcd"));
//        //Mong muốn 1 element hiển thị hay không hiển thị (textbox, textarea, radio, button,...)
//        element.isDisplayed();
//        //Mong muốn 1 element có thể thao tác lên được
//        element.isEnabled();
//        //Mong muốn 1 element đã được chọn hay chưa (checkbox, radio button, drop down)
//        element.isSelected();
//        //Click vào element
//        element.click();
//        //scroll
//        actions.clickAndHold().moveToElement();
//
//
//
//    }
//
//
//}
