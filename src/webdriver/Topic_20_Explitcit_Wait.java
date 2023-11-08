package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Explitcit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
	}

	// @Test
	public void TC_01_Explitcit_Invisible_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Wait cho Start button visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start']"))).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")))
				.isDisplayed());

	}

	// @Test
	public void TC_02_Explitcit_Calendar() {

		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));

		// Verify khong co ngay nao duoc chon
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(),
				"No Selected Dates to display.");

		// Wait cho ngày 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']"))).click();
		// Wait cho ajax loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
				"div#ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1 div.raDiv")));
		// Wait cho ngay 19 duoc chon
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='19']")));
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='19']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(), "Sunday, November 19, 2023");

	}

	@Test
	public void TC_03_Explitcit_UploadFiles() {

		String flowerImgName = "flower.jpg";
		String heardImgName = "heard.jpg";
		String flowerPath = projectPath + "//UploadFiles//" + flowerImgName;
		String heardPath = projectPath + "//UploadFiles//" + heardImgName;

		driver.get("https://gofile.io/?t=uploadFiles");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button']")));
		driver.findElement(By.cssSelector("button[type='button']")).click();

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(flowerPath + "\n" + heardPath);

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo div.spinner-border")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
				"//span[text()='flower.jpg']/parent::div/following-sibling::div//div[@role='progressbar']/span")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
				"//span[text()='heard.jpg']/parent::div/following-sibling::div//div[@role='progressbar']/span")));

		Assert.assertEquals(driver.findElement(By.cssSelector("div.mainUploadSuccess div.alert")).getText(),
				"Your files have been successfully uploaded");

		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")))
				.click();

		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent div.spinner-border")));

		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='flower.jpg']//parent::a/parent::div/following-sibling::div[contains(@class,'px-0')]//span[text()='Download']//parent::button")))
				.isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='flower.jpg']//parent::a/parent::div/following-sibling::div[contains(@class,'px-0')]//span[text()='Play']//parent::button")))
				.isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='heard.jpg']//parent::a/parent::div/following-sibling::div[contains(@class,'px-0')]//span[text()='Download']//parent::button")))
				.isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='heard.jpg']//parent::a/parent::div/following-sibling::div[contains(@class,'px-0')]//span[text()='Play']//parent::button")))
				.isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}