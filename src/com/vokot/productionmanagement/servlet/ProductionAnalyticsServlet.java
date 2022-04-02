package com.vokot.productionmanagement.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vokot.productionmanagement.model.Notice;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductAnalytics;
import com.vokot.productionmanagement.model.ProductionAnalytics;
import com.vokot.productionmanagement.service.ProductAnalyticsService;
import com.vokot.productionmanagement.service.ProductNoticeService;
import com.vokot.productionmanagement.service.ProductService;
import com.vokot.productionmanagement.service.ProductionAnalyticsService;

/**
 * This is the ProductionAnalytics Servlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

/**
 * Servlet implementation class ProductionAnalyticsServlet
 */
@WebServlet(urlPatterns = {"/productionAnalyticsDashboard"})
public class ProductionAnalyticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductionAnalyticsService productionAnsDAO;
	private ProductNoticeService noticeDAO;
	private ProductService productDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductionAnalyticsServlet() {
        super();
        productionAnsDAO = new ProductionAnalyticsService();
        noticeDAO = new ProductNoticeService();
        productDAO = new ProductService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();

		switch (action) {

		case "/productionAnalyticsDashboard":
			try {
				productionAnalyticsDashboard(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
			default:
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void productionAnalyticsDashboard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		ProductionAnalytics productionAnalytics = productionAnsDAO.selectProductionAnalytics();
		
		HttpSession inspection = request.getSession();
		inspection.setAttribute("notice", productionRequest(request, response));
		
		request.setAttribute("productionAnalytics", productionAnalytics);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productionManagement/productionAnalytics-dashboard.jsp");

		dispatcher.forward(request, response);
	}
	
	private List<Notice> productionRequest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<Notice> noticeList = noticeDAO.selectallNotices();
		
		Product product = new Product();

		for (Notice notice : noticeList) {
			noticeDAO.selectManufacturingProduct(notice);

			product = productDAO.selectProductByID(notice.getProductID());
			productDAO.selectProductCollection(product);

			notice.setOriginProduct(product);
		}

		return noticeList;
	}
}
