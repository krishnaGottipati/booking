package pages;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Helpers;

public class Flights {

	public static WebDriver driver;
	
	public Flights(WebDriver driver) {
		Flights.driver = driver;
	}

	/* ---- Repositories on Flights page  --- */	
	static By flights = By.xpath("//span[normalize-space()='Flights']");
	static By from = By.xpath("//div[@id='react-select-2--value']//input[@role='combobox']");
	static By searchFlights = By.xpath("//input[@id='airSubmitButtonId']");
	static By to = By.xpath("//div[@id='react-select-3--value']//input[@role='combobox']");
	static By depature = By.xpath("//input[@id='visualDepartureDate']");
	static By returndate = By.xpath("//input[@id='visualReturnDate']");
	static By datePickerCaption = By.xpath("//div[@class='DayPicker-Caption']//div");
	
	/* ---- Repositories on Search Results page  --- */	
	
	static By prices = By.xpath("//td//div[@class='tripItemPricePerPersonValue']");
	static By prices1 = By.xpath("//td//div[@class='tripItemPricePerPersonValue']/.");
	
	static By continueButton = By.xpath("//span[contains(text(),'Continue')]");


	/* ----     Flight helpers      --- */	
	public static void clickOnFlights() throws InterruptedException {
		Helpers.waitForElement(flights, 30).click();
		//Thread.sleep(5000);
		//driver.findElement(By.xpath("//span[@class='css-83z8lv']")).click();  //Handling the cookie
	}
	
	public static void acceptCookie() {
		Helpers.waitForElement(By.xpath("//span[@class='css-83z8lv']"), 30).click();
		
	}
	public static void enterFrom(String text) throws InterruptedException {
		WebElement element = Helpers.waitForElement(from, 40);
		element.sendKeys(text);
		Thread.sleep(1000);
		element.sendKeys(Keys.ENTER);
	}
	public static void enterTo(String text) throws InterruptedException {
		WebElement element = Helpers.waitForElement(to, 40);
		element.sendKeys(text);
		Thread.sleep(1000);
		element.sendKeys(Keys.ENTER);
	}
	public static void clickOnSearchFlights() {
		Helpers.waitForElement(searchFlights, 40).click();
	}
	public static void pickDateFromCalander(String StartDayfromToday) {
		int startDay = Integer.parseInt(StartDayfromToday.split(":")[1]);
		String monthYear = Helpers.getMonthAndYear(startDay);
		if (StartDayfromToday.split(":")[0].startsWith("Departure")) {
			Helpers.waitForElement(depature, 40).click();
			selectDate(monthYear, startDay);
		} else if (StartDayfromToday.split(":")[0].startsWith("Return")) {
			Helpers.waitForElement(returndate, 40).click();
			selectDate(monthYear, startDay);
		}
	}
	public static void selectDate(String monthYear, int date) {
		List<WebElement> listOfWebElements = Helpers.listOfElements(datePickerCaption);
		for (WebElement webElement : listOfWebElements) {
			if (webElement.getText().contains(monthYear)) {
				WebElement ele = driver.findElement(By.xpath("//div[@aria-label=\"" + Helpers.getDate(date) + "\"]"));
				if (ele.isDisplayed()) {
					ele.click();
					break;
				}
			} else {
				driver.findElement(By.xpath("//div[@class=\"DayPicker-NavBar\"]//span[@aria-label='Next Month']")).click();
				WebElement ele1 = driver.findElement(By.xpath("//div[@aria-label=\"" + Helpers.getDate(date) + "\"]"));
				if (ele1.isDisplayed()) {
					ele1.click();
				}
			}
		}
	}
	
	
	/* ----     Search results page helpers      --- */	
	
	public static void getPricesList() throws InterruptedException {
		Helpers.waitForElement(prices1, 30);
		List<WebElement> prices = Helpers.listOfElements(prices1);
		HashMap<Integer, Integer> pr = new HashMap<Integer, Integer>();
		for (int i = 0; i < prices.size(); i++) {
			String str = prices.get(i).getText();
			int str1 = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			pr.put(i, str1);
		}
		System.out.println(pr);
		int minValue = (Collections.max(pr.values())); 
		for (Entry<Integer, Integer> entry : pr.entrySet()) {
			if (entry.getValue() == minValue) {
				System.out.println(entry.getKey()); // Print the key with max value
				prices.get(entry.getKey());
				Thread.sleep(1000);
				int index = entry.getKey() + 1;
				System.out.println("index =" + index);
				WebElement book = driver.findElement(By.xpath("(//div[@class='tripItemRightContent__content'])[" + index + "]//..//div[contains(text(),'Book')]/.."));
				book.click();
	
				//driver.findElement(By.xpath("(//div[@class='bookButtonContent'])[\"" + entry.getKey() +  "\"]")).click();
				break;
			}
			
		}
	}
	
	public static boolean continueButtonIsDisplayed() {
		return Helpers.waitForElement(continueButton, 40).isDisplayed();
	}
	}
		
	
