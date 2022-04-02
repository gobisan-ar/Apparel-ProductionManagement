<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="true" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Product Inspections</title>

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
		<jsp:include page="/partials/sideNav-productMangement.jsp" />
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
									<h1 class="h3 mb-0 text-gray-800">All Product Inspections</h1>
								</div>
							</div>
							<!--  Page Title && Description End -->
							
							<!-- alert start -->
							<c:if test="${not empty sessionScope.message}">
								<div class="row d-flex justify-content-center">
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

							<!-- Main content start -->
							<div class="col-md-12 mt-4">
								<div class="card">
									<div class="card-body">
										<!-- data table start -->
										<div class="table-responsive p-3">
											<table class="table data-table table-striped table-hover" id="lineTable">
												<thead>
													<tr>
														<th scope="col">Notice ID</th>
														<th scope="col">Product ID</th>
														<th scope="col">Production ID</th>
														<th scope="col">Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var = "compProduction" items="${compProductionList}">
														<tr>
															<th scope="row">${compProduction.noticeID}</th>
															<td>${compProduction.productID}</td>
															<td>${compProduction.productionID}</td>
															<td>
															<c:if test="${compProduction.inspection eq 'PASS'}">
																<button class="btn-sm btn-success updateStatus" data-bs-toggle="modal" data-bs-target="#updateStatus">
																	${compProduction.inspection}
																</button>
															</c:if>
															<c:if test="${compProduction.inspection eq 'PENDING'}">
																<button class="btn-sm btn-warning updateStatus" data-bs-toggle="modal" data-bs-target="#updateStatus">
																	${compProduction.inspection}
																</button>
															</c:if>
															<c:if test="${compProduction.inspection eq 'FAIL'}">
																<button class="btn-sm btn-danger updateStatus" data-bs-toggle="modal" data-bs-target="#updateStatus">
																	${compProduction.inspection}
																</button>
															</c:if>
															</td>
															
															<input type="hidden" id="productionID" name="productionID" value="${compProduction.productionID}" />
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- Main content end -->
						</div>
					</div>
					<!-- page content end -->
				</div>
				<!-- footer start -->
				<jsp:include page="/partials/footer-admin.jsp" />
				<!-- footer start -->
			</div>
		</div>
		<!-- main content-end -->
	</div>
	
	<!-- Status Modal Start-->
	<div class="modal fade" id="updateStatus" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Update production Status</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">PRODUCTION&nbsp;<span id="prnID"></span></div>
				<form method="post" action="inspectionStatus-update">
					
					<div class="mx-3 mb-4">
						<select class="form-select" aria-label="Default select example" id="inspectionStatus" name="inspectionStatus">
						  <option value="pending">Pending</option>
						  <option value="pass">Pass</option>
						  <option value="fail">Fail</option>
						</select>
					</div>
				
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-success" id="updateStatus">Update</button>
						<input type="hidden" name="productionID" id="productionID">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Status Modal End -->

	<!--  cdn script files -->
	<jsp:include page="/partials/cdnScripts.jsp" />

	<!--  local script files -->
	<jsp:include page="/partials/localScripts.jsp" />

	<!-- custom script -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/chartScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/toggleScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/dataTableScript.js"></script>
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionDemoScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionModalScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionValidationScript.js"></script>

	<script>
		$(document).ready(function() {
			$("#productInspection").addClass("active");
			$("#bar").toggleClass("open");
			$("#page-content-wrapper, #sidebar-wrapper").toggleClass("toggled");
		});
	</script>


</body>
</html>