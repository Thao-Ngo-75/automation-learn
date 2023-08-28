package webdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type_Java {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Kiểu dữ liệu nguyên thủy (Primitive)
			// Số nguyên: byte, short, int, long (ko có thập phân) <Theo thứ tự lớn dần về kích thước, độ rộng
				// byte lưu được khoảng 256 số -124 > 124
			// Số thực: float, double (Có thập phân)
			float studenpoint = 9.5f;
			double abc = 34.5d;
			// Logic: boolean
			boolean status = true;
			status = false;
			// Kí tự: char
			char a = 'A';
		// Kiểu dữ liệu tham chiếu (Reference)
			//Class
			FirefoxDriver driver = new (FirefoxDriver);
			
			// Interface
			WebElement firstNameTextBox;
			
			//String
			String firstname = "Automation Testing";
			
			// Object
			Object people;
			//Array
			String[] studen = {"","",""};
			
	}

}
