package VisualTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;

public class Applitest {
	
	@Test
	public void test() {
		
		WebDriver driver = new ChromeDriver();
		//driver.get("https://applitools.com/helloworld/");
		//driver.get("https://applitools.com/helloworld/?diff1");
		driver.get("https://applitools.com/helloworld/?diff2");
		
		
		Eyes eyes = new Eyes();
		
		eyes.setApiKey("SET YOUR API KEY");
		
		eyes.open(driver, "Applitools Demo", "Hello World Test");
		
		eyes.checkWindow("Hello World");
		
		eyes.close();
		
		
		
	}

}
