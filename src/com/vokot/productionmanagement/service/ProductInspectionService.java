package com.vokot.productionmanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vokot.productionmanagement.model.ManufacturingProduct;
import com.vokot.productionmanagement.model.Notice;
import com.vokot.productionmanagement.model.Production;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductInspectionService class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ProductInspectionService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query
	
	// Select
	private static final String SELECT_PRODUCTIONS_BY_STATUS_SQL = "SELECT * FROM production WHERE productionStatus = ?;";
	
	private static final String UPDATE_INSPECTIONSTATUS_SQL = "UPDATE production SET inspection = ? WHERE productionID = ?";
	
	//select Productions
		public List<Production> selectCompProductions(String status) {
			List<Production> productions = new ArrayList<>();
			// get database connection
			try (Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTIONS_BY_STATUS_SQL);) {
					preparedStatement.setString(1, status);
				// execute the query
				ResultSet rs = preparedStatement.executeQuery();

				// process the ResultSet object
				while (rs.next()) {
					int productionID = rs.getInt("productionID");
					String productionStatus = rs.getString("productionStatus");
					int noticeID = rs.getInt("noticeID");
					int supervisor = rs.getInt("supervisor");
					String inspection = rs.getString("inspection");

					productions.add(new Production(productionID, productionStatus, noticeID, supervisor, inspection));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return productions;
		}
		
		// update inspection status
				public int updateInspectionStatus(int productionID, String status) throws SQLException {
					// establish database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSPECTIONSTATUS_SQL)) {

						preparedStatement.setString(1, status);
						preparedStatement.setInt(2, productionID);

						// execute the query
						int rowUpdated = preparedStatement.executeUpdate();
						
						System.out.println("Status-CHECK");

						return rowUpdated;
					} catch (Exception e) {
						e.printStackTrace();
					}

					return 0;
				}
}
