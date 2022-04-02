package com.vokot.productionmanagement.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * This is the Product model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */
public class Product {
	private int productID;
	private String productTitle;
	private int productType;
	private String typeName;
	private int productCollection;
	private String collectionName;
	private double unitCost;
	private String productImage;
	private List<String> colors;
	private List<String> sizeRange;
	
	public Product() {

	}

	public Product(String productTitle, int productType, int productCollection, double unitCost, String productImage) {
		super();
		this.productTitle = productTitle;
		this.productType = productType;
		this.productCollection = productCollection;
		this.unitCost = unitCost;
		this.productImage = productImage;
	}
	
	public Product(int productID, String productTitle) {
		super();
		this.productID = productID;
		this.productTitle = productTitle;
	}
	
	public Product(int productID) {
		super();
		this.productID = productID;
	}

	public Product(int productID, String productTitle, int productType, int productColletion, double unitCost,
			String productImage) {
		super();
		this.productID = productID;
		this.productTitle = productTitle;
		this.productType = productType;
		this.productCollection = productColletion;
		this.unitCost = unitCost;
		this.productImage = productImage;
	}
	
	public Product(String productTitle, List<String> colors, List<String> sizeRange) {
		super();
		this.productTitle = productTitle;
		this.colors = colors;
		this.sizeRange = sizeRange;
	}

	public Product(int productID, String productTitle, List<String> colors, List<String> sizeRange) {
		super();
		this.productID = productID;
		this.productTitle = productTitle;
		this.colors = colors;
		this.sizeRange = sizeRange;
	}
	
	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @param prodcutID the prodcutID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * @return the productTitle
	 */
	public String getProductTitle() {
		return productTitle;
	}

	/**
	 * @param productTitle the productTitle to set
	 */
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	/**
	 * @return the productType
	 */
	public int getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(int productType) {
		this.productType = productType;
	}

	/**
	 * @return the productColletion
	 */
	public int getProductCollection() {

		return productCollection;
	}

	/**
	 * @param productColletion the productColletion to set
	 */
	public void setProductCollection(int productColletion) {
		this.productCollection = productColletion;
	}

	/**
	 * @return the unitCost
	 */
	public double getUnitCost() {
		return unitCost;
	}

	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	/**
	 * @return the productImage name
	 */
	public String getProductImage() {
		return productImage;
	}

	/**
	 * @param productImage the productImage name to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	/**
	 * @return the colors
	 */
	public List<String> getColors() {
		return colors;
	}

	/**
	 * @param colors the list of colors to set
	 */
	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	/**
	 * @return the sizeRange
	 */
	public List<String> getSizeRange() {
		//sizeRange.replaceAll(String::toUpperCase);
		//Collections.reverse(sizeRange);
		
		return sizeRange;
	}

	/**
	 * @param sizeRange the list of sizeRange to set
	 */
	public void setSizeRange(List<String> sizeRange) {
		this.sizeRange = sizeRange;
	}
	
	/**
	 * @return the collectionName
	 */
	public String getCollectionName() {
		return collectionName;
	}

	/**
	 * @param collectionName the collectionName to set
	 */
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		if(productType == 0)
			return "Mens";
		else
			return "Womens";
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}