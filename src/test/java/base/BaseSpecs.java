package base;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSpecs {

	static {
		requestSpecification = new RequestSpecBuilder().setBaseUri("https://api.waqi.info")
				.addQueryParam("token", "1a5d8f027e344fa191b88e966bdb5d4aa4853d4a").build();

		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("ok"))
				.build();
	}

	public static RequestSender requestSender(String path) {
		RequestSpecification reqSpec = new RequestSpecBuilder().setBasePath(path).build();
		return given(reqSpec);
	}

	public static RequestSender requestSenderFeed() {
		return requestSender("/feed");
	}

	public static RequestSender requestSenderMapBounds() {
		return requestSender("/map/bounds");
	}

	public static RequestSender requestSenderSearch() {
		return requestSender("/search");
	}
}
