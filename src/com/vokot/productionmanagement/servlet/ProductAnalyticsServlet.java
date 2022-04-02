package com.vokot.productionmanagement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.vokot.productionmanagement.model.ProductAnalytics;
import com.vokot.productionmanagement.model.Production;
import com.vokot.productionmanagement.service.ProductAnalyticsService;
import com.vokot.productionmanagement.service.ProductInspectionService;
import com.vokot.productionmanagement.service.ProductNoticeService;
import com.vokot.productionmanagement.service.ProductService;

/**
 * This is the ProductAnalytics Servlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

/**
 * Servlet implementation class ProductAnalyticsServlet
 */
@WebServlet(urlPatterns = { "/productAnalyticsDashboard" })
public class ProductAnalyticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductAnalyticsService productAnsDAO;
	private ProductInspectionService inspectionDAO;
	private ProductNoticeService noticeDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductAnalyticsServlet() {
		super();
		// TODO Auto-generated constructor stub
		productAnsDAO = new ProductAnalyticsService();
		inspectionDAO = new ProductInspectionService();
		noticeDAO = new ProductNoticeService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();

		switch (action) {

		case "/productAnalyticsDashboard":
			try {
				productAnalyticsDashboard(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
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

	private void productAnalyticsDashboard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		ProductAnalytics productAnalytics = productAnsDAO.selectProductAnalytics();

		request.setAttribute("productAnalytics", productAnalytics);

		HttpSession inspection = request.getSession();
		inspection.setAttribute("inspection", productInspectionView(request, response));

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productManagement/productAnalytics-dashboard.jsp");

		dispatcher.forward(request, response);
	}

	// list inspections
	private List<Production> productInspectionView(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// list completed productions
		List<Production> compProductions = inspectionDAO.selectCompProductions("completed");

		for (Production production : compProductions) {
			production.setProductID(noticeDAO.selectNoticeByID(production.getNoticeID()).getProductID());
		}

		return compProductions;
	}
}
