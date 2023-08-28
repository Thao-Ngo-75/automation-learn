package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
//	public void TC_01_ID() {
//		driver.findElement(By.id("Company")).sendKeys("Tech Avenue");
//	}
//
//	@Test
//	public void TC_02_Class() {
//		driver.findElement(By.name("q")).sendKeys("abc");
//	}
//
//	@Test
//	public void TC_03_linktext() {
//		driver.findElement(By.linkText("Search")).click();
//	}
	public void TC_04_Css() {
//		driver.get("https://demo.nopcommerce.com/register");
		//cach 1: combine thẻ + id
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("thuong ngu");
		// cách 2: cách viết chuẩn của css
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("abc");
		// cách 3: 
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("thao@gmail.com");
	}
	
	
	public void TC_05_Xpath() {
		//cach 1: combine thẻ + id
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("thuong ngu");
		// cách 2: cách viết chuẩn của css
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("abc");
		// cách 3
		driver.findElement(By.xpath("//input[@data-val-required='Password is required.']"));
		
	}
	@AfterClass
	public void afterClass() {
		
	}
}