package com.vokot.productionmanagement.model;

import java.util.HashMap;

/**
 * This is the Route model class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class Route {
	private int routeID;
	private int productionID;
	private int lineID;
	private HashMap<String, Integer> units;

	
	public Route() {
		this.units = new HashMap<String, Integer>();
	}

	public Route(int routeID, int productionID, int lineID, HashMap<String, Integer> units) {
		super();
		this.routeID = routeID;
		this.productionID = productionID;
		this.lineID = lineID;
		this.units = units;
	}
	
	public Route(int routeID, int productionID, int lineID) {
		super();
		this.routeID = routeID;
		this.productionID = productionID;
		this.lineID = lineID;
	}

	public Route(int productionID, int lineID, HashMap<String, Integer> units) {
		super();
		this.productionID = productionID;
		this.lineID = lineID;
		this.units = units;
	}

	public Route(int productionID, int lineID) {
		super();
		this.productionID = productionID;
		this.lineID = lineID;
	}

	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}

	public int getProductionID() {
		return productionID;
	}

	public void setProductionID(int productionID) {
		this.productionID = productionID;
	}

	public int getLineID() {
		return lineID;
	}

	public void setLineID(int lineID) {
		this.lineID = lineID;
	}

	public HashMap<String, Integer> getUnits() {
		return units;
	}

	public void setUnits(HashMap<String, Integer> units) {
		this.units = units;
	}	
}
