package com.vokot.productionmanagement.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.vokot.productionmanagement.model.Line;
import com.vokot.productionmanagement.model.Notice;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductCollection;
import com.vokot.productionmanagement.model.Production;
import com.vokot.productionmanagement.model.Route;
import com.vokot.productionmanagement.model.Schedule;
import com.vokot.productionmanagement.model.Supervisor;
import com.vokot.productionmanagement.service.ProductNoticeService;
import com.vokot.productionmanagement.service.ProductService;
import com.vokot.productionmanagement.service.ProductionLineService;
import com.vokot.productionmanagement.service.ProductionPlanService;

/**
 * This is the ProductionServlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

/**
 * Servlet implementation class ProductionServlet
 */
@WebServlet(urlPatterns = { "/planForm-create", "/productionPlan-create", "/productionNotice-view",
		"/productionRequest-view", "/noticeStatus-update", "/plan-create", "/productionStatus-view", "/production-delete", "/production-edit", "/planUpdate", "/productionStatus-update"})
public class ProductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductNoticeService noticeDAO;
	private ProductService productDAO;
	private ProductionPlanService planDAO;
	private ProductionLineService lineDAO;
	private ProductionPlanService productionDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductionServlet() {
		super();
		this.noticeDAO = new ProductNoticeService();
		this.productDAO = new ProductService();
		this.lineDAO = new ProductionLineService();
		this.planDAO = new ProductionPlanService();
		this.productionDAO = new ProductionPlanService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();

		switch (action) {
		case "/productionNotice-view":
			try {
				productionNoticeView(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/productionRequest-view":
			try {
				productionRequest(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/noticeStatus-update":
			try {
				noticeStatusUpdate(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
			
		case "/planForm-create":
			try {
				productionPlanFormCreate(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/plan-create":
			try {
				productionPlanCreate(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/productionStatus-view":
			try {
				productionStatusView(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/production-delete":
			try {
				deleteProduction(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/production-edit":
			try {
				productionEdit(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/planUpdate":
			try {
				updateProduction(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/productionStatus-update":
			try {
				updateProductionStatus(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void productionNoticeView(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int noticeID = Integer.parseInt(request.getParameter("nid"));

		Notice notice = noticeDAO.selectNoticeByID(noticeID);
		noticeDAO.selectManufacturingProduct(notice);

		notice.setOriginProduct(productDAO.selectProductByID(notice.getProductID()));
		
		productDAO.selectProductCollection(notice.getOriginProduct());
		
		request.setAttribute("notice", notice);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productionManagement/productionNotice-view.jsp");

		dispatcher.forward(request, response);
	}

	private void productionRequest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		// String msg = request.getAttribute("msg").toString();

		List<Notice> noticeList = noticeDAO.selectallNotices();
		Product product = new Product();

		for (Notice notice : noticeList) {
			noticeDAO.selectManufacturingProduct(notice);

			product = productDAO.selectProductByID(notice.getProductID());
			productDAO.selectProductCollection(product);

			notice.setOriginProduct(product);
		}

		request.setAttribute("noticeList", noticeList);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productionManagement/productionRequest-view.jsp");

		dispatcher.forward(request, response);
	}
	
	private void noticeStatusUpdate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		int noticeID = Integer.parseInt(request.getParameter("nid").toString());
		int status = Integer.parseInt(request.getParameter("sts").toString());
		
		noticeDAO.updateNoticeStatus(noticeID, status);

		HttpSession session = request.getSession();
		
		if(status == -1) {
			session.setAttribute("message", "Notice Rejected");
		}else if(status == 0) {
			session.setAttribute("message", "Notice Pending");
		}

		productionRequest(request, response);
	}

	private void productionPlanFormCreate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int noticeID = Integer.parseInt(request.getParameter("nid"));

		List<Line> lineList = lineDAO.selectallLines();

		for (Line line : lineList) {
			line.setLineUnits(lineDAO.selectallLineUnits(line));
		}

		List<Supervisor> supervisorlist = planDAO.selectSupervisor();
		
		HttpSession session = request.getSession();
		session.setAttribute("nid", noticeID);
		
		request.setAttribute("lineList", lineList);
		request.setAttribute("supervisorlist", supervisorlist);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productionManagement/productionPlan-create.jsp");

		dispatcher.forward(request, response);
	}

	// insert production plan
	private void productionPlanCreate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int noticeID = Integer.parseInt(request.getParameter("nid"));

		int lineID = Integer.parseInt(request.getParameter("line"));
		int supervisor = Integer.parseInt(request.getParameter("supervisor"));
		String[] units = request.getParameterValues("unit[]");

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		int cuttingDays = Integer.parseInt(request.getParameter("cuttingDays"));
		int printingDays = Integer.parseInt(request.getParameter("printingDays"));
		int sewingDays = Integer.parseInt(request.getParameter("sewingDays"));
		int washingDays = Integer.parseInt(request.getParameter("washingDays"));
		int pressingDays = Integer.parseInt(request.getParameter("pressingDays"));
		int packagingDays = Integer.parseInt(request.getParameter("packagingDays"));

		String message = new String();

		try {

			Production production = new Production(noticeID, supervisor);

			int newID = planDAO.insertProduction(production);

			if (newID > 0) {
				message = "Production Successfully Scheduled!";

				Route route = new Route(newID, lineID);

				HashMap<String, Integer> productionUnits = new HashMap<String, Integer>();

				for (String unit : units) {
					if (unit.equals("cutting"))
						productionUnits.put("cutting", 1);

					else if (unit.equals("printing"))
						productionUnits.put("printing", 1);

					else if (unit.equals("sewing"))
						productionUnits.put("sewing", 1);

					else if (unit.equals("washing"))
						productionUnits.put("washing", 1);

					else if (unit.equals("pressing"))
						productionUnits.put("pressing", 1);

					else if (unit.equals("packaging"))
						productionUnits.put("packaging", 1);
				}
				
				noticeDAO.updateNoticeStatus(noticeID, 1);

				route.setUnits(productionUnits);

				planDAO.insertRoute(route);

				Schedule schedule = new Schedule(newID, startDate, endDate);

				HashMap<String, Integer> unitDays = new HashMap<String, Integer>();

				unitDays.put("cutting", cuttingDays);
				unitDays.put("printing", printingDays);
				unitDays.put("sewing", sewingDays);
				unitDays.put("washing", washingDays);
				unitDays.put("pressing", pressingDays);
				unitDays.put("packaging", packagingDays);

				schedule.setUnitDays(unitDays);

				planDAO.insertSchedule(schedule);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("message", message);

		response.sendRedirect("productionStatus-view");
	}

	private void productionStatusView(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Production> productionList = planDAO.selectallProductions();
		
		for(Production production : productionList) {
			int productionID = production.getProductionID();
			
			int supID = production.getSupervisor();
			
			production.setLineID(planDAO.selectRoute(productionID).getLineID());
			production.setDueDate(planDAO.selectSchedule(productionID).getEndDate());
			production.setSupervisorName(planDAO.selectProductionSup(supID).getSupName());
		}

		request.setAttribute("productionList", productionList);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productionManagement/productionStatus-view.jsp");

		dispatcher.forward(request, response);
	}

	// delete production
	private void deleteProduction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		planDAO.deleteProduction(pid);

		HttpSession session = request.getSession();
		session.setAttribute("message", "Production successfully droped!");

		response.sendRedirect("productionStatus-view");
	}

	// navigate to production update form
	private void productionEdit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int productionID = Integer.parseInt(request.getParameter("pid"));

		Production production = planDAO.selectProduction(productionID);
		List<Line> lineList = lineDAO.selectallLines();
		Route route = planDAO.selectRoute(productionID);
		Schedule shcedule = planDAO.selectSchedule(productionID);
		int supervisor = planDAO.selectSupervisorByID(productionID);
		List<Supervisor> supervisorlist = planDAO.selectSupervisor();

		request.setAttribute("production", production);
		request.setAttribute("lineList", lineList);
		request.setAttribute("route", route);
		request.setAttribute("schedule", shcedule);
		request.setAttribute("supervisor", supervisor);
		request.setAttribute("supervisorlist", supervisorlist);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productionManagement/productionPlan-update.jsp");

		dispatcher.forward(request, response);
	}

	// update production
	private void updateProduction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int productionID = Integer.parseInt(request.getParameter("productionID"));
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		int routeID = Integer.parseInt(request.getParameter("routeID"));
		int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));

		int lineID = Integer.parseInt(request.getParameter("line"));
		int supervisor = Integer.parseInt(request.getParameter("supervisor"));
		String[] units = request.getParameterValues("unit[]");

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		int cuttingDays = Integer.parseInt(request.getParameter("cuttingDays"));
		int printingDays = Integer.parseInt(request.getParameter("printingDays"));
		int sewingDays = Integer.parseInt(request.getParameter("sewingDays"));
		int washingDays = Integer.parseInt(request.getParameter("washingDays"));
		int pressingDays = Integer.parseInt(request.getParameter("pressingDays"));
		int packagingDays = Integer.parseInt(request.getParameter("packagingDays"));

		String message = new String();

		Production production = new Production(productionID, noticeID, supervisor);
	

			int rowUpdated = planDAO.updateProduction(production);

			if (rowUpdated > 0) {
				System.out.println("CHECK");
				message = "Production Successfully Updated!";

				Route route = new Route(routeID, productionID, lineID);

				HashMap<String, Integer> productionUnits = new HashMap<String, Integer>();

				for (String unit : units) {
					if (unit.equals("cutting"))
						productionUnits.put("cutting", 1);

					else if (unit.equals("printing"))
						productionUnits.put("printing", 1);

					else if (unit.equals("sewing"))
						productionUnits.put("sewing", 1);

					else if (unit.equals("washing"))
						productionUnits.put("washing", 1);

					else if (unit.equals("pressing"))
						productionUnits.put("pressing", 1);

					else if (unit.equals("packaging"))
						productionUnits.put("packaging", 1);
				}

				route.setUnits(productionUnits);

				planDAO.updateRoute(route);

				Schedule schedule = new Schedule(scheduleID ,productionID, startDate, endDate);

				HashMap<String, Integer> unitDays = new HashMap<String, Integer>();

				unitDays.put("cutting", cuttingDays);
				unitDays.put("printing", printingDays);
				unitDays.put("sewing", sewingDays);
				unitDays.put("washing", washingDays);
				unitDays.put("pressing", pressingDays);
				unitDays.put("packaging", packagingDays);

				schedule.setUnitDays(unitDays);

				planDAO.updateSchedule(schedule);
			}


		HttpSession session = request.getSession();
		session.setAttribute("message", message);

		response.sendRedirect("productionStatus-view");
	}
	
	// update production status
		private void updateProductionStatus(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException {
			
			int productionID = Integer.parseInt(request.getParameter("productionID"));
			String status = request.getParameter("productionStatus");

			String message = new String();

			Production production = new Production(productionID, status);
		

				int rowUpdated = planDAO.updateProductionStatus(production);

				if (rowUpdated > 0) {
					message = "Production Status Successfully Updated!";
					
					if(status.equals("gate-passed")) {
						Production gatepassed = productionDAO.selectProduction(productionID);
						
						int noticeID = gatepassed.getNoticeID();
						Notice notice = noticeDAO.selectNoticeByID(noticeID);
						
						int productID = notice.getProductID();
						int quantity = notice.getQuantity();
						
						productDAO.insertGatePassedProduct(noticeID, productionID, productID, quantity);
						
						message = "Gate-Pass Issued Successfully!";
					}
				}


			HttpSession session = request.getSession();
			session.setAttribute("message", message);

			response.sendRedirect("productionStatus-view");
		}
}
