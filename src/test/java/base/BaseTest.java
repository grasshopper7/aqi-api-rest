package base;

import org.junit.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public abstract class BaseTest {

	protected static RequestSpecification baseRequestSpecification;
	protected static ResponseSpecification baseResponseSpecification;

	@BeforeClass
	public static void constructSpecifications() {

		baseRequestSpecification = new RequestSpecBuilder().setBaseUri("https://api.waqi.info")
				.addQueryParam("token", "<TOKEN>").build();

		baseResponseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("ok"))
				.build();
	}
}
