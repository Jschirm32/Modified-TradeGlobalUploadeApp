package com.brand.x3.product.model;

public class ProductInfo {
	/*
		This class is a collection of initialized variables
		to represent different fields within a SQL query to
		the AB-SAGEDB-01\X3 Microsoft SQL x3 database
	 */
	private int YPHASE_0;
	private String SKU_ID;
    private String name;
    private String description;
    private String title;
    private String keywords;
    private String specs;
    private String color;
    private String countryOfOrigin;
    private String gender;
    private String material;
    private String UOMSize;
    private String brand;
    private double length, width, height;
    private String UOMWeight;
    private double Weight;
	private double harmonizedTariffCode;
    private String ImageURL;
    private String MerchantStyle;
    private String ciDesc1;
    private String ciDesc2;
    private String ciDesc3;
    private String isMasterProduct;
    private String UseBagPaddedMailer;
    private String isHazmat;
    
    public int getYPHASE_0() {
  		return YPHASE_0;
  	}
  	public void setYPHASE_0(int yPHASE_0) {
  		YPHASE_0 = yPHASE_0;
  	}
	public String getSKU_ID() {
		return SKU_ID;
	}
	public void setSKU_ID(String sKU_ID) {
		SKU_ID = sKU_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getUOMSize() {
		return UOMSize;
	}
	public void setUOMSize(String uOMSize) {
		UOMSize = uOMSize;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getUOMWeight() {
		return UOMWeight;
	}
	public void setUOMWeight(String uOMWeight) {
		UOMWeight = uOMWeight;
	}
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public double getHarmonizedTariffCode() {
		return harmonizedTariffCode;
	}
	public void setHarmonizedTariffCode(double harmonizedTariffCode) {
		this.harmonizedTariffCode = harmonizedTariffCode;
	}
	public String getImageURL() {
		return ImageURL;
	}
	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}
	public String getMerchantStyle() {
		return MerchantStyle;
	}
	public void setMerchantStyle(String merchantStyle) {
		MerchantStyle = merchantStyle;
	}
	public String getCiDesc1() {
		return ciDesc1;
	}
	public void setCiDesc1(String ciDesc1) {
		this.ciDesc1 = ciDesc1;
	}
	public String getCiDesc2() {
		return ciDesc2;
	}
	public void setCiDesc2(String ciDesc2) {
		this.ciDesc2 = ciDesc2;
	}
	public String getCiDesc3() {
		return ciDesc3;
	}
	public void setCiDesc3(String ciDesc3) {
		this.ciDesc3 = ciDesc3;
	}
	
	public String getIsMasterProduct() {
		return isMasterProduct;
	}
	public void setIsMasterProduct(String isMasterProduct) {
		this.isMasterProduct = isMasterProduct;
	}

	public String getUseBagPaddedMailer() {
		return UseBagPaddedMailer;
	}
	public void setUseBagPaddedMailer(String useBagPaddedMailer) {
		UseBagPaddedMailer = useBagPaddedMailer;
	}
	public String getIsHazmat() {
		return isHazmat;
	}
	public void setIsHazmat(String isHazmat) {
		this.isHazmat = isHazmat;
	}
	@Override
	public String toString() {
		return "ProductInfo [YPHASE_0=" + YPHASE_0 + ", SKU_ID=" + SKU_ID + ", name=" + name + ", description="
				+ description + ", title=" + title + ", keywords=" + keywords + ", specs=" + specs + ", color=" + color
				+ ", countryOfOrigin=" + countryOfOrigin + ", gender=" + gender + ", material=" + material
				+ ", UOMSize=" + UOMSize + ", brand=" + brand + ", length=" + length + ", width=" + width + ", height="
				+ height + ", UOMWeight=" + UOMWeight + ", Weight=" + Weight + ", harmonizedTariffCode="
				+ harmonizedTariffCode + ", ImageURL=" + ImageURL + ", MerchantStyle=" + MerchantStyle + ", ciDesc1="
				+ ciDesc1 + ", ciDesc2=" + ciDesc2 + ", ciDesc3=" + ciDesc3 + ", isMasterProduct=" + isMasterProduct
				+ ", UseBagPaddedMailer=" + UseBagPaddedMailer + ", isHazmat=" + isHazmat + "]";
	}
    
	

}   

	
