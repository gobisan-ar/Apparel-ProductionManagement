package com.vokot.productionmanagement.model;

import java.util.HashMap;

/**
 * This is the ProductCollection model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */
public class ProductCollection {
	private int collectionID;
	private String collectionTitle;
	private HashMap<String, Integer> rawMaterials;
	
	public ProductCollection() {

	}

	public ProductCollection(int collectionID, String collectionTitle, HashMap<String, Integer> rawMaterials) {
		this.collectionID = collectionID;
		this.collectionTitle = collectionTitle;
		this.rawMaterials = rawMaterials;
	}
	
	public ProductCollection(int collectionID, String collectionTitle) {
		this.collectionID = collectionID;
		this.collectionTitle = collectionTitle;
	}

	public int getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}

	public String getCollectionTitle() {
		return collectionTitle;
	}

	public void setCollectionTitle(String collectionTitle) {
		collectionTitle = collectionTitle;
	}

	public HashMap<String, Integer> getRawMaterials() {
		return rawMaterials;
	}

	public void setRawMaterials(HashMap<String, Integer> rawMaterials) {
		this.rawMaterials = rawMaterials;
	}
}
