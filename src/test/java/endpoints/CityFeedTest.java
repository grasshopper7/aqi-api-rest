package endpoints;

import static base.BaseSpecs.requestSenderFeed;

import org.junit.Test;

public class CityFeedTest {

	@Test
	public void getLondonAirData() {
		System.out.println(Thread.currentThread().getId() + "--- LONDON AIR DATA ---");
		requestSenderFeed().get("/london/").then().log().body();
	}

	@Test
	public void getDefaultAirData() {
		System.out.println(Thread.currentThread().getId() + "--- DEFAULT CITY AIR DATA ---");
		requestSenderFeed().get("/here/").then().log().body();
	}

	@Test
	public void getLatLongCityAirData() {
		System.out.println(Thread.currentThread().getId() + "--- LAT LONG CITY AIR DATA ---");
		requestSenderFeed().get("/geo:49.460983;11.061859/").then().log().body();
	}
}
