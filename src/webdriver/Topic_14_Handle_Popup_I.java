package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Handle_Popup_I {
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
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Fixed_inDOM_Ngoaingu() {
		driver.get("https://ngoaingu24h.vn/");
		
		driver.findElement(By.cssSelector("button.login_")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='modal-content']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='account-input']")).sendKeys("automa");
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='password-input']")).sendKeys("automa");
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[@type='button']")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='modal-content']")).isDisplayed());
	}

	//@Test
	public void TC_02_Fixed_inDOM_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.right")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("auto@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		
		
	}

	//@Test
	public void TC_03_Fixed_NotInDOM_Tiki() {
		
		driver.get("https://tiki.vn/");
		
		By loginPopup = By.cssSelector("div[class='styles__Root-sc-2hr4xa-0 jyAQAr']");
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		driver.findElement(By.cssSelector("div[data-view-id=header_header_account_container]")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//form/span[1]")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//form/span[2]")).getText(), "Mật khẩu không được để trống");
		
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
	}
	
	@Test
	public void TC_03_Fixed_NotInDOM_Facebook() {
		driver.get("https://www.facebook.com/");
		By signUpPop = By.cssSelector("div._8ien");
		
		Assert.assertEquals(driver.findElements(signUpPop).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid*=registration]")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(signUpPop).size(), 1);
		
		driver.findElement(By.cssSelector("img._8idr")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(signUpPop).size(), 0);
		
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