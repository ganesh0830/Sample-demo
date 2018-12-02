package com.project.testFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import com.jayway.restassured.RestAssured;

public class BasePage {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream("src/main/java/com/project/framework/env.properties");
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("HOST");
	}
}