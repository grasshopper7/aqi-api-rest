package endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;

import org.junit.Test;

import base.BaseSpecs;

public class MapBoundsTest extends BaseSpecs {

	@Test
	public void getStationsBetweenLatitudeLongitude() {
		System.out.println(Thread.currentThread().getId() + "--- STATIONS BETWEEN BERLIN & MUNICH ---");

		given().param("latlng", "52.520008,13.404954,48.137154,11.576124").when().get("/map/bounds/").then().log()
				.body();
	}

	@Test
	public void validateMapStationsSchema() {
		System.out.println(Thread.currentThread().getId() + "--- VALIDATE MAP STATIONS SCHEMA ---");

		given().param("latlng", "52.520008,13.404954,48.137154,11.576124").when().get("/map/bounds/").then()
				.body(matchesJsonSchema(new File("src/test/resources/airquality/map-stations-schema.json")));
	}
}
