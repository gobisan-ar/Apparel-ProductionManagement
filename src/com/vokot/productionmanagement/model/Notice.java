package com.vokot.productionmanagement.model;

/**
 * This is the Notice model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class Notice {
	private int noticeID;
	private String noticeTitle;
	private String origin;
	private int  quantity;
	private String completion;
	private int status;
	private int productID;
	private int reference;
	private String noticeStatus;
	private ManufacturingProduct manufacturingProduct;
	private Product originProduct;
	private double estBudget;
	
	public Notice() {
		this.manufacturingProduct = new ManufacturingProduct();
	}

	public Notice(int noticeID, String noticeTitle, String origin, int quantity, double estBudget, String completion, int status, int reference, int productID) {
		super();
		this.noticeID = noticeID;
		this.noticeTitle = noticeTitle;
		this.origin = origin;
		this.quantity = quantity;
		this.estBudget = estBudget;
		this.completion = completion;
		this.status = status;
		this.reference = reference;
		this.productID = productID;	
	}
	
	
	public Notice(int noticeID, String noticeTitle, String origin, int quantity, double estBudget, String completion, int reference, int productID) {
		super();
		this.noticeID = noticeID;
		this.noticeTitle = noticeTitle;
		this.origin = origin;
		this.quantity = quantity;
		this.estBudget = estBudget;
		this.completion = completion;
		this.productID = productID;
		this.reference = reference;
	}
	
	public Notice(String noticeTitle, String origin, int quantity, double estBudget, String completion, int productID,
			int reference) {
		super();
		this.noticeTitle = noticeTitle;
		this.origin = origin;
		this.quantity = quantity;
		this.estBudget = estBudget;
		this.completion = completion;
		this.productID = productID;
		this.reference = reference;
	}
	
	public Notice(String noticeTitle, String origin, int quantity, double estBudget, String completion, int status, int productID,
			int reference) {
		super();
		this.noticeTitle = noticeTitle;
		this.origin = origin;
		this.quantity = quantity;
		this.estBudget = estBudget;
		this.completion = completion;
		this.status = status;
		this.productID = productID;
		this.reference = reference;
	}

	public int getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(int noticeID) {
		this.noticeID = noticeID;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getOrigin() {
		return origin.substring(0, 1).toUpperCase() + origin.substring(1);
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public String getNoticeStatus() {
		if(status == 0)
			return "Pending";
		else if(status == 1)
			return "Approved";
		else
			return "Rejected";
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public ManufacturingProduct getManufacturingProduct() {
		return manufacturingProduct;
	}

	public void setManufacturingProduct(ManufacturingProduct manufacturingProduct) {
		this.manufacturingProduct = manufacturingProduct;
	}

	public Product getOriginProduct() {
		return originProduct;
	}

	public void setOriginProduct(Product originProduct) {
		this.originProduct = originProduct;
	}

	public double getEstBudget() {
		return estBudget;
	}

	public void setEstBudget(double estBudget) {
		this.estBudget = estBudget;
	}
}
