package com.vokot.productionmanagement.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.vokot.productionmanagement.model.ManufacturingProduct;
import com.vokot.productionmanagement.model.Notice;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductCollection;
import com.vokot.productionmanagement.service.ProductNoticeService;
import com.vokot.productionmanagement.service.ProductService;

/**
 * This is the ProductNoticeServlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

/**
 * Servlet implementation class ProductNoticeServlet
 */
@WebServlet(urlPatterns = {"/productNotice-create", "/productNotice-form", "/productNotice-insert", "/productNotice-view", "/notice-delete", "/productNotice-edit", "/notice-update"})
public class ProductNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productDAO;
	private ProductNoticeService noticeDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductNoticeServlet() {
		super();
		this.productDAO = new ProductService();
		this.noticeDAO = new ProductNoticeService();
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
		
		case "/productNotice-create":
			try {
				productNoticeCreate(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/productNotice-form":
			try {
				productNoticeForm(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/productNotice-insert":
			try {
				insertNotice(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/productNotice-view":
			try {
				listProductNotices(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/notice-delete":
			try {
				deleteNotice(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/notice-update":
			try {
				updateNotice(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/productNotice-edit":
			try {
				productNoticeEdit(request, response);
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
	
	private void productNoticeCreate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productManagement//productNotice-create.jsp");

		dispatcher.forward(request, response);
	}

	// productNotice
	private void productNoticeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int productID = Integer.parseInt(request.getParameter("pid"));
		List<ProductCollection> collections = productDAO.selectallProductCollections();

		Product product = productDAO.selectProductByID(productID);

		productDAO.selectallColors(product);
		productDAO.selectallSizes(product);
		productDAO.selectProductCollection(product);
		productDAO.selectallProductCollections();

		request.setAttribute("product", product);
		request.setAttribute("collectionList", collections);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productManagement/productNotice-create.jsp");

		dispatcher.forward(request, response);
	}

	// insert product notice
	private void insertNotice(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		String noticeTitle = request.getParameter("noticeTitle");
		String origin = request.getParameter("origin");
		int quantity = Integer.parseInt(request.getParameter("prodQty"));
		double estBudget = Double.parseDouble(request.getParameter("estBudget"));
		String completion = request.getParameter("compDate");
		
		String color =  request.getParameter("color");
		String size = request.getParameter("size");
		
		int productID = Integer.parseInt(request.getParameter("productID"));
		String orderID = request.getParameter("orderID");
		
		int referenceNo = 0;
		
		if(orderID == "") {
			referenceNo = productID;
		}else {
			referenceNo = Integer.parseInt(orderID);
		}
		
		String message = new String();

		int newID = noticeDAO.insertNotice(new Notice(noticeTitle, origin, quantity, estBudget, completion, productID, referenceNo));

		if (newID > 0) {
			message = "Product Notice Successfully Created!";
			
			noticeDAO.insertManufacturingProduct(new ManufacturingProduct(newID, productID, color, size));
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("message", message);

		response.sendRedirect("productNotice-view");
	}
	
	// list all product notices
	private void listProductNotices(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<Notice> noticeList = noticeDAO.selectallNotices();

		for (Notice notice : noticeList) {
			noticeDAO. selectManufacturingProduct(notice);
		}

		request.setAttribute("noticeList", noticeList);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productManagement/productNotice-view.jsp");

		dispatcher.forward(request, response);
	}
	
	// delete notice
		private void deleteNotice(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException {
			int nid = Integer.parseInt(request.getParameter("nid"));
			
			noticeDAO.deleteNotice(nid);
			noticeDAO.deleteMfgProduct(nid);
			
			 HttpSession session=request.getSession();  
		     session.setAttribute("message", "Notice successfully revoked!");  
		     
			response.sendRedirect("productNotice-view");
		}
		

		// update product notice
		private void updateNotice(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException {
			
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			String noticeTitle = request.getParameter("noticeTitle");
			String origin = request.getParameter("origin");
			int quantity = Integer.parseInt(request.getParameter("prodQty"));
			String completion = request.getParameter("compDate");
			double estBudget = Double.parseDouble(request.getParameter("estBudget"));
			
			String color =  request.getParameter("color");
			String size = request.getParameter("size");
			
			int productID = Integer.parseInt(request.getParameter("productID"));
			String orderID = request.getParameter("orderID");
			
			int referenceNo = 0;
			
			if(orderID == "") {
				referenceNo = productID;
			}else {
				referenceNo = Integer.parseInt(orderID);
			}
			
			String message = new String();

			int newID = noticeDAO.updateNotice(new Notice(noticeID, noticeTitle, origin, quantity, estBudget,completion, productID, referenceNo));

			if (newID > 0) {
				message = "Product Notice Successfully Updated!";
				
				noticeDAO.updateManufacturingProduct(new ManufacturingProduct(newID, productID, color, size));
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("message", message);

			response.sendRedirect("productNotice-view");
		}
		
		// form update product notice
		private void productNoticeEdit(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			
			int noticeID = Integer.parseInt(request.getParameter("nid"));
			
			Notice notice = noticeDAO.selectNoticeByID(noticeID);
			
			Product product = productDAO.selectProductByID(notice.getProductID());
			notice.setOriginProduct(product);
			
			noticeDAO.selectManufacturingProduct(notice);

			request.setAttribute("notice", notice);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productManagement/productNotice-update.jsp");

			dispatcher.forward(request, response);
		}

}
