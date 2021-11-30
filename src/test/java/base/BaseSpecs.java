package base;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseSpecs {

	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder().setBaseUri("https://api.waqi.info").addQueryParam("token", "<TOKEN>").build();
	}

	public static ResponseSpecification responseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("ok")).build();
	}

	public static RequestSender requestSender(String path) {
		RequestSpecification reqSpec = new RequestSpecBuilder().addRequestSpecification(requestSpec()).setBasePath(path)
				.build();
		return given(reqSpec, responseSpec());
	}
}
