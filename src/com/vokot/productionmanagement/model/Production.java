package com.vokot.productionmanagement.model;

/**
 * This is the Production model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class Production {
	private int productionID;
	private String productionStatus;
	private int noticeID;
	private int productID;
	private int supervisor;
	private int lineID;
	private String dueDate;
	private String inspection;
	private String supervisorName;
	
    
	public Production() {
		// TODO Auto-generated constructor stub
	}

	public Production(int productionID, String productionStatus, int noticeID, int supervisor, int lineID,
			String dueDate, String inspection) {
		super();
		this.productionID = productionID;
		this.productionStatus = productionStatus;
		this.noticeID = noticeID;
		this.supervisor = supervisor;
		this.lineID = lineID;
		this.dueDate = dueDate;
		this.inspection = inspection;
	}
	
	public Production(int productionID, String productionStatus, int noticeID, int supervisor, String inspection) {
		super();
		this.productionID = productionID;
		this.productionStatus = productionStatus;
		this.noticeID = noticeID;
		this.supervisor = supervisor;
		this.lineID = lineID;
		this.dueDate = dueDate;
		this.inspection = inspection;
	}



	public Production(int productionID, String productionStatus, int noticeID, int supervisor, int lineID,
			String dueDate) {
		super();
		this.productionID = productionID;
		this.productionStatus = productionStatus;
		this.noticeID = noticeID;
		this.supervisor = supervisor;
		this.lineID = lineID;
		this.dueDate = dueDate;
	}

	public Production(int productionID, String productionStatus, int noticeID, int supervisor) {
		super();
		this.productionID = productionID;
		this.productionStatus = productionStatus;
		this.noticeID = noticeID;
		this.supervisor = supervisor;
	}
	
	public Production(int productionID, String productionStatus) {
		this.productionID = productionID;
		this.productionStatus = productionStatus;
	}
	
	public Production(int productionID, int noticeID, int supervisor) {
		super();
		this.productionID = productionID;
		this.noticeID = noticeID;
		this.supervisor = supervisor;
	}


	public Production(String productionStatus, int noticeID, int supervisor) {
		super();
		this.productionStatus = productionStatus;
		this.noticeID = noticeID;
		this.supervisor = supervisor;
	}

	public Production(int noticeID, int supervisor) {
		super();
		this.noticeID = noticeID;
		this.supervisor = supervisor;
	}
	
	public int getProductionID() {
		return productionID;
	}

	public void setProductionID(int productionID) {
		this.productionID = productionID;
	}

	public String getProductionStatus() {
		return productionStatus.toUpperCase();
	}

	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}

	public int getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(int noticeID) {
		this.noticeID = noticeID;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

	public int getLineID() {
		return lineID;
	}

	public void setLineID(int lineID) {
		this.lineID = lineID;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getInspection() {
		return inspection.toUpperCase();
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
}
