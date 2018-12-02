package com.project.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HelperFunctions {
	static Calendar cal;
	static SimpleDateFormat dateOnly;

	/**
	 * This function return Todays date in yyyy-MM-dd format
	 * 
	 * @return
	 */
	public static String getTodaysDate() {
		cal = Calendar.getInstance();
		dateOnly = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateOnly.format(cal.getTime());
		return date;
	}

	/**
	 * This function return date reduced by 1 month
	 * 
	 * @return
	 */
	public static String getOneMonthReducedDate() {
		cal = Calendar.getInstance();
		dateOnly = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.MONTH, -1);
		String date = dateOnly.format(cal.getTime());
		return date;
	}

	/**
	 * This function returns write the json contents to file
	 * 
	 * @param jsonString
	 * @param filePath
	 * @throws IOException
	 */
	public static void updateJsonFileWithIndentation(String jsonString, String filePath) throws IOException {
		FileWriter file = new FileWriter(filePath);
		try {
			file.write(jsonString);
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	/**
	 * This function return the file name for provided file path
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		Path path = Paths.get(filePath);
		return path.getFileName().toString();
	}
}