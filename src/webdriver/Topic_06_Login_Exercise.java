package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Login_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void Login_01_EmptyEmail_PW() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAc).click();
		sleepInSecond(2);
		
		driver.findElement(logbtn).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
}

	@Test
	public void Login_02_Invalid_Email() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAc).click();
		sleepInSecond(2);
		
		driver.findElement(Email).sendKeys("1234@12312");
		driver.findElement(pw).sendKeys("123456");
		sleepInSecond(2);
		
		driver.findElement(logbtn).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
		
	}

	@Test
	public void Login_03_PW_lessthan_6chac() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAc).click();
		sleepInSecond(2);

		
		driver.findElement(Email).sendKeys("automation@gmail.com");
		driver.findElement(pw).sendKeys("123");
		sleepInSecond(2);
		
		driver.findElement(logbtn).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
		
	}
	
	@Test
	public void Login_04_Incorrect_Email_PW() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAc).click();
		sleepInSecond(2);
		
		driver.findElement(Email).clear();
		driver.findElement(pw).clear();
		
		driver.findElement(Email).sendKeys("automation@gmail.com");
		driver.findElement(pw).sendKeys("123123123");
		sleepInSecond(2);
		
		driver.findElement(logbtn).click();
		Assert.assertEquals(driver.findElement(By.className("error-msg")).getText(), "Invalid login or password.");
		
		
		
		
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