package com.vokot.productionmanagement.model;

import java.util.List;

/**
 * This is the ManufacturingProduct model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ManufacturingProduct extends Product{
	private int mfgProductID;
	private int noticeID;
	private Production production;
	private String color;
	private String size;
	
	public ManufacturingProduct() {
		
	}
	
	public ManufacturingProduct(int  mfgProductID, int productID, String productTitle, String color, String size, int noticeID) {
		super(productID, productTitle);
		this.mfgProductID = mfgProductID;
		this.color = color;
		this.size = size;
		this.noticeID = noticeID;
	}
	
	public ManufacturingProduct(int productID,int noticeID, String color, String size) {
		super(productID);
		this.mfgProductID = mfgProductID;
		this.color = color;
		this.size = size;
		this.noticeID = noticeID;
	}
	
	public ManufacturingProduct(int mfgProductID, int productID,int noticeID, String color, String size) {
		super(productID);
		this.mfgProductID = mfgProductID;
		this.color = color;
		this.size = size;
		this.noticeID = noticeID;
	}

	public int getMfgProductID() {
		return mfgProductID;
	}

	public void setMfgProductID(int mfgProductID) {
		this.mfgProductID = mfgProductID;
	}

	public int getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(int noticeID) {
		this.noticeID = noticeID;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}
	
	
}
