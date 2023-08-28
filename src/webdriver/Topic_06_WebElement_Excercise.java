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

public class Topic_06_WebElement_Excercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.id("mail");
	By ageUnder18 = By.id("under_18");
	By education = By.id("edu");
	By nameUser5 = By.xpath("//h5[text()='Name: User5']");
	By langJava = By.cssSelector("input#java");

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

//	@Test
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Textbox
		if (driver.findElement(emailTextbox).isDisplayed())
		{
			driver.findElement(emailTextbox).sendKeys("thaongo@gmail.com");
			System.out.println("Element is displayed");
			
		} else {
			System.out.println("Element is not displayed");
		}
		
		//Radio
		if (driver.findElement(ageUnder18).isDisplayed()) {
			driver.findElement(ageUnder18).click();
			System.out.println("Element is displayed");
			
		} else {
			System.out.println("Element is not displayed");
		}
		
		//Text Area
		if (driver.findElement(education).isDisplayed()) {
			driver.findElement(education).sendKeys("WebElement");
			System.out.println("Element is displayed");
			
		} else {
			System.out.println("Element is not displayed");
		}
		
		//Neu khong hien thi
		if (driver.findElement(nameUser5).isDisplayed()) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
	}

	//@Test
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Enable
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
			System.out.println("Email textbox is not enable");
		}
		
		if (driver.findElement(By.cssSelector("textarea#bio")).isEnabled()) {
			System.out.println("Bio is enable");
		} else {
			System.out.println("Bio is disable");
		}
		
	}

//	@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(ageUnder18).click();
		driver.findElement(By.cssSelector("input#java")).click();
		
		Assert.assertTrue(driver.findElement(ageUnder18).isSelected());
		Assert.assertTrue(driver.findElement(langJava).isSelected());
		
		driver.findElement(langJava).click();
		Assert.assertFalse(driver.findElement(langJava).isSelected());
	}
	
	@Test
	public void TC_04_Register_Interact() {
		
		driver.get("https://login.mailchimp.com/signup/");
		WebElement emailfield = driver.findElement(By.cssSelector("input#email"));
		WebElement passwordfield = driver.findElement(By.cssSelector("input#new_password"));
		
		
		emailfield.sendKeys("thao.ngo@gmail.com");
		passwordfield.sendKeys("111");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		sleepInSecond(2);
		
		
		emailfield.clear();
		passwordfield.clear();
		
		emailfield.sendKeys("thao.ngo@gmail.com");
		passwordfield.sendKeys("aaa");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		sleepInSecond(2);
		
		emailfield.clear();
		passwordfield.clear();
		
		emailfield.sendKeys("thao.ngo@gmail.com");
		passwordfield.sendKeys("AAA");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		sleepInSecond(2);
		
		emailfield.clear();
		passwordfield.clear();
		
		emailfield.sendKeys("thao.ngo@gmail.com");
		passwordfield.sendKeys("@#$%");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		sleepInSecond(2);
		
		emailfield.clear();
		passwordfield.clear();
		
		emailfield.sendKeys("thao.ngo@gmail.com");
		passwordfield.sendKeys("67123458");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		sleepInSecond(2);
		
		emailfield.clear();
		passwordfield.clear();
		
		emailfield.sendKeys("thao.ngo@gmail.com");
		passwordfield.sendKeys("Thao@1234");
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	
		
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