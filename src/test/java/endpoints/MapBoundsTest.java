package endpoints;

import org.junit.BeforeClass;
import org.junit.Test;

import base.BaseSpecs;
import io.restassured.specification.RequestSender;

public class MapBoundsTest {

	private static RequestSender requestSender;

	@BeforeClass
	public static void updateSpecifications() {
		requestSender = BaseSpecs.requestSender("/map/bounds/");
	}

	@Test
	public void getStationsBetweenLatitudeLongitude() {
		System.out.println(Thread.currentThread().getId() + "--- STATIONS BETWEEN BERLIN & MUNICH ---");
		requestSender.get("?latlng=52.520008,13.404954,48.137154,11.576124").then().log().body();
	}
}
