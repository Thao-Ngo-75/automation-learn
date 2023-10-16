package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Handle_Frame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_iFrame() {

		driver.get("https://skills.kynaenglish.vn/");
		
		//iFrame FB: check follower
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'//www.facebook.com')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lfloat']//div[2]")).getText(), "167K followers");
		
		//iFrame domain
		driver.switchTo().defaultContent();
		//iFrame csChat
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("thaongo");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("0987654321");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("div.overlay")).click();
		
		//iFrame domain
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> CourseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course : CourseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		
	}

	//@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("thaongo1234");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		//Switched qua trang netportal nên ko test được case này

	}

	@Test
	public void TC_03_() {

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 1000ms = 1s

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}