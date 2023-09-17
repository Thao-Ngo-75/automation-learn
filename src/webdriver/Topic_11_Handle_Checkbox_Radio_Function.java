package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_Checkbox_Radio_Function {
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

	//@Test
	public void TC_01_Default_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");

		isSelected(By.cssSelector("input[value='Summer']"));
		unSelected(By.cssSelector("input[value='Summer']"));

		Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Summer']")).isSelected());
	}

	@Test
	public void TC_02_Default_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		selectCheckboxMultiple("div.mdc-checkbox", "Checked");
		selectCheckboxMultiple("div.mdc-checkbox", "Indeterminate");

	}

	public void isSelected(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();

		}

	}

	public void unSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();

		}

	}

	public void selectCheckboxMultiple(String allItemsCss, String expectedCss) {
		driver.get("https://material.angular.io/components/checkbox/examples");

		List<WebElement> checkboxes = driver.findElements(By.cssSelector(allItemsCss));

		for (WebElement checkbox : checkboxes) {
			String checkboxText = checkbox.getText();
			if (checkboxText.trim().equals(expectedCss)) {
				checkbox.click();
			}
			break;
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