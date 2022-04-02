package com.vokot.productionmanagement.model;

import java.util.HashMap;

/**
 * This is the ProductionAnalytics model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class ProductionAnalytics {
	private int countProductions;
	private int countGatepasses;
	private int countRequests;
	private HashMap<Integer, Integer> countLines;
	private HashMap<String, Integer> countMfgStatus;
	
	public ProductionAnalytics() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductionAnalytics(int countProductions, int countGatepasses, int countRequests,
			HashMap<Integer, Integer> countLines, HashMap<String, Integer> countMfgStatus) {
		super();
		this.countProductions = countProductions;
		this.countGatepasses = countGatepasses;
		this.countRequests = countRequests;
		this.countLines = countLines;
		this.countMfgStatus = countMfgStatus;
	}

	public ProductionAnalytics(int countProductions, int countGatepasses, int countRequests) {
		super();
		this.countProductions = countProductions;
		this.countGatepasses = countGatepasses;
		this.countRequests = countRequests;
	}

	public int getCountProductions() {
		return countProductions;
	}

	public void setCountProductions(int countProductions) {
		this.countProductions = countProductions;
	}

	public int getCountGatepasses() {
		return countGatepasses;
	}

	public void setCountGatepasses(int countGatepasses) {
		this.countGatepasses = countGatepasses;
	}

	public int getCountRequests() {
		return countRequests;
	}

	public void setCountRequests(int countRequests) {
		this.countRequests = countRequests;
	}

	public HashMap<Integer, Integer> getCountLines() {
		return countLines;
	}

	public void setCountLines(HashMap<Integer, Integer> countLines) {
		this.countLines = countLines;
	}

	public HashMap<String, Integer> getCountMfgStatus() {
		return countMfgStatus;
	}

	public void setCountMfgStatus(HashMap<String, Integer> countMfgStatus) {
		this.countMfgStatus = countMfgStatus;
	}
}
