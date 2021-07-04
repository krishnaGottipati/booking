package tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import pages.Flights;
import utils.Base;

public class TestCase1 extends Base {

	@Test
	public void tc2() throws InterruptedException {
		Flights.clickOnFlights();
		Flights.acceptCookie();
		Flights.enterFrom("Delhi");
		Flights.enterTo("Pune");
		Flights.pickDateFromCalander("DepartureFromToday:20");
		Flights.pickDateFromCalander("ReturnFromToday:45");
		Flights.clickOnSearchFlights();
		Flights.getPricesList();  //Pick the prices and select the maximum one
		assertEquals(Flights.continueButtonIsDisplayed(), true);

	}
	
}
