package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Exercise {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty_Data() {
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert.assertTrue -> Kiểm trả 1 điều kiện trả về Đúng
		//Assert.assertFalse -> Kiểm trả 1 điều kiện trả về Sai
		//Assert.assertEquals -> Kiểm trả thực tế và mong đợi như nhau
		// Data type: Webelement # String > Phải gettext thì mới thành String data type
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại. ");
		
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(By.id("txtFirstname-error")).sendKeys("thao ngo");
		driver.findElement(By.id("txtEmail-error")).sendKeys("thao.ngo@@");
		driver.findElement(By.id("txtCEmail-error")).sendKeys("thao.ngo@@");
		driver.findElement(By.id("txtPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone-error")).sendKeys("0990909035");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
	}

	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		
		driver.findElement(By.id("txtFirstname-error")).sendKeys("thao ngo");
		driver.findElement(By.id("txtEmail-error")).sendKeys("thao.ngo@gmail.com");
		driver.findElement(By.id("txtCEmail-error")).sendKeys("thao.ngo4@gmail.com");
		driver.findElement(By.id("txtPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone-error")).sendKeys("0990909035");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	

	public void TC_04_PW_LessThan_6character() {
		
		driver.findElement(By.id("txtFirstname-error")).sendKeys("thao ngo");
		driver.findElement(By.id("txtEmail-error")).sendKeys("thao.ngo@gmail.com");
		driver.findElement(By.id("txtCEmail-error")).sendKeys("thao.ngo4@gmail.com");
		driver.findElement(By.id("txtPassword-error")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword-error")).sendKeys("12345");
		driver.findElement(By.id("txtPhone-error")).sendKeys("0990909035");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
		
	
		
	}
	
	public void TC_05_Incorrect_ConfirmPW() {
		
		driver.findElement(By.id("txtFirstname-error")).sendKeys("thao ngo");
		driver.findElement(By.id("txtEmail-error")).sendKeys("thao.ngo@gmail.com");
		driver.findElement(By.id("txtCEmail-error")).sendKeys("thao.ngo4@gmail.com");
		driver.findElement(By.id("txtPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword-error")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone-error")).sendKeys("0990909035");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
			
		
	}
	
	public void TC_06_Invalid_PhoneNumber() {
	
		// Nhiều hơn 10 ký tự
		driver.findElement(By.id("txtFirstname-error")).sendKeys("thao ngo");
		driver.findElement(By.id("txtEmail-error")).sendKeys("thao.ngo@gmail.com");
		driver.findElement(By.id("txtCEmail-error")).sendKeys("thao.ngo4@gmail.com");
		driver.findElement(By.id("txtPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword-error")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone-error")).sendKeys("09909090354354");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số. ");
			
		// Ít hơn 10 ký tự
		
		driver.findElement(By.id("txtPhone-error")).clear();
		driver.findElement(By.id("txtPhone-error")).sendKeys("099090");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số. ");
		
		//Không phải đầu số của nhà mạng
		driver.findElement(By.id("txtPhone-error")).clear();
		driver.findElement(By.id("txtPhone-error")).sendKeys("5453423467");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		
	}
		
		
	@AfterClass
	public void afterClass() {
		
	}
}