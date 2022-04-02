package com.vokot.productionmanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vokot.productionmanagement.model.Line;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductAnalytics;
import com.vokot.productionmanagement.model.ProductReport;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductAnalytics Service class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ProductAnalyticsService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query
	
	// Select
	private static final String COUNT_PRODUCTS = "SELECT COUNT(product_id) AS pcount FROM product;";
	private static final String COUNT_PENDING_NOTICE = "SELECT COUNT(notice_id) AS pncount FROM productNotice WHERE notice_status = ?;";
	private static final String COUNT_PENDING_INSPECTION = "SELECT COUNT(noticeID) AS picount FROM Production WHERE productionStatus = ? AND inspection = ?;";
	private static final String COUNT_COLLECTION = "SELECT COUNT(product_id) AS ccount FROM product WHERE product_collection = ?;";
	
	private static final String COUNT_NOTICE = "SELECT COUNT(notice_id) AS ncount FROM productNotice WHERE notice_status = ?;";

	private static final String COUNT_GENDER_COLLECTION = "SELECT COUNT(product_id) AS gcount FROM product WHERE product_type = ? AND product_collection = ?;";
	
	private static final String HIGH_COST_PRODUCT = "SELECT * FROM product WHERE product_unitcost = (SELECT MAX(product_unitcost) FROM product)";
	
	private static final String LOW_COST_PRODUCT = "SELECT * FROM product WHERE product_unitcost = (SELECT MIN(product_unitcost) FROM product);";
	
	private static final String UNITCOST = "SELECT product_unitcost FROM product;";
	
	// select product metrics
	public ProductAnalytics selectProductAnalytics() {
		HashMap<String, Integer> collectionCount = new HashMap<String, Integer>();
		
		collectionCount.put("T-Shirt", countCollection(1));
		collectionCount.put("Shirt", countCollection(2));
		collectionCount.put("Hoodie", countCollection(3));
		
		HashMap<String, Integer> noticeCount = new HashMap<String, Integer>();
		
		noticeCount.put("Approved", countNotices(-1));
		noticeCount.put("Pending", countNotices(0));
		noticeCount.put("Rejected", countNotices(1));
		
		ProductAnalytics productANS = new ProductAnalytics(
				countProducts(), countPendingNotices(), countPendingInspections(), 
				collectionCount, noticeCount);
		
		return productANS;
	}
	
	// select product gender collection metrics
		public ProductReport selectGenderCollection() {
			
			ProductReport productRep = new ProductReport(genderCollectionCount(0,1),
						genderCollectionCount(0,2),
						genderCollectionCount(0,3),
						genderCollectionCount(1,1),
						genderCollectionCount(1,2),
						genderCollectionCount(1,3)
					);
			
			return productRep;
		}
	
	private int countProducts() {
		int countProducts = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PRODUCTS);) {
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
						 countProducts = rs.getInt("pcount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return countProducts;
	}
	
	private int countPendingNotices() {
		int countNotices = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PENDING_NOTICE);) {
					preparedStatement.setString(1, "pending");
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
						 countNotices = rs.getInt("pncount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return countNotices;
	}
	
	private int countPendingInspections() {
		int countInspections = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PENDING_INSPECTION);) {
					preparedStatement.setString(1, "completed");
					preparedStatement.setString(2, "pending");
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
	
	private int countCollection(int collection) {
		int countCollections = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_COLLECTION);) {
					preparedStatement.setInt(1, collection);
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
					countCollections = rs.getInt("ccount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return countCollections;
	}
	
	private int countNotices(int status) {
		int countCollections = 0;
		// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(COUNT_NOTICE);) {
					preparedStatement.setInt(1, status);
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();

					// process the ResultSet object
					while (rs.next()) {;
					countCollections = rs.getInt("ncount");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return countCollections;
	}
	
	private int genderCollectionCount(int gender, int collection) {
		int count = 0;
		
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(COUNT_GENDER_COLLECTION);) {
			preparedStatement.setInt(1, gender);
			preparedStatement.setInt(2, collection);
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {;
				count = rs.getInt("gcount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<Product> highCostProducts() {
		List<Product> products = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(HIGH_COST_PRODUCT);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int productID = rs.getInt("product_id");
				String title = rs.getString("product_title");
				double unitCost = rs.getDouble("product_unitcost");
				int collection = rs.getInt("product_collection");
				
				Product product = new Product();
				
				product.setProductID(productID);
				product.setProductTitle(title);
				product.setUnitCost(unitCost);
				product.setProductCollection(collection);
				
				products.add(product);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Product> lowCostProducts() {
		List<Product> products = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(LOW_COST_PRODUCT);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int productID = rs.getInt("product_id");
				String title = rs.getString("product_title");
				double unitCost = rs.getDouble("product_unitcost");
				int collection = rs.getInt("product_collection");
				
				Product product = new Product();
				
				product.setProductID(productID);
				product.setProductTitle(title);
				product.setUnitCost(unitCost);
				product.setProductCollection(collection);
				
				products.add(product);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Double> unitCost() {
		List<Double> cost = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UNITCOST);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				cost.add(rs.getDouble("product_unitcost"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cost;
	}
}
