package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_Button {
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

	// @Test
	public void TC_01_Handle_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		By loginBtn = By.cssSelector("button.fhs-btn-login");

		// Verify login btn disabled
		Assert.assertFalse(driver.findElement(loginBtn).isEnabled());

		// Verify color of the login btn when disabling
		String loginBtnBackGround = driver.findElement(loginBtn).getCssValue("background-image");
		Assert.assertTrue(loginBtnBackGround.contains("rgb(224, 224, 224)"));

		// Nhap data vao 2 fields
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("thao.ngo@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345667");
		sleepInSecond(3);

		// Verify loginbtn enabled
		Assert.assertTrue(driver.findElement(loginBtn).isEnabled());
		loginBtnBackGround = driver.findElement(loginBtn).getCssValue("background-color");
		Color loginBtnBackGroundColor = Color.fromString(loginBtnBackGround);
		Assert.assertEquals(loginBtnBackGroundColor.asHex().toUpperCase(), "#C92127");

	}

	@Test
	public void TC_02_Handle_DefaultCheckbox() {
		// step1
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		// step2
		if (!driver.findElement(By.xpath("//label[text()='Rain sensor']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Rain sensor']/preceding-sibling::input")).click();
		}

		// step3
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[text()='Rain sensor']/preceding-sibling::input")).isSelected());
		// step4
		if (driver.findElement(By.xpath("//label[text()='Rain sensor']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Rain sensor']/preceding-sibling::input")).click();
		}
		Assert.assertFalse(
				driver.findElement(By.xpath("//label[text()='Rain sensor']/preceding-sibling::input")).isSelected());
		// step5

	}

	//@Test
	public void TC_03_Handle_DefaultRadio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		if (!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
			
		} 
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
		
		if (!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
			
		} 
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