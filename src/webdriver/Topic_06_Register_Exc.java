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

public class Topic_06_Register_Exc {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random ran;
	String emailAddress;
	
	By myAc = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By Email = By.cssSelector("#email");
	By pw = By.cssSelector("#pass");
	By logbtn = By.cssSelector("button[title='Login']");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		ran = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAc).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys("thi");
		driver.findElement(By.id("middlename")).sendKeys("ngo");
		driver.findElement(By.id("lastname")).sendKeys("thao");
		driver.findElement(By.id("email_address")).sendKeys("automation"+ ran.nextInt(999) + "@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Automa@123");
		driver.findElement(By.id("confirmation")).sendKeys("Automa@123");
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText(),"");
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText(),"");
		
		driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src='http://live.techpanda.org/media/wysiwyg/test/logo.png']")).isDisplayed());
		System.out.println("Back to Home page successful");
		
		
	}

	@Test
	public void TC_02_() {
		
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