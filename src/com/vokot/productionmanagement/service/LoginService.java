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
import com.vokot.productionmanagement.model.Login;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductCollection;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductionLineService class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class LoginService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query
	
	// Select
	private static final String SELECT_USER_SQL = "SELECT * FROM login WHERE login_username = ? AND login_password = ?;";
		
	// select all Products
	public String validateUser(Login login) {
		String module = "";
		
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL);) {
				preparedStatement.setString(1, login.getUsername());
				preparedStatement.setString(2, login.getPassword());
				
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				module = rs.getString("login_module");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return module;
	}
}