package com.vokot.productionmanagement.servlet;

import com.vokot.productionmanagement.model.ManufacturingProduct;
import com.vokot.productionmanagement.model.Notice;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductCollection;
import com.vokot.productionmanagement.model.Production;
import com.vokot.productionmanagement.service.ProductInspectionService;
import com.vokot.productionmanagement.service.ProductNoticeService;
import com.vokot.productionmanagement.service.ProductService;
import com.vokot.productionmanagement.service.ProductionPlanService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * This is the ProductServlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Aug 2021
 */

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(urlPatterns = {"/product-view", "/product-create", "/productInsert", "/productUpdate", "/product-delete", "/product-edit", "/productInspection-view", "/inspectionStatus-update"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productDAO;
	private ProductInspectionService inspectionDAO;
	private ProductNoticeService noticeDAO;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		this.productDAO = new ProductService(); 
		this.inspectionDAO = new ProductInspectionService();
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

		case "/product-view":
			try {
				listProducts(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/product-create":
			try {
				selectallProductCollections(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/product-edit":
			try {
				productEdit(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/productInsert":
			try {
				insertProduct(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/productUpdate":
			try {
				updateProduct(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/product-delete":
			try {
				deleteProduct(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/collection-view":
			try {
				listCollections(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
			
		case "/productInspection-view":
			try {
				productInspectionView(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

			
		case "/inspectionStatus-update":
			try {
				updateInspectionStatus(request, response);
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

	// list products
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> productList = productDAO.selectallProducts();

		for (Product product : productList) {
			productDAO.selectallColors(product);
			productDAO.selectallSizes(product);
			productDAO.selectProductCollection(product);
		}

		request.setAttribute("productList", productList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productManagement/product-view.jsp");

		dispatcher.forward(request, response);
	}

	// insert product
	private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String product_title = request.getParameter("productTitle");
		int product_type = Integer.parseInt(request.getParameter("productType"));
		int product_collection = Integer.parseInt(request.getParameter("productCollection"));
		double product_unitCost = Double.parseDouble(request.getParameter("unitCost"));
		String[] colorRange = request.getParameterValues("color[]");
		String[] sizeRange = request.getParameterValues("size[]");
		
		String message = new String();

		try {
			Part filePart = request.getPart("productImg");
			String product_image = filePart.getSubmittedFileName();

			// append timestamp to image name
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			product_image = timestamp.getTime() + product_image;

			Product product = new Product(product_title, product_type, product_collection, product_unitCost,
					product_image);

			int newID = productDAO.insertProduct(product);

			if (newID > 0) {
				message = "Product Successfully Created!";

				// write image to server directory
				String path = getServletContext().getRealPath("/" + "images/apparel" + File.separator + product_image);
				filePart.write(path);
			
				// insert colors
				for (String color : colorRange) {
					productDAO.insertProductColor(newID, color);
				}

				// insert size ranges
				for (String size : sizeRange) {
					productDAO.insertProductSize(newID, size);
				}
			}

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		HttpSession session=request.getSession();  
	    session.setAttribute("message",message);  
	      
	      response.sendRedirect("product-view");
	}
	
	// update product
		private void updateProduct(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException {
			int product_id = Integer.parseInt(request.getParameter("productID"));
			String product_title = request.getParameter("productTitle");
			int product_type = Integer.parseInt(request.getParameter("productType"));
			int product_collection = Integer.parseInt(request.getParameter("productCollection"));
			double product_unitCost = Double.parseDouble(request.getParameter("unitCost"));
			String[] colorRange = request.getParameterValues("color[]");
			String[] sizeRange = request.getParameterValues("size[]");
			
			String message = "Product Successfully Updated!";

			try {
				Part filePart = request.getPart("productImg");
				String product_image = filePart.getSubmittedFileName();

				// append timestamp to image name
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				product_image = timestamp.getTime() + product_image;

				Product product = new Product(product_id, product_title, product_type, product_collection, product_unitCost,
						product_image);

				int rowUpdated = productDAO.updateProduct(product);

				if (rowUpdated > 0) {
					message = "Product Successfully Updated!";

					// write image to server directory
					String path = getServletContext().getRealPath("/" + "images/apparel" + File.separator + product_image);
					filePart.write(path);
					
					// delete existing colors
					productDAO.deleteProductColor(product_id);
					
					// delete existing size
					productDAO.deleteProductSize(product_id);

					// insert updated colors
					for (String color : colorRange) {
						productDAO.insertProductColor(product_id, color);
					}

					// insert size ranges
					for (String size : sizeRange) {
						productDAO.insertProductSize(product_id, size);
					}
				}

			} catch (IOException | ServletException e) {
				e.printStackTrace();
			}

			  HttpSession session=request.getSession();  
		      session.setAttribute("message",message);  
		      
		      response.sendRedirect("product-view");
		}

	// delete product
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		productDAO.deleteProduct(pid);
		
		 HttpSession session=request.getSession();  
	     session.setAttribute("message", "Product successfully removed!");  
	     
		response.sendRedirect("product-view");
	}

	// list product collection
	private void selectallProductCollections(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ProductCollection> collections = productDAO.selectallProductCollections();

		request.setAttribute("collectionList", collections);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/view/productManagement/product-create.jsp");

		dispatcher.forward(request, response);
	}
	
	private void productEdit(HttpServletRequest request, HttpServletResponse response)
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productManagement/product-update.jsp");

		dispatcher.forward(request, response);
	}
	
	// list collection
		private void listCollections(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<ProductCollection> collectionList = productDAO.selectallCollections();

			for (ProductCollection collection : collectionList) {
				productDAO.selectCollectionMaterials(collection);
			}

			request.setAttribute("collectionList", collectionList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productManagement/productCollection-view.jsp");

			dispatcher.forward(request, response);
		}
		
		// list inspections
		private void productInspectionView(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {

			// list completed productions
			List<Production> compProductions = inspectionDAO.selectCompProductions("completed");
			
			for(Production production : compProductions) {
				production.setProductID(noticeDAO.selectNoticeByID(production.getNoticeID()).getProductID());
			}
			
		
			request.setAttribute("compProductionList", compProductions);
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/view/productManagement/productInspection-view.jsp");

			dispatcher.forward(request, response);
		}
		
		// update inspection status
				private void updateInspectionStatus(HttpServletRequest request, HttpServletResponse response)
						throws SQLException, IOException {
					
					int productionID = Integer.parseInt(request.getParameter("productionID"));
					String status = request.getParameter("inspectionStatus");
					

					String message = new String();
				
					int rowUpdated = inspectionDAO.updateInspectionStatus(productionID, status);
					

						if (rowUpdated > 0) {
							message = "Inspection Successfully Updated!";
						}


					HttpSession session = request.getSession();
					session.setAttribute("message", message);

					response.sendRedirect("productInspection-view");
				}
}
