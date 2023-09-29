package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_User_Interaction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Actions action;

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

	// @Test
	public void TC_01_hoverElement() {

		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	// @Test
	public void TC_02_fahasa() {

		driver.get("https://www.fahasa.com/");

		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Ä�á»“ ChÆ¡i']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Xáº¿p HÃ¬nh - Láº¯p GhÃ©p']"))
				.click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Xáº¿p HÃ¬nh - Láº¯p GhÃ©p']")).isDisplayed());

	}

	// @Test
	public void TC_03_ClickAndHold() {

		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(7)).release().perform();
		sleepInSecond(3);

		List<WebElement> listNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listNumbersSelected.size(), 8);

	}

	@Test
	public void TC_04_selectRandomItems() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> listItemsRandom = driver.findElements(By.cssSelector("ol#selectable>li"));
		action.keyDown(Keys.CONTROL)
		.click(listItemsRandom.get(1))
		.click(listItemsRandom.get(4))
		.click(listItemsRandom.get(8))
		.keyUp(Keys.CONTROL)
		.perform();
		
		sleepInSecond(3);
		
		List<WebElement> listItemsSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listItemsSelected.size(), 3);

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