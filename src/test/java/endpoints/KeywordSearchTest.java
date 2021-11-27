package endpoints;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

public class KeywordSearchTest extends BaseTest {

	private static RequestSender requestSender;

	@BeforeClass
	public static void updateSpecifications() {
		RequestSpecification reqSpec = new RequestSpecBuilder().addRequestSpecification(baseRequestSpecification)
				.setBasePath("/search/").build();

		requestSender = given(reqSpec, baseResponseSpecification);
	}

	@Test
	public void getStationsByKeyword() {
		System.out.println("--- STATIONS WITH KEYWORD AMSTERDAM ---");
		requestSender.get("?keyword=amsterdam").then().log().body();
	}
}
