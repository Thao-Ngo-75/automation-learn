package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebBrowser {
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Webelement_FinElement() {
		//Có thể dùng lại nhiều lần
		WebElement emailid = driver.findElement(By.xpath("//input[@id='email']"));
		emailid.click();
		emailid.sendKeys("abc@gmail.com");
		
		
		//Find Element số nhiều
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		driver.getCurrentUrl();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "URL cua trang khac");
		
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("chứa text full hoặc not full"));
		
		
		driver.getWindowHandle();
		String loginwindowID = driver.getWindowHandle();
		
		driver.getWindowHandles();
		Set<String> allIDs = driver.getWindowHandles();
		
		driver.manage();
		Options opt = driver.manage();
		opt.getClass();
		opt.timeouts();
		Timeouts time = opt.timeouts();
		time.implicitlyWait(5, TimeUnit.SECONDS);
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		opt.window();
		Window window = opt.window();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		nav.to(URL);
		
		driver.switchTo();
		TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame();
		tar.window();
	}

	@Test
	public void TC_02_Logo() {
		
	}

	@Test
	public void TC_03_Form() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}