package com.astralbrands.sage.x3.process;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ExtractBrandProcess implements Processor {

	/*
		Process to retrieve the brand name and
		format the exchange message with the band
		information into '.csv' format file
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Map.Entry<String, String>> brands = exchange.getProperty("BRANDS_LIST", ArrayList.class);
		int index = exchange.getProperty(Exchange.LOOP_INDEX, Integer.class);
		if (index < brands.size()) {
			Map.Entry<String, String> brand = brands.get(index);
			System.out.println("process brand : " + brand);
			String fileName = brand.getKey() + File.separator + brand.getKey() + "_" + getCurrentDate() + ".csv";
			exchange.setProperty("BRAND_NAME", brand.getKey());
			exchange.setProperty("FILE_NAME", fileName);
			//exchange.setProperty("ID_LIST", populateIdList(brand.getValue()));
			exchange.setProperty("isCsvFilePresent", brand.getValue());
			exchange.getMessage().setBody(brand.getValue());
			exchange.getMessage().setHeader(Exchange.FILE_NAME, fileName);
		}
	}

	private ArrayList<String> populateIdList(String csvData) {
		ArrayList<String> ids = new ArrayList<String>();
		System.out.println("csv data:" + csvData);
		String[] rows = csvData.split("\n");
		boolean start = false;
		for (String row : rows) {
			if (start) {
				String[] cols = row.split(",");
				String skuId = cols[0];
				System.out.println("ids :" + skuId);
				ids.add(skuId);
			}
			start = true;
		}
		return ids;
	}

	private String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		System.out.println(LocalDate.now().format(formatter));
		return LocalDate.now().format(formatter);
	}

}
