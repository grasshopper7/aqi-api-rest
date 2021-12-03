package endpoints;

import static base.BaseSpecs.requestSenderSearch;

import org.junit.Test;

public class KeywordSearchTest {

	@Test
	public void getStationsByKeyword() {
		System.out.println(Thread.currentThread().getId() + "--- STATIONS WITH KEYWORD AMSTERDAM ---");
		requestSenderSearch().get("?keyword=amsterdam").then().log().body();
	}
}
