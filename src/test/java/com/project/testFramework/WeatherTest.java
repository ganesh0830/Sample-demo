package com.project.testFramework;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.project.framework.Resources;

public class WeatherTest extends BasePage {

	// PostSessionTokenDevPortal obj = new PostSessionTokenDevPortal();

	@Test
	public void getWeatherOfCity() {
		Response res = given().header("Content-Type", "application/json").when().get(Resources.placeGetCities()).then()
				.assertThat().statusCode(200).log().all().and().contentType(ContentType.JSON).and().extract()
				.response();
		String responseString = res.asString();
		System.out.println(responseString);

	}

}