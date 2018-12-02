package com.project.testFramework;

import org.testng.annotations.Test;

import com.project.demo.HelperFunctions;
import com.project.demo.JsonHandler;

public class JsonTest {
	@Test
	public void testJson(){
		String filePath = "src/test/resources/Seller-Json.json";

		try {
			JsonHandler.printAddressLine1Value(filePath);
			JsonHandler.printAllPhoneNumbers(filePath);
			JsonHandler.getJurisdictionInfoAndupdateJsonFile(filePath);
			if (HelperFunctions.getFileName(filePath).contains("hybrid")) {
				System.out.println("File name is having the word hybrid.");
			} else {
				System.out.println("File name is not having the word hybrid");
			}
			JsonHandler.printAmzonSellerKey(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}