package com.vokot.productionmanagement.servlet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vokot.productionmanagement.model.Product;
import com.vokot.productionmanagement.model.ProductReport;
import com.vokot.productionmanagement.model.ProductionAnalytics;
import com.vokot.productionmanagement.model.ProductionReport;
import com.vokot.productionmanagement.service.ProductionAnalyticsService;

/**
 * This is the ProductionReport Servlet class
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

/**
 * Servlet implementation class ProductionReportServlet
 */
@WebServlet(urlPatterns = { "/ProductionReport" })
public class ProductionReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductionAnalyticsService productionalsDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductionReportServlet() {
		super();
		productionalsDAO = new ProductionAnalyticsService();
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

		case "/ProductionReport":

			try {
				generateProductionReport(request, response);
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

	private void generateProductionReport(HttpServletRequest request, HttpServletResponse response)
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
			String subtitle = "Production Summary  Report";

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
			String tableTitle = "Production Status Summary";

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

			PdfPTable statusTable = new PdfPTable(3);

			statusTable.setSpacingBefore(20f);
			statusTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell c1 = new PdfPCell(new Phrase("Status", colHead));
			statusTable.addCell(c1);

			c1 = new PdfPCell(new Phrase("No of Productions", colHead));
			statusTable.addCell(c1);

			c1 = new PdfPCell(new Phrase("Percentage", colHead));
			statusTable.addCell(c1);

			ProductionAnalytics productionStat = productionalsDAO.selectProductionAnalytics();

			HashMap<String, Integer> status = productionStat.getCountMfgStatus();

			double totProductions = status.get("running") + status.get("scheduled") + status.get("completed");
			double percentage = 0.0;

			for (HashMap.Entry<String, Integer> cur : status.entrySet()) {
				String key = cur.getKey();
				int value = cur.getValue();

				if (key.equalsIgnoreCase("scheduled")) {
					statusTable.addCell(new Phrase("Scheduled"));
					statusTable.addCell(Integer.toString(value));
					statusTable.addCell(Double.toString((value / totProductions) * 100.0) + " %");
				}

				if (key.equalsIgnoreCase("running")) {
					statusTable.addCell(new Phrase("Running"));
					statusTable.addCell(Integer.toString(value));
					statusTable.addCell(Double.toString((value / totProductions) * 100) + " %");
				}

				if (key.equalsIgnoreCase("completed")) {
					statusTable.addCell(new Phrase("Completed"));
					statusTable.addCell(Integer.toString(value));
					statusTable.addCell(Double.toString((value / totProductions) * 100) + " %");
				}
			}

			statusTable.setSpacingAfter(10f);

			document.add(statusTable);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			// Table Heading
			tableTitle = "Production Lines' Capacity Summary";

			paraTableTtile = new Paragraph(tableTitle, tableHeading);
			paraSubTitle.setSpacingBefore(10f);
			paraSubTitle.setAlignment(Element.ALIGN_LEFT);

			document.add(paraTableTtile);

			// Table
			PdfPTable lineTable = new PdfPTable(3);

			lineTable.setSpacingBefore(20f);
			lineTable.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell c2 = new PdfPCell(new Phrase("Line", colHead));
			lineTable.addCell(c2);

			c2 = new PdfPCell(new Phrase("No of Running Productions", colHead));
			lineTable.addCell(c2);

			c2 = new PdfPCell(new Phrase("Percentage", colHead));
			lineTable.addCell(c2);

			HashMap<Integer, Integer> lineCap = productionStat.getCountLines();

			double totCap = 0;
			double linepercentage = 0.0;

			for (HashMap.Entry<Integer, Integer> cur : lineCap.entrySet()) {
				totCap += cur.getValue();
			}

			for (HashMap.Entry<Integer, Integer> cur : lineCap.entrySet()) {
				int key = cur.getKey();
				int value = cur.getValue();

				lineTable.addCell("Line " + Integer.toString(key));
				lineTable.addCell(Integer.toString(value));
				lineTable.addCell(Double.toString((value / totCap) * 100.0) + " %");
			}

			lineTable.setSpacingAfter(10f);

			document.add(lineTable);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			// Chart
			ProductionReport productionRep = productionalsDAO.selectProductionReport();
			
			final DefaultPieDataset dataset = new DefaultPieDataset();

	        dataset.setValue("Pending", productionRep.getInspPending());
	        dataset.setValue("Pass", productionRep.getInspPass());
	        dataset.setValue("Fail", productionRep.getInspFail());
	       		
	        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
	        
	        final JFreeChart chart1 = ChartFactory.createPieChart("Production Inspection Status Summary", dataset, true, true, false);
	        final PiePlot plot1 = (PiePlot) chart1.getPlot();
	        plot1.setLabelGenerator(labelGenerator);
	        
	        plot1.setSectionPaint("Pending", Color.yellow);
	        plot1.setSectionPaint("Pass", Color.green);
	        plot1.setSectionPaint("Fail", Color.red);
	        
			BufferedImage bufferedImage = chart1.createBufferedImage(1024, 512);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", bos);
			Image image = Image.getInstance(bos.toByteArray());
			
			image.setRotation(90);
			image.scalePercent(50f);
			document.add(image);

			document.close();

			HttpSession session = request.getSession();

			session.setAttribute("message", "Report Downloaded Successfully");

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("productionAnalyticsDashboard");
	}
}
