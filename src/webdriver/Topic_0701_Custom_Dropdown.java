package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_0701_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	WebDriverWait explicitWait;
	List webElement;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_JQuery() {

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Slower");

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Faster");

		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Slow");

	}

	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemDropdown("i.dropdown.icon", "span.text", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");

	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");


	}
	
	@Test
	public void TC_04_Editable() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection//");
		
		selectItemDropdown("input.search", "span.text", "Andorra");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Andorra");

		
	}
	
	public void selectItemDropdown(String parentCss, String allItemCss, String expectedTextItem) {
//		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

// 		1: Click vào 1 thẻ bất kì để làm sao nó xổ ra hết items của dropdown
		driver.findElement(By.cssSelector(parentCss)).click();

//		2: Chờ cho các items load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

//		Đưa hết tất cả các options vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

//		3: Tìm item xem đúng cái đang cần hay ko
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
//			4: Kiểm trả text của item đúng với cái mình mong muốn
			if (itemText.trim().equals(expectedTextItem)) {
//				5: Click vào item đó
				tempItem.click();
//			Thoát khỏi vòng lặp ko xét cho các case còn lại nữa
				break;

			}

		}

	}

	public void editAndSelectItemDropdown(String textBox, String allItemCss, String expectedTextItem) {
		
		driver.findElement(By.cssSelector(textBox)).clear();

// 		1: Click vào 1 thẻ bất kì để làm sao nó xổ ra hết items của dropdown
		driver.findElement(By.cssSelector(textBox)).sendKeys(expectedTextItem);
		sleepInSecond(2);
//		2: Chờ cho các items load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

//		Đưa hết tất cả các options vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

//		3: Tìm item xem đúng cái đang cần hay ko
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
//			4: Kiểm trả text của item đúng với cái mình mong muốn
			if (itemText.trim().equals(expectedTextItem)) {
//				5: Click vào item đó
				tempItem.click();
//			Thoát khỏi vòng lặp ko xét cho các case còn lại nữa
				break;

			}

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