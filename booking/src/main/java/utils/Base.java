package utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Flights;

public class Base {
	WebDriver driver;
	Helpers helper;
	Flights flights;
	
	public Base() {
		if(driver == null) {
				initilizeDriver();	
		}
		
	}

	private void initilizeDriver() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		driver.get("https://www.booking.com/");
		
		helper = new Helpers(driver);
		flights = new Flights(driver);
		
		
		
	}

}
