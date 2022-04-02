package com.vokot.productionmanagement.model;

/**
 * This is the ProductionReport model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class ProductionReport {
	private int inspPending;
	private int inspPass;
	private int inspFail;
	
	public ProductionReport() {

	}

	public ProductionReport(int inspPending, int inspPass, int inspFail) {
		super();
		this.inspPending = inspPending;
		this.inspPass = inspPass;
		this.inspFail = inspFail;
	}

	public int getInspPending() {
		return inspPending;
	}

	public void setInspPending(int inspPending) {
		this.inspPending = inspPending;
	}

	public int getInspPass() {
		return inspPass;
	}

	public void setInspPass(int inspPass) {
		this.inspPass = inspPass;
	}

	public int getInspFail() {
		return inspFail;
	}

	public void setInspFail(int inspFail) {
		this.inspFail = inspFail;
	}
}
