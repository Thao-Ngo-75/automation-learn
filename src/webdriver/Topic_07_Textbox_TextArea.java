package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random ran = new Random();
	Actions action;
	String employeeID = String.valueOf(ran.nextInt(9999));


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_EmptyEmail_PW() {
		//login admin
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		sleepInSecond(6);
		
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepInSecond(4);
		
		//Direct to PIM
		driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
		sleepInSecond(6);
		driver.findElement(By.xpath("//a[text()='Add Employee']/parent::li")).click();
		sleepInSecond(6);
		
		// Fill data
		WebElement firstName = driver.findElement(By.cssSelector("input[name='firstName']"));
		WebElement lastName = driver.findElement(By.cssSelector("input[name='lastName']"));
		WebElement employeetextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		
		
		
		firstName.sendKeys("thao");
		lastName.sendKeys("ngo");
		employeetextbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(1);
		employeetextbox.sendKeys(Keys.DELETE);
		sleepInSecond(1);
		
		employeetextbox.sendKeys(employeeID);
		
		driver.findElement(By.xpath("//input[@type='checkbox']/parent::label/span")).click();
		sleepInSecond(5);
		
		WebElement userName = driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"));
		WebElement password = driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"));
		WebElement confPass = driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"));
		
		userName.sendKeys("afc" + employeeID);
		password.sendKeys("thaoauto@123");
		confPass.sendKeys("thaoauto@123");
		
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepInSecond(8);		
		
		// Verify data
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "thao");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "ngo");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		// redirect to immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div//button")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys("0984-4765-322");
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys("My name is thao auto /nHello");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(4);
		
		//Edit screen
		driver.findElement(By.xpath("//button//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		
		// Verify data
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),"0984-4765-322");
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"),"My name is thao auto /nHello");
		
		//Logout
		driver.findElement(By.xpath("//li//span[@class='oxd-userdropdown-tab']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("thaoauto@123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepInSecond(4);
		
		// Verify
		driver.findElement(By.xpath("//span[text()='My Info']/parent::a")).click();
		sleepInSecond(6);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "thao");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "ngo");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		// redirect to immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//button//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),"0984-4765-322");
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value"),"My name is thao auto /nHello");
		
		
	}

	@Test
	public void TC_02_() {
		//Login
				
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
		driver.quit();
	}
}