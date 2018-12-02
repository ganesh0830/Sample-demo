package com.project.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonHandler {
	static ObjectMapper mapper = new ObjectMapper();
	static JsonNode rootNode;

	/**
	 * This function prints the value of AddressLine1
	 * @param filePath
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static void printAddressLine1Value(String filePath) throws JsonProcessingException, IOException {
		// String addressLine1;
		rootNode = mapper.readTree(new File(filePath));
		JsonNode addressNode = rootNode.get("contact").get("address");
		Map<String, ?> addressMap = mapper.convertValue(addressNode, Map.class);
		for (Map.Entry<String, ?> entry : addressMap.entrySet()) {
			if (entry.getKey().equals("addressLine1")) {
				System.out.println("addressLine1: " + entry.getValue());
				System.out.println("--------------------------------------------------");
			}
		}
	}

	/**
	 * This function prints the values of phone numbers
	 * @param filePath
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static void printAllPhoneNumbers(String filePath) throws JsonProcessingException, IOException {
		rootNode = mapper.readTree(new File(filePath));
		JsonNode phoneNumbersNode = rootNode.get("contact").get("phoneNumbers");
		Iterator<JsonNode> phoneNumbers = phoneNumbersNode.elements();
		while (phoneNumbers.hasNext()) {
			JsonNode phoneNode = phoneNumbers.next();
			System.out.println("Phone Numbers = " + phoneNode.asLong());
		}
		System.out.println("--------------------------------------------------");
	}

	/**
	 * This functions print Jurisdiction data and update json file for updated dates.
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void getJurisdictionInfoAndupdateJsonFile(String filePath) throws FileNotFoundException, IOException {
		rootNode = mapper.readTree(new File(filePath));
		JsonNode jurisdictionISOCodeNode = rootNode.get("registrations").get("jurisdictionISOCode");
		JsonNode jurisdictionDataNode = jurisdictionISOCodeNode.path("jurisdictionData");
		Iterator<JsonNode> elements = jurisdictionDataNode.elements();
		JsonNode jurisdictionNode;
		int count = 0;
		while (elements.hasNext()) {
			jurisdictionNode = elements.next();
			// System.out.println(jurisdictionNode);
			Map<String, ?> jurisdictionMap = mapper.convertValue(jurisdictionNode, Map.class);

			for (Map.Entry entry : jurisdictionMap.entrySet()) {
				// System.out.println(entry.getKey());
				if (entry.getKey().equals("jurisdiction")) {
					System.out.println("jurisdiction: " + entry.getValue());
					count++;
				}

				if (entry.getKey().equals("isVISACountries")) {
					System.out.println("isVISACountries: " + entry.getValue());
				}

				if (entry.getKey().equals("filingFrequencies")) {
					Map<String, ?> fillingFreqMap = mapper.convertValue(entry.getValue(), Map.class);
					for (Map.Entry<String, ?> entry1 : fillingFreqMap.entrySet()) {
						if (entry1.getKey().equals("ecl")) {
							System.out.println("ecl: " + entry1.getValue());
						}
					}
				}

				if (entry.getKey().equals("importRegime")) {
					System.out.println("importRegime count: " + ((ArrayList) entry.getValue()).size());
				}

				if (entry.getKey().equals("taxOffice")) {
					Map<String, ?> taxOfficeMap = mapper.convertValue(entry.getValue(), Map.class);
					for (Map.Entry<String, ?> entry1 : taxOfficeMap.entrySet()) {
						if (entry1.getKey().equals("name")) {
							System.out.println("taxoffice: " + entry1.getValue());
						}
					}
				}

				if (entry.getKey().equals("visaRegistrationDate")) {
					entry.setValue(HelperFunctions.getTodaysDate());
				}

				if (entry.getKey().equals("registrationDate")) {
					entry.setValue(HelperFunctions.getOneMonthReducedDate());
				}
			}
			System.out.println("--------------------------------------------------");
			((ArrayNode) jurisdictionDataNode).set(count - 1, mapper.convertValue(jurisdictionMap, JsonNode.class));
		}

		String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
		HelperFunctions.updateJsonFileWithIndentation(indented, filePath);
		System.out.println("Total count of jurisdiction is: " + count);
		System.out.println("--------------------------------------------------");
	}

	public static void printAmzonSellerKey(String filePath) throws JsonProcessingException, IOException {
		rootNode = mapper.readTree(new File(filePath));
		System.out.println("Amazon Seller key: " + rootNode.get("amazonSellerKey").asText());
		System.out.println("--------------------------------------------------");
	}
}
