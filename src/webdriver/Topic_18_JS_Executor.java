package webdriver;

import java.util.Random;
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

public class Topic_18_JS_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Random rand;

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
	public void TC_01_Tech_Panda() {
		navigateToUrlByJS("http://live.techpanda.org");
		sleepInSecond(5);

		Assert.assertEquals(getDomainName(), "live.techpanda.org");
		Assert.assertEquals(getURL(), "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		sleepInSecond(2);
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		hightlightElement("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(2);
		clickToElementByJS("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("IPhone was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		sleepInSecond(2);
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(2);
		
		Assert.assertEquals(getTitle(),"Customer Service");
		
		
		hightlightElement("//input[@type='email']");
		sleepInSecond(2);
		scrollToElementOnDown("//input[@type='email']");
		sleepInSecond(2);
		hightlightElement("//input[@type='email']");
		sendkeyToElementByJS("//input[@type='email']", "thao543@gmail.net");
		sleepInSecond(3);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(3);
		
		Assert.assertEquals(getDomainName(), "demo.guru99.com");
		
	}

	@Test
	public void TC_02_HTML5() {
		
		String Name = "//input[@id='name']";
		String Email = "//input[@id='email']";
		String PW = "//input[@id='password']";
		String ConfirmPW = "//input[@id='password_confirmation']";
		String registerBtn = "//button[@type='submit']";
		
		navigateToUrlByJS("https://warranty.rode.com/register");
		sleepInSecond(5);
		
		hightlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(Name), "Please fill out this field.");
		
		sendkeyToElementByJS(Name, "thao");
		sleepInSecond(3);
		hightlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(Email), "Please fill out this field.");
		
		sendkeyToElementByJS(Email, "ngothi@");
		sleepInSecond(3);
		hightlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(Email), "Please enter an email address.");
		
		sendkeyToElementByJS(Email, "ngothi@gmail.com");
		sleepInSecond(3);
		hightlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(PW), "Please fill out this field.");
		
		sendkeyToElementByJS(PW, "Thao@1234");
		sleepInSecond(3);
		hightlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(ConfirmPW), "Please fill out this field.");
		
		sendkeyToElementByJS(ConfirmPW, "Thao@1234");
		sleepInSecond(3);
		hightlightElement(registerBtn);
		clickToElementByJS(registerBtn);
		sleepInSecond(3);
		
		Assert.assertEquals(getURL(), "https://warranty.rode.com/registration");
		Assert.assertTrue(getInnerText().contains("Register your warranty"));

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

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain;");

	}

	public String getURL() {

		return (String) jsExecutor.executeScript("return document.URL;");
	}
	public String getTitle() {
		
		return (String) jsExecutor.executeScript("return document.title;");
	}
	
	
	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');",
				getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
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
	public String randomEmail() {
		return "auto" + rand.nextInt(9999) + "@gmail.com";
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}