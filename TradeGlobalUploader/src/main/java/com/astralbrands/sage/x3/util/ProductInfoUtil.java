package com.astralbrands.sage.x3.util;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.util.StringUtils;

public class ProductInfoUtil {
	private static final Map<String, String> COUNTRY_ORIGIN_MAP = new HashMap<>();
	static {
		COUNTRY_ORIGIN_MAP.put("2", "CA");
		COUNTRY_ORIGIN_MAP.put("3", "CN");
		COUNTRY_ORIGIN_MAP.put("4", "DE");
		COUNTRY_ORIGIN_MAP.put("5", "IT");
		COUNTRY_ORIGIN_MAP.put("6", "KR");
		COUNTRY_ORIGIN_MAP.put("7", "TW");
		COUNTRY_ORIGIN_MAP.put("8", "US");
		COUNTRY_ORIGIN_MAP.put("9", "FR");
	}

	public static String getUpdateQuery(String brand) {
		String query = "update Test_TGupload set uploadIndex = 1 where Brand='" + brand + "';";
		System.out.println("query :" + query);
		return query;
	}

	/*
		Checks the Map object's value for each key
		to ensure it is a valid format so it can retrieve
		the value + add to StringJoiner then function is
		used to

	 */
	public static boolean checkRequiredField(Map<String, Object> rec) {
		int counting = 0;
		if (rec == null) {
			return false;
		}
		StringJoiner sj = new StringJoiner(",");
		if (isValidStr(rec.get("SKU_ID"))) {
			sj.add("SKU_ID");
		}
		if (isValidStr(rec.get("Name"))) {
			sj.add("Name");
		}
		if (isValidStr(rec.get("Description"))) {
			sj.add("Description");
		}
		if (isValidInt(rec.get("UOMSize"))) {
			sj.add("UOMSize");
		}
		if (isValidInt(rec.get("Length"))) {
			sj.add("Length");
		}
		if (isValidInt(rec.get("Width"))) {
			sj.add("Width");
		}
		if (isValidInt(rec.get("Height"))) {
			sj.add("Height");
		}
		if (isValidInt(rec.get("UOMWeight"))) {
			sj.add("UOMWeight");
		}
		if (isValidInt(rec.get("Weight"))) {
			sj.add("Weight");
		}
		if (isValidInt(rec.get("HarmonizedTariffCode"))) {
			sj.add("HarmonizedTariffCode");
		}
		if (rec.get("isMasterProduct") == null) {
			sj.add("isMasterProduct");
		}
		if (isValidStr(rec.get("brand"))) {
			sj.add("brand");
		}

		if (StringUtils.hasLength(sj.toString())) {
			//for(int i = 0; i <= (sj.toString()).length(); i++) {
			//				counting++;
			//				if(i == (sj.toString().length())){
			//					System.out.println("Valid product info record count: " + counting);
			//				}
			//			}
			return true;
		} else {
			System.out.println("Below fields are empty : " + sj.toString());
			return false;
		}


	}

	/*
		Checks the object if null
		Converts the object to a string
		Ensures the string is populated
	 */
	private static boolean isValidStr(Object stValue) {
		if (stValue == null) {
			return true;
		}
		return StringUtils.hasLength(stValue.toString());
	}

	/*
		Checks the object for any value
		Converts the object to type 'Double'
		Checks the object as a 'Double' type
	 */
	private static boolean isValidInt(Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof Double) {
			Double val = (Double) value;
			if (val == 0.0 || val.intValue() == 0) {
				return true;
			}
		}
		return false;
	}

	/*
		Creates and returns a String populated
		with a product row's value for each column in the DB
		by initializing the format for the String 'q'
		and replaces each value in 'q' for the Map object's value
	 */
	public static String getInsertQuery(Map<String, Object> rec) {
		String q = "Insert Query";
		if (!checkRequiredField(rec)) {
			return null;
		}
		q = "INSERT INTO Test_TGupload"
				+ "(SKU_ID,Name_Prod,Description_Prod,Title,Keywords,Specs,Color,CountryOfOrigin,Gender,Material,UOMSize,Brand,Length_Prod,Width,Height,UOMWeight,Weight_Prod,HarmonizedTariffCode,ImageURL,MerchantStyle,ciDesc1,ciDesc2,ciDesc3,isMasterProduct,UseBagPaddedMailer,isHazmat,uploadIndex) SELECT "
				+ " \'#SKU_ID#\', " + "\'#Name_Prod#\'," + "\'#Description_Prod#\'," + "\'#Title#\',"
				+ "\'#Keywords#\'," + "\'#Specs#\'," + "\'#Color#\'," + "\'#CountryOfOrigin#\'," + "\'#Gender#\',"
				+ "\'#Material#\'," + "\'#UOMSize#\'," + "\'#Brand#\'," + "\'#Length_Prod#\'," + "\'#Width#\',"
				+ "\'#Height#\'," + "\'#UOMWeight#\'," + "\'#Weight_Prod#\'," + "\'#HarmonizedTariffCode#\',"
				+ "\'#ImageURL#\'," + "\'#MerchantStyle#\'," + "\'#ciDesc1#\'," + "\'#ciDesc2#\', " + "\'#ciDesc3#\',"
				+ "\'#isMasterProduct#\'," + "\'#UseBagPaddedMailer#\'," + "\'#isHazmat#\',"
				+ "\'#uploadIndex#\' WHERE NOT EXISTS (SELECT 1 FROM Test_TGupload WHERE SKU_ID = \'#SKU_ID#\' ) ;";

		q = q.replace("#SKU_ID#", getString(rec.get("SKU_ID")));
		q = q.replace("#Name_Prod#", getString(rec.get("Name")));
		q = q.replace("#Description_Prod#", getString(rec.get("Description")));
		q = q.replace("#Title#", getString(rec.get("Title")));
		q = q.replace("#Keywords#", getString(rec.get("Keywords")));
		q = q.replace("#Specs#", getString(rec.get("Specs")));
		q = q.replace("#Color#", getString(rec.get("Color")));
		q = q.replace("#CountryOfOrigin#", getMapVale(rec.get("")));
		q = q.replace("#Gender#", getString(rec.get("Gender")));
		q = q.replace("#Material#", getString(rec.get("Material")));
		q = q.replace("#UOMSize#", getString(rec.get("UOMSize")));
		q = q.replace("#Brand#", getString(rec.get("Brand")));
		q = q.replace("#Length_Prod#", String.valueOf(rec.get("Length")));
		q = q.replace("#Width#", String.valueOf(rec.get("Width")));
		q = q.replace("#Height#", String.valueOf(rec.get("Height")));
		q = q.replace("#Weight_Prod#", String.valueOf(rec.get("Weight")));
		q = q.replace("#UOMWeight#", getString(rec.get("UOMWeight")));
		q = q.replace("#HarmonizedTariffCode#", String.valueOf(rec.get("HarmonizedTariffCode")));
		q = q.replace("#ImageURL#", getString(rec.get("ImageURL")));
		q = q.replace("#MerchantStyle#", getString(rec.get("MerchantStyle")));
		q = q.replace("#ciDesc1#", getString(rec.get("CiDesc1")));
		q = q.replace("#ciDesc2#", getString(rec.get("CiDesc2")));
		q = q.replace("#ciDesc3#", getString(rec.get("CiDesc3")));
		q = q.replaceAll("#isMasterProduct#", getString(rec.get("IsMasterProduct")));
		q = q.replace("#UseBagPaddedMailer#", getString(rec.get("UseBagPaddedMailer")));
		q = q.replace("#uploadIndex#", String.valueOf(0));
		q = q.replace("#isHazmat#", getString(rec.get("IsHazmat")));

		return q;
	}

	public static CharSequence getMapVale(Object value) {
		return value != null ? COUNTRY_ORIGIN_MAP.get(getString(value)) : "";
	}

	public static String getString(Object value) {
		return value != null ? value.toString() : "";
	}


}

