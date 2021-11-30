package endpoints;

import org.junit.BeforeClass;
import org.junit.Test;

import base.BaseSpecs;
import io.restassured.specification.RequestSender;

public class CityFeedTest {

	private static RequestSender requestSender;

	@BeforeClass
	public static void updateSpecifications() {
		requestSender = BaseSpecs.requestSender("/feed");
	}

	@Test
	public void getLondonAirData() {
		System.out.println(Thread.currentThread().getId()+"--- LONDON AIR DATA ---");
		requestSender.get("/london/").then().log().body();
	}

	@Test
	public void getDefaultAirData() {
		System.out.println(Thread.currentThread().getId()+"--- DEFAULT CITY AIR DATA ---");
		requestSender.get("/here/").then().log().body();
	}

	@Test
	public void getLatLongCityAirData() {
		System.out.println(Thread.currentThread().getId()+"--- LAT LONG CITY AIR DATA ---");
		requestSender.get("/geo:49.460983;11.061859/").then().log().body();
	}
}
