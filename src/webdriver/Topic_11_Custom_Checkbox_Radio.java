package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Custom_radioButton() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		
		By radioBtn = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioBtn));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(radioBtn).isSelected());
	

	}

	@Test
	public void TC_02_Custom_Checkbox_Radio_Button() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By radioBtn = By.cssSelector("div[aria-label='Đà Nẵng']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Nam']");
		
		jsExecutor.executeScript("arguments[0].click(); arguments[1].click() ", driver.findElement(radioBtn), driver.findElement(checkbox));
		sleepInSecond(3);
		
		//Cách verify 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Đà Nẵng'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Nam'][aria-checked='true']")).isDisplayed());
		//Cách verify 2
		Assert.assertEquals(driver.findElement(radioBtn).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
		
	}

	
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
	//	driver.quit();
	}
}