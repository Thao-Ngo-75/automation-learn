package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_File_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

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

	//@Test
	public void TC_01_Upload_One_File() {
		
		String flowerName = "flower.jpg";
		String may_anhName = "may_anh.jpg";
		String tableName = "table.jpg";
		
		String flowerFilePath = projectPath +  "\\UploadFiles\\" + flowerName;
		String may_anhFilePath = projectPath +  "\\UploadFiles\\" + may_anhName;
		String tableFilePath = projectPath +  "\\UploadFiles\\" + tableName;
	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(flowerFilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(may_anhFilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(tableFilePath);
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+flowerName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+may_anhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+tableName+"']")).isDisplayed());
		
		List<WebElement> uploadFilesBtn = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadFilesBtn) {
			button.click();
			sleepInSecond(3);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+flowerName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+may_anhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+tableName+"']")).isDisplayed());
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+flowerName+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+may_anhName+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+tableName+"')]"));
	}

	@Test
	public void TC_02_Upload_Multiple_Files() {
		String flowerName = "flower.jpg";
		String may_anhName = "may_anh.jpg";
		String tableName = "table.jpg";
		
		String flowerFilePath = projectPath +  "\\UploadFiles\\" + flowerName;
		String may_anhFilePath = projectPath +  "\\UploadFiles\\" + may_anhName;
		String tableFilePath = projectPath +  "\\UploadFiles\\" + tableName;
	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(flowerFilePath +"\n"+ may_anhFilePath +"\n" + tableFilePath);
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+flowerName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+may_anhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+tableName+"']")).isDisplayed());
		
		List<WebElement> uploadFilesBtn = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadFilesBtn) {
			button.click();
			sleepInSecond(3);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+flowerName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+may_anhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+tableName+"']")).isDisplayed());
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+flowerName+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+may_anhName+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+tableName+"')]"));
	}

	@Test
	public void TC_03_() {
		
	}
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {

		return driver.findElement(By.xpath(locator));
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