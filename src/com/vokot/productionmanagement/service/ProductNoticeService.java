package com.vokot.productionmanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vokot.productionmanagement.model.ManufacturingProduct;
import com.vokot.productionmanagement.model.Notice;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductNoticeService class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

public class ProductNoticeService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// Query

	// Insert
	private static final String INSERT_PRODUCTNOTCE_SQL = "INSERT INTO productnotice"
			+ "(notice_title, notice_origin, notice_quantity, estBudget, notice_completion, referenceNumber, product_id) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?);";

	private static final String INSERT_MANUFACTURINGPRODUCT_SQL = "INSERT INTO manufacturingProduct"
			+ "(notice_id, product_id, mfgProduct_color, mfgProduct_size) VALUES " + "(?, ?, ?, ?);";

	// update
	private static final String UPDATE_PRODUCTNOTCE_SQL = "UPDATE productnotice SET notice_title = ?, notice_origin = ?, notice_quantity = ?, estBudget = ?, notice_completion = ?, referenceNumber = ?, product_id = ? WHERE notice_id = ?";
	private static final String UPDATE_NOTICESTATUS_SQL = "UPDATE productnotice SET notice_status = ? WHERE notice_id = ?";
	private static final String UPDATE_MANUFACTURINGPRODUCT_SQL = "UPDATE manufacturingProduct SET product_id = ?, mfgProduct_color = ?, mfgProduct_size = ? WHERE notice_id = ?;";
	
	// Select
	private static final String SELECT_ALL_PRODUCTNOTCES_SQL = "SELECT * FROM productnotice ORDER BY notice_id DESC;";

	// select by ID
	private static final String SELECT_PRODUCTNOTCE_BYID_SQL = "SELECT * FROM productnotice WHERE notice_id = ?;";
	private static final String SELECT_MANUFACTURINGPRODUCT_BYID_SQL = "SELECT * FROM manufacturingProduct WHERE notice_id = ?;";

	// delete
	private static final String DELETE_PRODUCTNOTICE_SQL = "DELETE FROM productnotice WHERE notice_id = ?;";
	private static final String MANUFACTURINGPRODUCT_SQL = "DELETE FROM manufacturingProduct WHERE notice_id = ?;";

	// insert notice
	public int insertNotice(Notice notice) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTNOTCE_SQL,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, notice.getNoticeTitle());
			preparedStatement.setString(2, notice.getOrigin());
			preparedStatement.setInt(3, notice.getQuantity());
			preparedStatement.setDouble(4, notice.getEstBudget());
			preparedStatement.setString(5, notice.getCompletion());
			preparedStatement.setInt(6, notice.getReference());
			preparedStatement.setInt(7, notice.getProductID());
			
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

	// update notice
	public int updateNotice(Notice notice) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTNOTCE_SQL)) {

			preparedStatement.setString(1, notice.getNoticeTitle());
			preparedStatement.setString(2, notice.getOrigin());
			preparedStatement.setInt(3, notice.getQuantity());
			preparedStatement.setDouble(4, notice.getEstBudget());
			preparedStatement.setString(5, notice.getCompletion());
			preparedStatement.setInt(6, notice.getReference());
			preparedStatement.setInt(7, notice.getProductID());
			preparedStatement.setInt(8, notice.getNoticeID());

			// execute the query
			int rowUpdated = preparedStatement.executeUpdate();

			return rowUpdated;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// update notice status
		public int updateNoticeStatus(int noticeID, int status) throws SQLException {
			// establish database connection
			try (Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NOTICESTATUS_SQL)) {

				preparedStatement.setInt(1, status);
				preparedStatement.setInt(2, noticeID);

				// execute the query
				int rowUpdated = preparedStatement.executeUpdate();

				return rowUpdated;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return 0;
		}

	
	// insert manufacturing product
	public int insertManufacturingProduct(ManufacturingProduct mfgProduct) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MANUFACTURINGPRODUCT_SQL,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, mfgProduct.getNoticeID());
			preparedStatement.setInt(2, mfgProduct.getProductID());
			preparedStatement.setString(3, mfgProduct.getColor());
			preparedStatement.setString(4, mfgProduct.getSize());

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

	// update manufacturing product
	public int updateManufacturingProduct(ManufacturingProduct mfgProduct) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MANUFACTURINGPRODUCT_SQL)) {

			preparedStatement.setInt(1, mfgProduct.getProductID());
			preparedStatement.setString(2, mfgProduct.getColor());
			preparedStatement.setString(3, mfgProduct.getSize());
			preparedStatement.setInt(4, mfgProduct.getNoticeID());

			// execute the query
			int rowUpdated = preparedStatement.executeUpdate();

			return rowUpdated;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// select all Notices
	public List<Notice> selectallNotices() {
		List<Notice> notices = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTNOTCES_SQL);) {
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int noticeID = rs.getInt("notice_id");
				String noticeTitle = rs.getString("notice_title");
				String noticeOrigin = rs.getString("notice_origin");
				int noticeQuantity = rs.getInt("notice_quantity");
				double estBudget = rs.getInt("estBudget");
				String noticeCompletion = rs.getString("notice_completion");
				int noticeStatus = rs.getInt("notice_status");
				int referenceNumber = rs.getInt("referenceNumber");
				int product_id = rs.getInt("product_id");

				notices.add(new Notice(noticeID, noticeTitle, noticeOrigin, noticeQuantity, estBudget, noticeCompletion, noticeStatus, referenceNumber, product_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}
	
	// select product by noticeID
	public void selectManufacturingProduct(Notice notice) {
		ManufacturingProduct mfgProduct = new ManufacturingProduct();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement(SELECT_MANUFACTURINGPRODUCT_BYID_SQL);) {
			preparedStatement.setInt(1, notice.getProductID());
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int mfgProductID = rs.getInt("mfgProduct_id");
				int noticeID = rs.getInt("notice_id");
				int productID = rs.getInt("product_id");
				String color = rs.getString("mfgProduct_color");
				String size = rs.getString("mfgProduct_size");

				mfgProduct = new ManufacturingProduct(productID, mfgProductID, noticeID, color, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		notice.setManufacturingProduct(mfgProduct);
	}

	// delete notice
	public boolean deleteNotice(int nid) throws SQLException {
		boolean rowDeleted;
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTNOTICE_SQL);) {
			preparedStatement.setInt(1, nid);
			// execute the query
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// delete notice
	public boolean deleteMfgProduct(int nid) throws SQLException {
		boolean rowDeleted;
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTNOTICE_SQL);) {
			preparedStatement.setInt(1, nid);
			// execute the query
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	

	// select product notice by ID
			public Notice selectNoticeByID(int nid) {
			
				Notice notice = new Notice();
				// get database connection
				try (Connection connection = dbconnection.getConnection();
						// create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTNOTCE_BYID_SQL);) {
						preparedStatement.setInt(1, nid);
					// execute the query
					ResultSet rs = preparedStatement.executeQuery();
					
					while (rs.next()) {
						int noticeID = rs.getInt("notice_id");
						String noticeTitle = rs.getString("notice_title");
						String noticeOrigin = rs.getString("notice_origin");
						int noticeQuantity = rs.getInt("notice_quantity");
						double estBudget = rs.getInt("estBudget");
						String noticeCompletion = rs.getString("notice_completion");
						int noticeStatus = rs.getInt("notice_status");
						int referenceNumber = rs.getInt("referenceNumber");
						int product_id = rs.getInt("product_id");

						notice = new Notice(noticeID, noticeTitle, noticeOrigin, noticeQuantity, estBudget, noticeCompletion, noticeStatus, referenceNumber, product_id);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return notice;
			}

}
