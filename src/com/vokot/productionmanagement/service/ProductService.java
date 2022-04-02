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

import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductCollection;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductService class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class ProductService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query
	
	//Insert
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO product"
			+ "(product_title, product_type, product_collection, product_image, product_unitCost) VALUES "
			+ "(?, ?, ?, ?, ?);";
	
	private static final String INSERT_PRODUCTCOLOR_SQL = "INSERT INTO productColor"
			+ "(product_id, product_color) VALUES " + "(?, ?);";
	
	private static final String INSERT_PRODUCTSIZE_SQL = "INSERT INTO productSize"
			+ "(product_id, product_size) VALUES " + "(?, ?);";
	
	private static final String INSERT_GATEPASSEDPRODUCT_SQL = "INSERT INTO GatePassedProduct"
			+ "(noticeID, productionID, productID, quantity) VALUES "
			+ "(?, ?, ?, ?);";
	
	private static final String UPDATE_PRODUCT_SQL = "UPDATE product SET product_title = ?, product_type = ?, product_collection = ?, product_image = ?, product_unitCost = ? WHERE product_id = ?";
	
	// Select
	private static final String SELECT_ALL_PRODUCTS_SQL = "SELECT * FROM Product ORDER BY product_id DESC;";
	private static final String SELECT_ALL_PRODUCT_COLORS_SQL = "SELECT * FROM ProductColor WHERE product_id = ?;";
	private static final String SELECT_ALL_PRODUCT_SIZES_SQL = "SELECT * FROM ProductSize WHERE product_id = ?;";
	private static final String SELECT_ALL_PRODUCTCOLLECTIONS_SQL = "SELECT * FROM ProductCollection;";
	private static final String SELECT_MATERIAL_UNITSBYID_SQL = "SELECT U.rawmaterial_id, R.materialName, U.material_qty FROM MaterialUnits U, RawMaterial R WHERE U.rawmaterial_id =  R.rawmaterials_id AND U.productCollection_id = ?;";
	
	private static final String SELECT_PRODUCTCOLLECTION_BYID_SQL = "SELECT productCollection_name FROM ProductCollection WHERE productCollection_id = ?;";
	private static final String SELECT_PRODUCT_BYID_SQL = "SELECT * FROM Product WHERE product_id = ?;";
	
	private static final String DELETE_PRODUCT_SQL = "DELETE FROM product WHERE product_id = ?;";
	private static final String DELETE_PRODUCTCOLOR_SQL = "DELETE FROM productColor WHERE product_id = ?;";
	private static final String DELETE_PRODUCTSIZE_SQL = "DELETE FROM productSize WHERE product_id = ?;";
	
	// select all Products
	public List<Product> selectallProducts() {
		List<Product> products = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS_SQL);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int productID = rs.getInt("product_id");

				String productTitle = rs.getString("product_title");
				int productType = rs.getInt("product_type");
				int productCollection = rs.getInt("product_collection");
				double unitCost = rs.getDouble("product_unitCost");
				String productImage = rs.getString("product_image");

				products.add(
						new Product(productID, productTitle, productType, productCollection, unitCost, productImage));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	// select product colors
	public void selectallColors(Product product) {
		List<String> productColors = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_COLORS_SQL);) {
			preparedStatement.setInt(1, product.getProductID());
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				String color = rs.getString("product_color");
				productColors.add(color);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		product.setColors(productColors);
	}

	// select product sizes
	public void selectallSizes(Product product) {
		List<String> productSizes = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_SIZES_SQL);) {
			preparedStatement.setInt(1, product.getProductID());
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				String size = rs.getString("product_size");
				productSizes.add(size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		product.setSizeRange(productSizes);
	}

	// select product collections
	public List<ProductCollection> selectallProductCollections() {
		List<ProductCollection> productCollections = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTCOLLECTIONS_SQL);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int collectionID = rs.getInt("productcollection_id");
				String collectionName = rs.getString("productcollection_name");

				productCollections.add(new ProductCollection(collectionID, collectionName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productCollections;
	}

	// insert product
	public int insertProduct(Product product) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL,
						Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, product.getProductTitle());
			preparedStatement.setInt(2, product.getProductType());
			preparedStatement.setInt(3, product.getProductCollection());
			preparedStatement.setString(4, product.getProductImage());
			preparedStatement.setDouble(5, product.getUnitCost());
			// execute the query
			preparedStatement.executeUpdate();

			int newID = 0;

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				newID = rs.getInt(1);
				return newID;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	// update product
		public int updateProduct(Product product) throws SQLException {
			// establish database connection
			try (Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
				preparedStatement.setString(1, product.getProductTitle());
				preparedStatement.setInt(2, product.getProductType());
				preparedStatement.setInt(3, product.getProductCollection());
				preparedStatement.setString(4, product.getProductImage());
				preparedStatement.setDouble(5, product.getUnitCost());
				preparedStatement.setInt(6, product.getProductID());
				// execute the query
				int rowUpdated = preparedStatement.executeUpdate();
				
				return rowUpdated;
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return 0;
		}

	// insert product color
	public void insertProductColor(int productID, String color) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTCOLOR_SQL)) {
			preparedStatement.setInt(1, productID);
			preparedStatement.setString(2, color);
			// execute the query
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// insert product color
	public void insertProductSize(int productID, String size) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTSIZE_SQL)) {
			preparedStatement.setInt(1, productID);
			preparedStatement.setString(2, size);
			// execute the query
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete product
		public boolean deleteProduct(int pid) throws SQLException{
			boolean rowDeleted;
			// establish database connection
			try(Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);){
				preparedStatement.setInt(1, pid);
				// execute the query
				rowDeleted = preparedStatement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		// delete product colors
		public boolean deleteProductColor(int pid) throws SQLException{
			boolean rowDeleted;
			// establish database connection
			try(Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTCOLOR_SQL);){
				preparedStatement.setInt(1, pid);
				// execute the query
				rowDeleted = preparedStatement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		// delete product size
				public boolean deleteProductSize(int pid) throws SQLException{
					boolean rowDeleted;
					// establish database connection
					try(Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTSIZE_SQL);){
						preparedStatement.setInt(1, pid);
						// execute the query
						rowDeleted = preparedStatement.executeUpdate() > 0;
					}
					return rowDeleted;
				}
		
		// select collection by ID
		public void selectProductCollection(Product product) {
			String collectionName = new String();
			// get database connection
			try (Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTCOLLECTION_BYID_SQL);) {
					preparedStatement.setInt(1, product.getProductCollection());
				// execute the query
				ResultSet rs = preparedStatement.executeQuery();

				// process the ResultSet object
				while (rs.next()) {
					 collectionName = rs.getString("productcollection_name");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			product.setCollectionName(collectionName);
		}
		
		// select product by ID
				public Product selectProductByID(int pid) {
					Product product = new Product();
					// get database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BYID_SQL);) {
							preparedStatement.setInt(1, pid);
						// execute the query
						ResultSet rs = preparedStatement.executeQuery();

						// process the ResultSet object
						while (rs.next()) {
							String productTitle = rs.getString("product_title");
							int productType = rs.getInt("product_type");
							int productCollection = rs.getInt("product_collection");
							double unitCost = rs.getDouble("product_unitCost");
							String productImage = rs.getString("product_image");

							product = new Product(pid, productTitle, productType, productCollection, unitCost, productImage);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					return product;
				}
				
				// list collections
				public List<ProductCollection> selectallCollections() {
					List<ProductCollection> collections = new ArrayList<>();
					// get database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTCOLLECTIONS_SQL);) {
						// execute the query
						ResultSet rs = preparedStatement.executeQuery();

						// process the ResultSet object
						while (rs.next()) {
							int collectionID = rs.getInt("productCollection_id");
							String collectionTitle = rs.getString("productCollection_Name");

							collections.add(new ProductCollection(collectionID, collectionTitle));

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return collections;
				}
				
				public void selectCollectionMaterials(ProductCollection collection) {
					HashMap<String, Integer> materialUnits = new HashMap<>();
					// get database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATERIAL_UNITSBYID_SQL);) {
						preparedStatement.setInt(1, collection.getCollectionID());
						// execute the query
						ResultSet rs = preparedStatement.executeQuery();

						// process the ResultSet object
						while (rs.next()) {
							int materialID = rs.getInt("rawmaterials_id");
							String materialTitle = rs.getString("rawmaterials_name");
							int materialQty = rs.getInt("material_qty");

							materialUnits.put(materialTitle, materialQty);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					collection.setRawMaterials(materialUnits);
				}
				
				// insert gate-passed product
				public int insertGatePassedProduct(int noticeID, int productionID, int productID, int quantity) throws SQLException {
					// establish database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GATEPASSEDPRODUCT_SQL,
									Statement.RETURN_GENERATED_KEYS)) {
						preparedStatement.setInt(1, noticeID);
						preparedStatement.setInt(2, productionID);
						preparedStatement.setInt(3, productID);
						preparedStatement.setInt(4, quantity);
						
						// execute the query
						preparedStatement.executeUpdate();

						int newID = 0;

						ResultSet rs = preparedStatement.getGeneratedKeys();
						if (rs.next()) {
							newID = rs.getInt(1);
							return newID;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					return 0;
				}
}
