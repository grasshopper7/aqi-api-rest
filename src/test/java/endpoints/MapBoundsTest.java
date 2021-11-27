package endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

public class MapBoundsTest extends BaseTest {

	private static JsonPath jsonPath;

	@BeforeClass
	public static void updateSpecifications() {
		RequestSpecification reqSpec = new RequestSpecBuilder().addRequestSpecification(baseRequestSpecification)
				.setBasePath("/map/bounds/").build();

		RequestSender requestSender = given(reqSpec, baseResponseSpecification);

		jsonPath = requestSender.get("?latlng=52.520008,13.404954,48.137154,11.576124").then().extract().jsonPath();
		jsonPath.setRootPath("data");

		System.out.println("--- STATIONS BETWEEN BERLIN & MUNICH ---");

		System.out.println(jsonPath.prettyPrint());

	}

	@Test
	public void getStationsBetweenLatitudeLongitude() {
		assertThat(jsonPath.getList("aqi").get(0), equalTo(11));
	}
}
