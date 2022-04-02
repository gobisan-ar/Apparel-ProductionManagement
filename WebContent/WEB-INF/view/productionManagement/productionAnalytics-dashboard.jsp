<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Production Dashboard</title>

<!--  cdn styles files -->
<jsp:include page="/partials/cdnStyles.jsp" />

<!--  local styles files -->
<jsp:include page="/partials/localStyles.jsp" />

<!-- custom styles -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/productionStyles.css">

</head>
<body>
	<div id="wrapper">
		<div class="overlay"></div>

		<!-- side navbar start-->
		<jsp:include page="/partials/sideNav-productionMangement.jsp" />
		<!-- side navbar end-->

		<!-- main content-start -->
		<div id="page-content-wrapper">
			<div id="content">
				<div class="container-fluid p-0 px-lg-0 px-md-0">

					<!-- top navbar start-->
					<jsp:include page="/partials/topNav-admin.jsp" />
					<!-- top navbar end -->

					<!-- page content start -->
					<div class="container-fluid px-lg-4">
						<div class="row">

							<!--  Page Title && Description Start -->
							<div class="col-md-12 mt-lg-4 mt-4">
								<div
									class="d-sm-flex align-items-center justify-content-between mt-3 mb-3">
									<h1 class="h3 mb-0 text-gray-800">Analytics Dashboard</h1>
									<button class="d-none d-sm-inline-block btn-sm btn-primary btn-blue shadow-sm"
										data-bs-toggle="modal" data-bs-target="#reportModal">
										<i class="bi bi-clipboard-data"></i>&nbsp; Generate Report
									</button>
								</div>
							</div>
							<!--  Page Title && Description End -->
							
							<!-- alert start -->
							<c:if test="${not empty sessionScope.message}">
								<div class="row d-flex justify-content-center mt-3">
									<div class="col-auto">
										<div class="alert alert-warning alert-dismissible fade show shadow-sm p-3 rounded">
											<span>${sessionScope.message}</span>
											<span class="text-danger px-3 float-end" onclick="this.parentElement.style.display='none';" style="cursor: pointer">
												<i class="bi bi-x-lg"></i>
											</span>
											<%session.removeAttribute("message");%>
										</div>
									</div>
								</div>
							</c:if>
							<!-- alert end -->
							
							<div class=" mb-3">&nbsp;</div>
							
							<!-- summary cards start -->
							<div class="col-md-12">
								<div class="row">
									<!-- card 1 -->
									<div class="col-sm-4">
										<div class="card">
											<div class="card-body">
												<h5 class="card-title mb-4">Productions</h5>
												<h1 class="display-5  mt-1 mb-3">${productionAnalytics.countProductions}</h1>
												<div class="mb-1">
													<span class="textmuted">Number of currently running
														productions</span>
												</div>
											</div>
										</div>
									</div>

									<!-- card 2 -->
									<div class="col-sm-4">
										<div class="card">
											<div class="card-body">
												<h5 class="card-title mb-4">Gatepasses</h5>
												<h1 class="display-5  mt-1 mb-3">${productionAnalytics.countGatepasses}</h1>
												<div class="mb-1">
													<span class="textmuted">Number of gatepasses issued</span>
												</div>
											</div>
										</div>
									</div>

									<!-- card 3 -->
									<div class="col-sm-4">
										<div class="card">
											<div class="card-body">
												<h5 class="card-title mb-4">Requests</h5>
												<h1 class="display-5  mt-1 mb-3">${productionAnalytics.countRequests}</h1>
												<div class="mb-1">
													<span class="textmuted">Number of pending production
														requests</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- summary cards end -->

							<!-- chart cards start -->
							<div class="col-md-12 mt-4">
								<div class="row">
									<!-- chart 1 -->
									<div class="col-sm-6">
										<div class="card">
											<div class="card-body d-flex justify-content-center">
												<div  class="align-middle" id="mfgChart"></div>
											</div>
										</div>
									</div>

									<!-- chart 2 -->
									<div class="col-sm-6">
										<div class="card">
											<div class="card-body d-flex justify-content-center">
												<div class="align-middle" id="lineChart"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- chart cards end -->

							<!-- table card start -->
							<div class="col-md-12 mt-4">
								<div class="card">
									<div class="card-body">
										<div
											class="d-md-flex align-items-center d-md-flex justify-content-between">
											<!-- table sorting start -->
											<div>
												<h4 class="card-title">Table Title</h4>
												<h5 class="card-subtitle">Overview of table title</h5>
											</div>
											<div class="ml-auto">
												<div class="dl">
													<select class="custom-select">
														<option value="365" selected="true">Yearly</option>
														<option value="30" selected="true">Monthly</option>
														<option value="7" selected="true">Weekly</option>
														<option value="1" selected="true">Daily</option>
													</select>
												</div>
											</div>
											<!-- table sorting end -->
										</div>
									</div>

									<!-- data table start -->
									<div class="table-responsive p-3">
										<table class="table v-middle data-table">
											<!-- table header start -->
											<thead>
												<tr class="bg-light">
													<th class="border-top-0">Notice ID</th>
													<th class="border-top-0">Type</th>
													<th class="border-top-0">Quantity</th>
													<th class="border-top-0">Budget</th>
													<th class="border-top-0">Delivery</th>
												</tr>
											</thead>
											<!-- table header end -->

											<!-- table body start -->
											<tbody>
													<c:if test="${not empty sessionScope.notice}">
													<c:forEach var = "penNotice" items="${sessionScope.notice}">
														<tr>
															<th scope="row"><a href="#" class="btn btn-circle btn-info text-white">${penNotice.noticeID}</a></th>
															<td>${penNotice.originProduct.collectionName}</td>
															<td>${penNotice.quantity}</td>
															<td>${penNotice.estBudget}</td>
															<td>${penNotice.completion}</td>
															<input type="hidden" id="productionID" name="productionID" value="${compProduction.productionID}" />
														</tr>
													</c:forEach>
													
													<%session.removeAttribute("inspection");%>
												</c:if>
											</tbody>
											<!-- table body end -->
										</table>
									</div>
									<!-- data table end -->
								</div>
							</div>
							<!-- table card end -->
						</div>
					</div>
					<!-- page content end -->
				</div>
			</div>
		</div>
		<!-- main content-end -->
	</div>
	
	<!-- Report Name Modal Start-->
	<div class="modal fade" id="reportModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Generate Production Report</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
						<form method="post" action="ProductionReport">
							<label for="reportName">Enter Report Name:</label><br/>
							<input type="text" class="mr-5" name="reportName" id="reportName" style="width: 100%;" required="required"/>
				</div>
					
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-success" id="setReportName">Download</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Status Modal End -->

	<!-- footer start -->
	<jsp:include page="/partials/footer-admin.jsp" />
	<!-- footer start -->

	<!--  cdn script files -->
	<jsp:include page="/partials/cdnScripts.jsp" />
	<!--  local script files -->
	<jsp:include page="/partials/localScripts.jsp" />

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/toggleScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/dataTableScript.js"></script>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/productionDemoScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/productionModalScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/productionValidationScript.js"></script>

	<script>
		$(document).ready(
				function() {
					$("#analyticsNav").addClass("active");
					$("#bar").toggleClass("open");
					$("#page-content-wrapper, #sidebar-wrapper").toggleClass(
							"toggled");
				});
	</script>

	<script>
		google.charts.load('current', {'packages' : [ 'corechart' ]});
		google.charts.setOnLoadCallback(drawChart);

		function drawChart() {
			// bar chart
			var lineData = google.visualization.arrayToDataTable([
					[ "Line", "Number of Productions", {role : "style"} ], 
					<c:forEach var = "lines" items="${productionAnalytics.countLines}">
						['Line ${lines.key}', ${lines.value}, "#F4976C"], 
					</c:forEach>
					]);
			
			var lineView = new google.visualization.DataView(lineData);
			lineView.setColumns([ 0, 1, {
				calc : "stringify",
				sourceColumn : 1,
				type : "string",
				role : "annotation"
			}, 2 ]);

			var lineOptions = {
				title : "Number of Productions Running in Lines",
				width : 400,
				height : 200,
				bar : {
					groupWidth : "95%"
				},
				legend : {
					position : "none"
				},
			};

			var lineChart = new google.visualization.BarChart(document.getElementById("lineChart"));
			lineChart.draw(lineView, lineOptions);
			
			// Product Collection Chart
	        var mfgData = google.visualization.arrayToDataTable([
	              ['Status', 'Count'],
	              <c:forEach var="entry" items="${productionAnalytics.countMfgStatus}">
                  	['${entry.key}', ${entry.value}],
              	  </c:forEach>  
	        ]);
	                    
	        var mfgOptions = {
	            title : 'Production Status', 
	            is3D : true, 
	            pieSliceText: 'label', 
	            tooltip :  {showColorCode: true}, 
	            'width' : 400, 
	            'height' : 200
	        };
	        
	        // Instantiate and draw our chart, passing in some options.
	        var mfgChart = new google.visualization.PieChart(document.getElementById("mfgChart"));
	        mfgChart.draw(mfgData, mfgOptions);
		}
	</script>

</body>
</html>