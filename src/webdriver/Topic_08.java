package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		
	}

	@Test
	public void TC_01_Login_() {
		driver.get("https://github.com/Thao-Ngo-75/automation-learn/blob/master/.gitignore");
	}

	@Test
	public void TC_02_testing() {
		driver.get("");
	}

	@Test
	public void TC_03_() {
		//testing about github
		driver.get("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}