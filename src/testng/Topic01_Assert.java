package testng;


import org.testng.Assert;

public class Topic01_Assert {
	public static void main(String[] args) {
		
		String fullname = "Automation Testing";
		Assert.assertTrue(fullname.contains("auto"));
		
		
	}
	
}
