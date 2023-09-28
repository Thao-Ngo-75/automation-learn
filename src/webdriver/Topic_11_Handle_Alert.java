package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_alertAccept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// C1
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked an alert successfully ')]"))
				.isDisplayed());

	}

	// @Test
	public void TC_02_alertAccept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked an alert successfully ')]"))
				.isDisplayed());

	}

//	@Test
	public void TC_03_alertConfirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked: Cancel')]")).isDisplayed());

	}

	// @Test
	public void TC_04_alertPrompt() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String promptText = "automation";
		alert.sendKeys(promptText);
		sleepInSecond(2);
		alert.accept();

		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + promptText);

	}

	// @Test
	public void TC_05_alert() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.findElement(By.xpath("//div[@class='inputfield ibvt loginData']/a")).click();
		sleepInSecond(2);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "Customer ID  cannot be left blank.");

	}

	//@Test
	public void TC_06_authenAlert() {

		driver.get("https://the-internet.herokuapp.com");
		String authenURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getURLbyAuthenAlert(authenURL, "admin", "admin"));
		sleepInSecond(2);

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	// @Test
	public void TC_07_authenAlert() {
		
		driver.get(getURLbyAuthenAlert("https://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		sleepInSecond(2);

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}
	@Test
	public void TC_08_authenAlertDMSIQA() {
		
		driver.get(getURLbyAuthenAlert("https://dmsi-uat.digicommercecloud.com/", "dmsi-uat", "meww*&hK)Wd4T&9f37%YfIU8"));
		sleepInSecond(5);
		driver.get(getURLbyAuthenAlert("https://dmsi-uat.digicommercecloud.com/", "dmsi-uat", "meww*&hK)Wd4T&9f37%YfIU8"));
		//Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Login']")).isDisplayed());
		
		
	}
	
	
	
	
	
	
	
	
	public String getURLbyAuthenAlert(String url, String userName, String password) {

		String[] splitURL = url.split("//");

		return splitURL[0] + "//" + userName + ":" + password + "@" + splitURL[1];

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
		// driver.quit();
	}
}