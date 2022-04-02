package com.vokot.productionmanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductAnalytics;
import com.vokot.productionmanagement.model.ProductReport;
import com.vokot.productionmanagement.model.ProductionAnalytics;
import com.vokot.productionmanagement.model.ProductionReport;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductionAnalytics Service class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ProductionAnalyticsService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query
	
	// Select
	private static final String COUNT_PRODUCTIONS = "SELECT COUNT(productionID) AS pcount FROM Production WHERE productionStatus = ?;";
	private static final String COUNT_NOTICES = "SELECT COUNT(notice_id) AS ncount FROM productNotice WHERE notice_status = ?;";
	private static final String COUNT_LINES = "SELECT lineID, COUNT(productionID) AS pcount FROM ProductionRoute GROUP BY lineID";
	
	private static final String COUNT_INSPECTION = "SELECT COUNT(productionID) AS picount FROM Production WHERE productionStatus = ? AND inspection = ?;";
	
	// select product metrics
	public ProductionAnalytics selectProductionAnalytics() {
		
		HashMap<String, Integer> countMfgStatus = new HashMap<String, Integer>();
		
		countMfgStatus.put("scheduled", countProductions("scheduled"));
		countMfgStatus.put("running", countProductions("running"));
		countMfgStatus.put("completed", countProductions("completed"));
		
		ProductionAnalytics productionANS = new ProductionAnalytics(
						countProductions("running"),
						countProductions("gate-passed"),
						countRequests(),
						countLines(),
						countMfgStatus);
				
		return productionANS;
	}
	
	public ProductionReport selectProductionReport() {
		ProductionReport productionRep = new ProductionReport(
				countInspections("pending"),
				countInspections("pass"),
				countInspections("fail"));
		
		return productionRep;
	}
	
	private int countProductions(String status) {
		int countProductions = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PRODUCTIONS);) {
						preparedStatement.setString(1, status);
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
						 countProductions = rs.getInt("pcount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return countProductions;
	}
	
	private int countRequests() {
		int countProductions = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_NOTICES);) {
						preparedStatement.setInt(1, 0);
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
						 countProductions = rs.getInt("ncount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return countProductions;
	}
	
	private HashMap<Integer, Integer> countLines() {
		HashMap<Integer, Integer> lineCount = new HashMap<Integer, Integer>();
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_LINES);) {
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					int lineID;
					int pcount;
					// process the ResultSet object
					while (rs.next()) {;
						 lineID = rs.getInt("lineID");
						 pcount = rs.getInt("pcount");
						 
						 lineCount.put(lineID, pcount);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return lineCount;
	}
	
	private int countInspections(String status) {
		int countInspections = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_INSPECTION);) {
					preparedStatement.setString(1, "completed");
					preparedStatement.setString(2, status);
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
					countInspections = rs.getInt("picount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return countInspections;
	}
}
