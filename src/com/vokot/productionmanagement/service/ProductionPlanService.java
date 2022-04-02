package com.vokot.productionmanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.Production;
import com.vokot.productionmanagement.model.Route;
import com.vokot.productionmanagement.model.Schedule;
import com.vokot.productionmanagement.model.Supervisor;
import com.vokot.productionmanagement.util.VokotDBConnection;

/**
 * This is the ProductionPlanService class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

public class ProductionPlanService {
	private VokotDBConnection dbconnection = new VokotDBConnection();

	// insert
	private static final String INSERT_PRODUCTION_SQL = "INSERT INTO production" + "(noticeID, supervisor) VALUES "
			+ "(?, ?);";

	private static final String INSERT_SCHEDULE_SQL = "INSERT INTO ProductionSchedule"
			+ "(productionID, startDate, endDate, cutting, printing, sewing ,washing ,pressing ,packaging) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String INSERT_ROUTE_SQL = "INSERT INTO ProductionRoute"
			+ "(lineID, productionID, cutting, printing, sewing ,washing ,pressing ,packaging) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?);";

	// update
	private static final String UPDATE_LINESTATUS_SQL = "UPDATE productionLine SET line_status = ? WHERE lineID = ?";

	private static final String UPDATE_PRODUCTION_SQL = "UPDATE production SET supervisor = ? WHERE productionID = ?";
	
	private static final String UPDATE_PRODUCTIONSTATUS_SQL = "UPDATE production SET productionStatus = ? WHERE productionID = ?";

	private static final String UPDATE_SCHEDULE_SQL = "UPDATE ProductionSchedule SET startDate = ?, endDate = ?, cutting = ?, printing = ?, sewing = ?, washing = ?, pressing = ?, packaging = ? WHERE sheduleID = ?;";

	private static final String UPDATE_ROUTE_SQL = "UPDATE ProductionRoute SET lineID = ?, cutting = ?, printing = ?, sewing = ?, washing = ?, pressing = ?, packaging = ? WHERE routeID = ?;";

	// select
	private static final String SELECT_ALL_PRODUCTIONS_SQL = "SELECT * FROM production ORDER BY productionID DESC;";

	// select by ID
	private static final String SELECT_PRODUCTION_BYID_SQL = "SELECT * FROM production WHERE productionID = ?";
	private static final String SELECT_ROUTE_BYID_SQL = "SELECT * FROM ProductionRoute WHERE productionID = ?";
	private static final String SELECT_SCHEDULE_BYID_SQL = "SELECT * FROM ProductionSchedule WHERE productionID = ?";
	private static final String SELECT_SUPERVISOR_SQL = "SELECT id, name FROM employee WHERE role = ?";
	private static final String SELECT_SUPERVISOR_BYID_SQL = "SELECT supervisor FROM Production WHERE productionID = ?";
	private static final String SELECT_PRODUCTION_SUPERVISOR_SQL = "SELECT name FROM employee WHERE id = ?";

	// delete
	private static final String DELETE_PRODUCTION_SQL = "DELETE FROM production WHERE productionID = ?;";

//insert production
	public int insertProduction(Production production) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTION_SQL,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, production.getNoticeID());
			preparedStatement.setInt(2, production.getSupervisor());

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

	// insert production route
	public int insertRoute(Route route) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROUTE_SQL,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, route.getLineID());
			preparedStatement.setInt(2, route.getProductionID());

			HashMap<String, Integer> units = route.getUnits();

			preparedStatement.setInt(3, units.getOrDefault("cutting", 0));
			preparedStatement.setInt(4, units.getOrDefault("printing", 0));
			preparedStatement.setInt(5, units.getOrDefault("sewing", 0));
			preparedStatement.setInt(6, units.getOrDefault("washing", 0));
			preparedStatement.setInt(7, units.getOrDefault("pessing", 0));
			preparedStatement.setInt(8, units.getOrDefault("packaging", 0));

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

	// insert production schedule
	public int insertSchedule(Schedule schedule) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCHEDULE_SQL,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, schedule.getProductionID());
			preparedStatement.setString(2, schedule.getStartDate());
			preparedStatement.setString(3, schedule.getEndDate());

			HashMap<String, Integer> units = schedule.getUnitDays();

			preparedStatement.setInt(4, units.getOrDefault("cutting", 0));
			preparedStatement.setInt(5, units.getOrDefault("printing", 0));
			preparedStatement.setInt(6, units.getOrDefault("sewing", 0));
			preparedStatement.setInt(7, units.getOrDefault("washing", 0));
			preparedStatement.setInt(8, units.getOrDefault("pressing", 0));
			preparedStatement.setInt(9, units.getOrDefault("packaging", 0));

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

//select all Productions
	public List<Production> selectallProductions() {
		List<Production> productions = new ArrayList<>();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTIONS_SQL);) {
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

	public boolean deleteProduction(int pid) {
		boolean rowDeleted = false;
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTION_SQL);) {
			preparedStatement.setInt(1, pid);
			// execute the query
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowDeleted;
	}

	// select production by ID
	public Production selectProduction(int productionID) {
		Production production = new Production();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTION_BYID_SQL);) {
			preparedStatement.setInt(1, productionID);
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				String productionStatus = rs.getString("productionStatus");
				int noticeID = rs.getInt("noticeID");
				int supervisor = rs.getInt("supervisor");

				production = (new Production(productionID, productionStatus, noticeID, supervisor));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return production;
	}

	// select route by ID
	public Route selectRoute(int productionID) {
		Route route = new Route();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROUTE_BYID_SQL);) {
			preparedStatement.setInt(1, productionID);
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int routeID = rs.getInt("routeID");
				int lineID = rs.getInt("lineID");

				HashMap<String, Integer> units = new HashMap<String, Integer>();

				units.put("cutting", rs.getInt("cutting"));
				units.put("printing", rs.getInt("printing"));
				units.put("sewing", rs.getInt("sewing"));
				units.put("washing", rs.getInt("washing"));
				units.put("pressing", rs.getInt("pressing"));
				units.put("packaging", rs.getInt("packaging"));

				route = (new Route(routeID, productionID, lineID, units));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return route;
	}

	// select schedule by ID
	public Schedule selectSchedule(int productionID) {
		Schedule schedule = new Schedule();
		// get database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SCHEDULE_BYID_SQL);) {
			preparedStatement.setInt(1, productionID);
			// execute the query
			ResultSet rs = preparedStatement.executeQuery();

			// process the ResultSet object
			while (rs.next()) {
				int sheduleID = rs.getInt("sheduleID");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");

				HashMap<String, Integer> units = new HashMap<String, Integer>();

				units.put("cutting", rs.getInt("cutting"));
				units.put("printing", rs.getInt("printing"));
				units.put("sewing", rs.getInt("sewing"));
				units.put("washing", rs.getInt("washing"));
				units.put("pressing", rs.getInt("pressing"));
				units.put("packaging", rs.getInt("packaging"));

				schedule = new Schedule(sheduleID, productionID, startDate, endDate, units);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return schedule;
	}
	
	// select supervisors
		public List<Supervisor> selectSupervisor() {
			List<Supervisor> supervisors = new ArrayList<Supervisor>();
			// get database connection
			try (Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPERVISOR_SQL);) {
				preparedStatement.setString(1, "Product Supervisor");
				// execute the query
				ResultSet rs = preparedStatement.executeQuery();

				// process the ResultSet object
				while (rs.next()) {
					int supID = rs.getInt("id");
					String supName = rs.getString("name");

					supervisors.add(new Supervisor(supID, supName));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return supervisors;
		}
		
		// select supervisor by ID
				public int selectSupervisorByID(int id) {
					int supervisor = 0;
					// get database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPERVISOR_BYID_SQL);) {
						preparedStatement.setInt(1, id);
						// execute the query
						ResultSet rs = preparedStatement.executeQuery();

						// process the ResultSet object
						while (rs.next()) {
							int supID = rs.getInt("supervisor");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					return supervisor;
				}
				
				// select supervisor name
				public Supervisor selectProductionSup(int id) {
					Supervisor supervisor = new Supervisor();
					// get database connection
					try (Connection connection = dbconnection.getConnection();
							// create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTION_SUPERVISOR_SQL);) {
						preparedStatement.setInt(1, id);
						// execute the query
						ResultSet rs = preparedStatement.executeQuery();

						// process the ResultSet object
						while (rs.next()) {
							String supName = rs.getString("name");
							
							supervisor.setSupID(id);
							supervisor.setSupName(supName);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					return supervisor;
				}

	// update production
	public int updateProduction(Production production) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTION_SQL)) {

			preparedStatement.setInt(1, production.getSupervisor());
			preparedStatement.setInt(2, production.getProductionID());

			// execute the query
			int updated = preparedStatement.executeUpdate();
			
			System.out.println("Poduction-CHECK");

			return updated;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	// update production route
	public int updateRoute(Route route) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROUTE_SQL)) {

			preparedStatement.setInt(1, route.getLineID());

			HashMap<String, Integer> units = route.getUnits();

			preparedStatement.setInt(2, units.getOrDefault("cutting", 0));
			preparedStatement.setInt(3, units.getOrDefault("printing", 0));
			preparedStatement.setInt(4, units.getOrDefault("sewing", 0));
			preparedStatement.setInt(5, units.getOrDefault("washing", 0));
			preparedStatement.setInt(6, units.getOrDefault("pessing", 0));
			preparedStatement.setInt(7, units.getOrDefault("packaging", 0));

			preparedStatement.setInt(8, route.getRouteID());
			
			System.out.println(route.getRouteID());

			// execute the query
			int rowUpdated = preparedStatement.executeUpdate();
			
			System.out.println("Route-CHECK");

			return rowUpdated;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// update production schedule
	public int updateSchedule(Schedule schedule) throws SQLException {
		// establish database connection
		try (Connection connection = dbconnection.getConnection();
				// create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCHEDULE_SQL)) {

			preparedStatement.setString(1, schedule.getStartDate());
			preparedStatement.setString(2, schedule.getEndDate());

			HashMap<String, Integer> units = schedule.getUnitDays();

			preparedStatement.setInt(3, units.getOrDefault("cutting", 0));
			preparedStatement.setInt(4, units.getOrDefault("printing", 0));
			preparedStatement.setInt(5, units.getOrDefault("sewing", 0));
			preparedStatement.setInt(6, units.getOrDefault("washing", 0));
			preparedStatement.setInt(7, units.getOrDefault("pressing", 0));
			preparedStatement.setInt(8, units.getOrDefault("packaging", 0));

			preparedStatement.setInt(9, schedule.getSheduleID());

			// execute the query
			int rowUpdated = preparedStatement.executeUpdate();
			
			System.out.println("Schedule-CHECK");

			return rowUpdated;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	// update production status
		public int updateProductionStatus(Production production) throws SQLException {
			// establish database connection
			try (Connection connection = dbconnection.getConnection();
					// create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTIONSTATUS_SQL)) {

				preparedStatement.setString(1, production.getProductionStatus());
				preparedStatement.setInt(2, production.getProductionID());

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
