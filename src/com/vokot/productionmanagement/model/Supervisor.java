package com.vokot.productionmanagement.model;

/**
 * This is the Supervisor model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class Supervisor {
	int supID;
	String supName;
	
	public Supervisor() {
		// TODO Auto-generated constructor stub
	}

	public Supervisor(int supID, String supName) {
		super();
		this.supID = supID;
		this.supName = supName;
	}

	public int getSupID() {
		return supID;
	}

	public void setSupID(int supID) {
		this.supID = supID;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}
}
