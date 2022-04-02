package com.vokot.productionmanagement.model;

import java.util.HashMap;

/**
 * This is the Schedule model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class Schedule {
	private int sheduleID;
	private int productionID;
	private String startDate;
	private String endDate;
	
	private HashMap<String, Integer> unitDays;
	
	public Schedule() {
		
	}

	public Schedule(int sheduleID, int productionID, String startDate, String endDate,
			HashMap<String, Integer> unitDays) {
		super();
		this.sheduleID = sheduleID;
		this.productionID = productionID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.unitDays = unitDays;
	}
	
	public Schedule(int sheduleID, int productionID, String startDate, String endDate) {
		super();
		this.sheduleID = sheduleID;
		this.productionID = productionID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Schedule(int productionID, String startDate, String endDate, HashMap<String, Integer> unitDays) {
		super();
		this.productionID = productionID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.unitDays = unitDays;
	}

	public Schedule(int productionID, String startDate, String endDate) {
		super();
		this.productionID = productionID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getSheduleID() {
		return sheduleID;
	}

	public void setSheduleID(int sheduleID) {
		this.sheduleID = sheduleID;
	}

	public int getProductionID() {
		return productionID;
	}

	public void setProductionID(int productionID) {
		this.productionID = productionID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public HashMap<String, Integer> getUnitDays() {
		return unitDays;
	}

	public void setUnitDays(HashMap<String, Integer> unitDays) {
		this.unitDays = unitDays;
	}
}
