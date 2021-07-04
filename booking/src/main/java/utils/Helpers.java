package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {
	static WebDriver driver;

	public Helpers(WebDriver driver) {
		this.driver = driver;
	}

	public static WebElement waitForElement(final By objlocator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(objlocator));
		return webElement;
	}

	public static boolean clickButton(By objlocator) {
		try {
			waitForElement(objlocator, 40).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<WebElement> listOfElements(By objlocator) {
		try {
			List<WebElement> listOfWebElements = driver.findElements(objlocator);
			for (int i = 0; i < listOfWebElements.size(); i++) {
				//System.out.println("List of elements = " + listOfWebElements.get(i).getText());
			}
			return listOfWebElements;
		} catch (Exception e) {
			return null;
		}

	}

	public static String getDate(int day) {
		
		LocalDate date2 =  LocalDate.now().plusDays(day);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd, yyyy");
		String date = date2.format(formatter);
		System.out.println(date);
		
		return date;
	}
	
	public static String getMonthAndYear(int day) {
		LocalDate date2 =  LocalDate.now().plusDays(day);
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMMM yyyy");
		String monthYear = date2.format(formatter2);
		System.out.println(monthYear);
		
		return monthYear;
	}

}
