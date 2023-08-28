package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		
	}

	@Test
	public void TC_01_() {
		WebElement element = driver.findElement(By.className(""));
		element.getCssValue(osName)

			
	@Test
	public void TC_02_() {
			String emailtextTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
			driver.findElement(By.xpath("//" + emailtextTagname + "[@id='email']"));
	}

	@Test
	public void TC_03_() {
		WebElement element = driver.findElement(By.className(""));
		Assert.assertTrue()
	}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}