package com.vokot.productionmanagement.servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyleConstants.FontConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.ByteArray;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductReport;
import com.vokot.productionmanagement.service.ProductAnalyticsService;
import com.vokot.productionmanagement.service.ProductService;

/**
 * This is the ProductReport Servlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

/**
 * Servlet implementation class ProductReportServlet
 */
@WebServlet("/ProductReport")
public class ProductReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductAnalyticsService productalsDAO;
	ProductService productDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductReportServlet() {
		super();
		productalsDAO = new ProductAnalyticsService();
		productDAO= new ProductService();
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

		case "/ProductReport":
			try {
				generateProductReport(request, response);
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

	private void generateProductReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		String fileName = request.getParameter("reportName");
		String filepath = "C:\\Users\\User\\Downloads\\" + fileName + ".pdf";
		
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filepath));

			// Title
			document.open();

			String title = "Vokot Apparel Manufacturers";

			Font heading = new Font();
			heading.setStyle(Font.BOLD);
			heading.setSize(36);

			Paragraph paraTitle = new Paragraph(title, heading);
			paraTitle.setAlignment(Element.ALIGN_CENTER);

			document.add(paraTitle);
			document.add(new Paragraph(" "));

			// Sub title
			String subtitle = "Product Summary Report";

			Font subheading = new Font();
			subheading.setStyle(Font.BOLD);
			subheading.setSize(20);

			Paragraph paraSubTitle = new Paragraph(subtitle, subheading);
			paraSubTitle.setSpacingBefore(10f);
			paraSubTitle.setAlignment(Element.ALIGN_CENTER);

			document.add(paraSubTitle);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			// Table Heading
			String tableTitle = "Product Collection Summary";

			Font tableHeading = new Font();
			tableHeading.setStyle(Font.BOLD);
			tableHeading.setSize(14);

			Paragraph paraTableTtile = new Paragraph(tableTitle, tableHeading);
			paraSubTitle.setSpacingBefore(10f);
			paraSubTitle.setAlignment(Element.ALIGN_LEFT);

			document.add(paraTableTtile);

			// Table
			Font colHead = new Font();
			colHead.setSize(12);
			colHead.setStyle(Font.BOLD);

			PdfPTable collectionTable = new PdfPTable(5);

			collectionTable.setSpacingBefore(20f);
			collectionTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell c1 = new PdfPCell(new Phrase("Gneder", colHead));
			collectionTable.addCell(c1);

			c1 = new PdfPCell(new Phrase("T-Shirt", colHead));
			collectionTable.addCell(c1);

			c1 = new PdfPCell(new Phrase("Shirt", colHead));
			collectionTable.addCell(c1);

			c1 = new PdfPCell(new Phrase("Hoodie", colHead));
			collectionTable.addCell(c1);

			c1 = new PdfPCell(new Phrase("Total", colHead));
			collectionTable.addCell(c1);
			collectionTable.setHeaderRows(1);

			ProductReport productRep = productalsDAO.selectGenderCollection();

			collectionTable.addCell(new Phrase("Mens", colHead));
			collectionTable.addCell(Integer.toString(productRep.getMensTshrit()));
			collectionTable.addCell(Integer.toString(productRep.getMensShirt()));
			collectionTable.addCell(Integer.toString(productRep.getMensHoodie()));
			collectionTable.addCell(Integer.toString(productRep.getTotMensCollection()));

			collectionTable.addCell(new Phrase("Womens", colHead));
			collectionTable.addCell(Integer.toString(productRep.getWomensTshrit()));
			collectionTable.addCell(Integer.toString(productRep.getWomensShirt()));
			collectionTable.addCell(Integer.toString(productRep.getWomensHoodie()));
			collectionTable.addCell(Integer.toString(productRep.getTotWomensCollection()));

			int totTshirt = productRep.getMensTshrit() + productRep.getWomensTshrit();
			int totShirt = productRep.getMensShirt() + productRep.getWomensShirt();
			int totHoodie = productRep.getMensHoodie() + productRep.getWomensHoodie();

			collectionTable.addCell(new Phrase("Total", colHead));
			collectionTable.addCell(Integer.toString(totTshirt));
			collectionTable.addCell(Integer.toString(totShirt));
			collectionTable.addCell(Integer.toString(totHoodie));
			collectionTable.addCell(Integer.toString(productRep.getTotMensCollection() + productRep.getTotWomensCollection()));

			collectionTable.setSpacingAfter(10f);

			document.add(collectionTable);

			document.add(new Paragraph(" "));
			
			// High Cost
			
			tableTitle = "High Manufacturing Cost Proudcts";
			
			paraTableTtile = new Paragraph(tableTitle, tableHeading);
			paraSubTitle.setSpacingBefore(10f);
			paraSubTitle.setAlignment(Element.ALIGN_LEFT);
			
			document.add(paraTableTtile);
			document.add(new Paragraph(""));

			PdfPTable costTable = new PdfPTable(4);
			costTable.setSpacingBefore(20f);
			costTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell cell = new PdfPCell(new Phrase("PID", colHead));
			costTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Title", colHead));
			costTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Collection", colHead));
			costTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Unit Cost", colHead));
			costTable.addCell(cell);
			
			List<Product> highCostProduct = productalsDAO.highCostProducts();
			
			for (Product product : highCostProduct) {
				productDAO.selectProductCollection(product);
				
				costTable.addCell(Integer.toString(product.getProductID()));
				costTable.addCell(product.getProductTitle());
				costTable.addCell(product.getCollectionName());
				costTable.addCell(Double.toString(product.getUnitCost()));
			}
			
			costTable.setSpacingAfter(10f);
			
			document.add(costTable);
			
			document.add(new Paragraph(" "));
			
			tableTitle = "Low Manufacturing Cost Proudcts";
			
			paraTableTtile = new Paragraph(tableTitle, tableHeading);
			paraSubTitle.setSpacingBefore(20f);
			paraSubTitle.setAlignment(Element.ALIGN_LEFT);
			
			document.add(paraTableTtile);
			document.add(new Paragraph(""));

			PdfPTable lowcostTable = new PdfPTable(4);
			lowcostTable.setSpacingBefore(10f);
			lowcostTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell lcell = new PdfPCell(new Phrase("PID", colHead));
			lowcostTable.addCell(lcell);

			lcell = new PdfPCell(new Phrase("Title", colHead));
			lowcostTable.addCell(lcell);

			lcell = new PdfPCell(new Phrase("Collection", colHead));
			lowcostTable.addCell(lcell);

			lcell = new PdfPCell(new Phrase("Unit Cost", colHead));
			lowcostTable.addCell(lcell);
			
			List<Product> lowCostProduct = productalsDAO.lowCostProducts();
			
			for (Product product : lowCostProduct) {
				productDAO.selectProductCollection(product);
				
				lowcostTable.addCell(Integer.toString(product.getProductID()));
				lowcostTable.addCell(product.getProductTitle());
				lowcostTable.addCell(product.getCollectionName());
				lowcostTable.addCell(Double.toString(product.getUnitCost()));
			}
			
			lowcostTable.setSpacingAfter(30f);
			
			document.add(lowcostTable);
			
			final DefaultBoxAndWhiskerCategoryDataset costDataset = new DefaultBoxAndWhiskerCategoryDataset();
			
			List<Double> costList = productalsDAO.unitCost();
			
			costDataset.add(costList, "", "");
			
	        final CategoryAxis xAxis = new CategoryAxis("MFG Cost");
	        final NumberAxis yAxis = new NumberAxis(" ");
	        yAxis.setAutoRangeIncludesZero(false);
	        
	        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
	        renderer.setFillBox(false);
	        renderer.setFillBox(true);
	        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);
	        renderer.setSeriesPaint(1, Color.LIGHT_GRAY);
	        renderer.setSeriesOutlinePaint(0, Color.BLACK);
	        renderer.setSeriesOutlinePaint(1, Color.BLACK);
	        renderer.setUseOutlinePaintForWhiskers(true);   
	        // Font legendFont = new Font("SansSerif", Font.NORMAL, 16);
	        renderer.setMedianVisible(true);
	        renderer.setMeanVisible(false);
	        // renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
	        
	        final CategoryPlot plot = new CategoryPlot(costDataset, xAxis, yAxis, renderer);
	        plot.setOrientation(PlotOrientation.HORIZONTAL);

	        Font plotFont = new Font();
	        plotFont.setFamily("SansSerif");
	        plotFont.setStyle(Font.BOLD);
	        plotFont.setSize(14f);
	        
	        final JFreeChart costChart = new JFreeChart("Manufacturing Cost per Unit", plot);
			
			BufferedImage bufferedImage = costChart.createBufferedImage(1024, 512);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", bos);
			Image image = Image.getInstance(bos.toByteArray());
			
			image.setRotation(90);
			image.scalePercent(50f);
			document.add(image);

//			document.add(image);
			// add image
			// document.add(Image.getInstance("C:\\Users\\User\\Downloads\\product_report.pdf"));

			document.close();

			HttpSession session = request.getSession();

			session.setAttribute("message", "Report Downloaded Successfully");

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("productAnalyticsDashboard");
	}

}
