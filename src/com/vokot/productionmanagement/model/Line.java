package com.vokot.productionmanagement.model;

import java.util.HashMap;

/**
 * This is the Line model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class Line {
	private int lineID;
    private String lineType;
    private int automation;
    private int lineStatus;
    private HashMap<Integer, String> lineUnits;
    
    public Line() {
		// TODO Auto-generated constructor stub
	}

	public Line(int lineID, String lineType, int automation, int lineStatus, HashMap<Integer, String> lineUnits) {
		super();
		this.lineID = lineID;
		this.lineType = lineType;
		this.automation = automation;
		this.lineStatus = lineStatus;
		this.lineUnits = lineUnits;
	}
	
	public Line(int lineID, String lineType, int automation, int lineStatus) {
		super();
		this.lineID = lineID;
		this.lineType = lineType;
		this.automation = automation;
		this.lineStatus = lineStatus;
	}

	public int getLineID() {
		return lineID;
	}

	public void setLineID(int lineID) {
		this.lineID = lineID;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public int getAutomation() {
		return automation;
	}

	public void setAutomation(int automation) {
		this.automation = automation;
	}

	public int getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(int lineStatus) {
		this.lineStatus = lineStatus;
	}

	public HashMap<Integer, String> getLineUnits() {
		return lineUnits;
	}

	public void setLineUnits(HashMap<Integer, String> lineUnits) {
		this.lineUnits = lineUnits;
	}
}
