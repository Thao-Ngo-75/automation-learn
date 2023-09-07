package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, day, month, year, email, company, pw;
	Select select;
	Random ran = new Random();
	

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
	public void TC_01_Exercise() {
		driver.get("https://demo.nopcommerce.com/");
		
		firstName = "Thao";
		lastName = "Ngo";
		day = "6";
		month = "May";
		year = "1997";
		company = "Tech";
		email = "automation" + ran.nextInt(9999)+ "@gmail.com";
		pw = "Auto@543";
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.name("FirstName")).sendKeys(firstName);
		driver.findElement(By.name("LastName")).sendKeys(lastName);
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		driver.findElement(By.name("Email")).sendKeys(email);
		driver.findElement(By.name("Company")).sendKeys(company);
		driver.findElement(By.name("Password")).sendKeys(pw);
		driver.findElement(By.name("ConfirmPassword")).sendKeys(pw);
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
		
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.name("Email")).sendKeys(email);
		driver.findElement(By.name("Password")).sendKeys(pw);
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("div.header-upper a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.name("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		
		
	}

	@Test
	public void TC_02_() {
		driver.get("https://rode.com/en/support/where-to-buy");
		new Select(driver.findElement(By.id("country"))).isMultiple();
		new Select(driver.findElement(By.id("country"))).selectByVisibleText("Vietnam");
		Assert.assertEquals(new Select(driver.findElement(By.id("country"))).getFirstSelectedOption().getText(), "Vietnam");
		
		driver.findElement(By.cssSelector("map_search_query")).sendKeys("HCM");
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		sleepInSecond(3);
		
		
		
		
		
		
		
		
		
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
		//driver.quit();
	}
}