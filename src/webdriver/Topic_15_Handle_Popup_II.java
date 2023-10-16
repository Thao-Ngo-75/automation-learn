package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Handle_Popup_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random ran;
	String emailAddress = "automa" + RandomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Random_inDOM() {
		
		driver.get("https://www.javacodegeeks.com/");		
		sleepInSecond(15);
		By popup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		if (driver.findElement(popup).isDisplayed()) {
			
			driver.findElement(By.cssSelector("div.lepopup-input>input[type='email']")).sendKeys(emailAddress);
			sleepInSecond(5);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']")).click();
			sleepInSecond(5);

		}
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		sleepInSecond(7);
		
		driver.findElement(By.cssSelector("button#search-submit>span.tie-icon-search")).click();
		sleepInSecond(7);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul#posts-container>li:first-child>div>h2>a")).getText(), "Agile Testing Explained");
	}

	//@Test
	public void TC_02_Random_inDOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		
		By popup = By.cssSelector("div.tcb-flex-row>div:first-of-type");
		if (driver.findElement(popup).isDisplayed()) {
			
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
		
	}

	@Test
	public void TC_03_Random_NotInDOM() {
		
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		By popup = By.cssSelector("div.popup-content");
		
		//same as isDisplay() for findElements
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(5);
		}
		
		driver.findElement(By.xpath("//h2[text()='KHÓA HỌC NHIỀU NGƯỜI MUA NHẤT']/parent::div//button")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.wrap-courses>div:first-child h4")).getText(), "Khóa học Thiết kế và Thi công Hệ thống BMS");
		
		
	}

	public int RandomNumber() {

		Random rand = new Random();
		return rand.nextInt(9999);

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
		//driver.quit();
	}
}