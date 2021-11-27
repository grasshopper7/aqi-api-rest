package endpoints;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

public class CityFeedTest extends BaseTest {

	private static RequestSender requestSender;

	@BeforeClass
	public static void updateSpecifications() {
		RequestSpecification reqSpec = new RequestSpecBuilder().addRequestSpecification(baseRequestSpecification)
				.setBasePath("/feed").build();

		requestSender = given(reqSpec, baseResponseSpecification);
	}

	@Test
	public void getLondonAirData() {
		System.out.println("--- LONDON AIR DATA ---");
		requestSender.get("/london/").then().log().body();
	}

	@Test
	public void getDefaultAirData() {
		System.out.println("--- DEFAULT CITY AIR DATA ---");
		requestSender.get("/here/").then().log().body();
	}

	@Test
	public void getLatLongCityAirData() {
		System.out.println("--- LAT LONG CITY AIR DATA ---");
		requestSender.get("/geo:49.460983;11.061859/").then().log().body();
	}
}
