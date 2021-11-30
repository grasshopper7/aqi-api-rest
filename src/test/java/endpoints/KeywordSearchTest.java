package endpoints;

import org.junit.BeforeClass;
import org.junit.Test;

import base.BaseSpecs;
import io.restassured.specification.RequestSender;

public class KeywordSearchTest {

	private static RequestSender requestSender;

	@BeforeClass
	public static void updateSpecifications() {
		requestSender = BaseSpecs.requestSender("/search");
	}

	@Test
	public void getStationsByKeyword() {
		System.out.println(Thread.currentThread().getId()+"--- STATIONS WITH KEYWORD AMSTERDAM ---");
		requestSender.get("?keyword=amsterdam").then().log().body();
	}
}
