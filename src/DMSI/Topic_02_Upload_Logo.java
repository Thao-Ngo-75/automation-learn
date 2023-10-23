package DMSI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Upload_Logo {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String userID = "ACC2";
	String password = "1234";
	Select select;
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Upload_One_File() {
		driver.get("https://salelob.dmsi.cloud/");
		sleepInSecond(2);

		By userIDfield = By.cssSelector("input#login_id");
		By passwordField = By.cssSelector("input#password");
		By signInBtn = By.cssSelector("button[type='submit']");
		String heardName = "heard.jpg";
		String flowerPath = projectPath + "\\UploadFiles\\" + heardName;

		driver.findElement(By.xpath("//a[text()='Login']")).click();
		sleepInSecond(2);

		driver.findElement(userIDfield).sendKeys(userID);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(signInBtn).click();
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.cssSelector("div#account-menu-button")).isDisplayed());

		selectItemDropdown("div#account-menu-button>div.MuiStack-root>div:nth-of-type(1)",
				"ul[aria-labelledby='account-menu-button']>li", "User Profile");
		sleepInSecond(7);

		driver.findElement(By.cssSelector("input#file-upload")).sendKeys(flowerPath);
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Replace']")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.xpath("//button[@type='button']//img[contains(@src,'svg')]"))).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_02_() {

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

	public void selectItemDropdown(String parentCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			sleepInSecond(3);
			if (itemText.trim().equals(expectedTextItem)) {
				tempItem.click();
				break;

			}

		}

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}