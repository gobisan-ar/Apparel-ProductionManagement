package com.vokot.productionmanagement.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vokot.productionmanagement.model.Line;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductCollection;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductionLineService class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ProductionLineService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query
	
	// Select
	private static final String SELECT_ALL_LINES_SQL = "SELECT * FROM productionLine;";
	private static final String SELECT_ALL_UNITS_SQL = "SELECT * FROM lineUnits WHERE lineID = ?;";
		
	// select all Products
	public List<Line> selectallLines() {
		List<Line> lines = new ArrayList<>();
		
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LINES_SQL);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				 int lineID = rs.getInt("lineID");
			     String lineType = rs.getString("line_type");
			     int automation = rs.getInt("automation");
			     int lineStatus = rs.getInt("line_status");
			     
				lines.add(new Line(lineID, lineType, automation, lineStatus));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public HashMap<Integer, String> selectallLineUnits(Line line) {
		HashMap<Integer, String> units = new HashMap<Integer, String>();
		
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_UNITS_SQL);) {
				preparedStatement.setInt(1, line.getLineID());
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				 int unitID = rs.getInt("unitID");
			     String unitName = rs.getString("unitName");
			     
				units.put(unitID, unitName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return units;
	}


}