package airquality;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.io.File;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.path.json.JsonPath;

public class StationsTest {

	private static JsonPath jsonPath;

	@BeforeClass
	public static void createJsonPath() {
		jsonPath = new JsonPath(new File("src/test/resources/airquality/stations.json"));
	}

	@Test
	public void liveRestApiResponseStatusTest() {
		given().when().queryParam("token", "<TOKEN>").queryParam("latlng", "52.520008,13.404954,48.137154,11.576124")
				.get("https://api.waqi.info/map/bounds/").then().statusCode(200).body("status", equalTo("ok"));
	}

	@Test
	public void verifyStatusText() {
		assertThat(jsonPath.getString("status"), equalTo("ok"));
	}

	@Test
	public void verifyFirstStationId() {
		assertThat(jsonPath.getInt("data[0].uid"), equalTo(6180));
	}

	@Test
	public void verifyFirstStationName() {
		assertThat(jsonPath.getString("data[0].station.name"), equalTo("Brandenburg a.d.Havel, Germany"));
	}

	@Test
	public void verifyAvailableStationCount() {
		assertThat(jsonPath.getList("data").size(), equalTo(43));
		assertThat(jsonPath.getInt("data.uid.collect {1} .sum()"), equalTo(43));
	}

	@Test
	public void verifyAqiValues() {
		assertThat(jsonPath.getList("data.aqi"), hasItems("14", "15", "16"));
	}

	@Test
	public void verifyStationNames() {
		assertThat(jsonPath.getList("data.station.name"),
				hasItems("Berlin, Germany", "Brandenburg a.d.Havel, Germany", "Prebuz, Karlovarsky, Czech Republic",
						"Kolpingplatz, Ecke Ringstraße 23 (Parkplatz Sonderschule), Austria"));
	}

	@Test
	public void verifyFourDigitStationUid() {
		assertThat(jsonPath.getList("data.uid.findAll {it > 6000 && it < 6050}"),
				containsInAnyOrder(6039, 6041, 6046, 6047, 6048));
	}

	@Test
	public void verifyFourDigitStationUidMultipleFind() {
		assertThat(jsonPath.getList("data.uid.findAll {it > 6000}. findAll{ it < 6050}"),
				containsInAnyOrder(6039, 6041, 6046, 6047, 6048));
	}

	@Test
	public void verifyAqiLessThan10Value() {
		assertThat(jsonPath.getList("data.aqi.collect {Integer.parseInt(it)}. findAll {it < 10}"),
				containsInAnyOrder(4, 8, 8));
	}

	@Test
	public void verifyAqiLessThan10StationNames() {
		assertThat(jsonPath.getList("data.findAll {Integer.parseInt(it.aqi) < 10}.station.name"),
				containsInAnyOrder("Johanneskirchen, München, Germany", "Podewilsstraße, Landshut, Germany",
						"Marktler Straße, Burghausen, Germany"));
	}

	@Test
	public void verifyMaximumAqi() {
		assertThat(jsonPath.getInt("data.aqi.collect {Integer.parseInt(it)}. max()"), equalTo(68));
	}

	@Test
	public void verifyMinimumAqi() {
		assertThat(jsonPath.getInt("data.aqi.collect {Integer.parseInt(it)}. min()"), equalTo(4));
	}

	@Test
	public void verifyLocationObject() {
		List<Location> locations = jsonPath.getList("data", Location.class);

		Location locObj = Location.builder().lat(52.194168f).lon(12.561389f).uid(9312).aqi(16)
				.station(Station.builder().name("Lütte (Belzig) +, Germany").time("2021-11-21T10:00:00+02:00").build())
				.build();

		assertThat(locations.get(1), equalTo(locObj));
	}
}
