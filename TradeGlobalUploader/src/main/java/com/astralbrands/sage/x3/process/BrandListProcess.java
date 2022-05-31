package com.astralbrands.sage.x3.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.astralbrands.sage.x3.util.ProductInfoUtil;

@Component
public class BrandListProcess implements Processor {

	// String to hold the csv file header data
	@Value("${csv.header}")
	String csvHeader;

	/*
		Process that collects list of brands under Astral in the database
	 */

	@Override
	public void process(Exchange exchange) throws Exception {

		ArrayList<Map<String, Object>> brands = exchange.getIn().getBody(ArrayList.class);
		if (brands == null) {
			System.out.println("empty brandlist, skip the process");
			exchange.setProperty("isBrandListPresent", false);
			return;
		}

		System.out.println("brands " + brands);
		ArrayList<Map.Entry<String, String>> brandListMap = getCsvFileData(brands);
		exchange.setProperty("BRANDS_LIST", brandListMap);
		exchange.setProperty("isBrandListPresent", true);
		exchange.setProperty("CamelLoopSize", brandListMap.size());
	}

	/*
		Function to obtain the data stored in a CSV file |||
		ArrayList populated with HashMap objects |||
		HashMap object contains mapping between Brand name
		and a line containing a product's information with
		each value separated with '~'
	 */
	public ArrayList<Map.Entry<String, String>> getCsvFileData(ArrayList<Map<String, Object>> brands) {
		ArrayList<Map.Entry<String, String>> brandList = new ArrayList<>();
		Map<String, String> csvData = new HashMap<String, String>();
		String brand;
		for (Map<String, Object> map : brands) {

			brand = ProductInfoUtil.getString(map.get("Brand"));
			String data = csvData.get(brand);
			String rowData = populateRowData(map);
			if (data == null) {
				csvData.put(brand, csvHeader + "\n" + rowData);
			} else {
				csvData.put(brand, data + "\n" + rowData);
			}
		}
		for (Map.Entry<String, String> entry : csvData.entrySet()) {
			brandList.add(entry);
		}
		return brandList;
	}

	/*
		Function to retrieve a product's key in the Map object
		and convert corresponding Object value to a String +
		Populate a String with a delimiter ',' after each
		String value for the correlating Map objects value
		- If product info is null return empty String
	 */
	private String populateRowData(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringJoiner sj = new StringJoiner(",");
		sj.add(ProductInfoUtil.getString(map.get("SKU_ID")));
		sj.add(ProductInfoUtil.getString(map.get("Name_Prod")));
		sj.add(ProductInfoUtil.getString(map.get("Description_Prod")));
		sj.add(ProductInfoUtil.getString(map.get("Title")));
		sj.add(ProductInfoUtil.getString(map.get("Keywords")));
		sj.add(ProductInfoUtil.getString(map.get("Specs")));
		sj.add(ProductInfoUtil.getString(map.get("Color")));
		sj.add(ProductInfoUtil.getString(map.get("CountryOfOrigin")));
		sj.add(ProductInfoUtil.getString(map.get("Gender")));
		sj.add(ProductInfoUtil.getString(map.get("Material")));
		sj.add(ProductInfoUtil.getString(map.get("UOMSize")));
		sj.add(ProductInfoUtil.getString(map.get("Brand")));
		sj.add(ProductInfoUtil.getString(map.get("Length_Prod")));
		sj.add(ProductInfoUtil.getString(map.get("Width")));
		sj.add(ProductInfoUtil.getString(map.get("Height")));
		sj.add(ProductInfoUtil.getString(map.get("UOMWeight")));
		sj.add(ProductInfoUtil.getString(map.get("Weight_Prod")));
		sj.add(ProductInfoUtil.getString(map.get("HarmonizedTariffCode")));
		sj.add(ProductInfoUtil.getString(map.get("ImageURL")));
		sj.add(ProductInfoUtil.getString(map.get("MerchantStyle")));
		sj.add(ProductInfoUtil.getString(map.get("ciDesc1")));
		sj.add(ProductInfoUtil.getString(map.get("ciDesc2")));
		sj.add(ProductInfoUtil.getString(map.get("ciDesc3")));
		sj.add(ProductInfoUtil.getString(map.get("isMasterProduct")));
		sj.add(ProductInfoUtil.getString(map.get("UseBagPaddedMailer")));
		sj.add(ProductInfoUtil.getString(map.get("isHazmat")));
		return sj.toString();
	}

}
