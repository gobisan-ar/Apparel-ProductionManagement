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

<title>Production Status</title>

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
									<h1 class="h3 mb-0 text-gray-800">All Productions</h1>
									<a href="<%=request.getContextPath()%>/productionPlan-create"
										class="d-none d-sm-inline-block btn-sm btn-primary btn-blue shadow-sm">
										<i class="bi bi-plus-lg"></i>&nbsp; Create New Plan
									</a>
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
														<th scope="col">Production ID</th>
														<th scope="col">Notice ID</th>
														<th scope="col">Line</th>
														<th scope="col">Supervisor</th>
														<th scope="col">Due Date</th>
														<th scope="col">Inspection</th>
														<th scope="col">Status</th>
														<th scope="col">Gatepass</th>
														<th scope="col">Update</th>
														<th scope="col">Drop</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="production" items="${productionList}">
														<tr>
															<th scope="row">${production.productionID}</th>
															<td>${production.noticeID}</td>
															<td>${production.lineID}</td>
															<td>${production.supervisorName}</td>
															<td>${production.dueDate}</td>
															<td class="fw-bold">
																<c:if test="${production.inspection eq 'PENDING'}">
																	<span class="text-warning">${production.inspection}</span>
																</c:if>
																<c:if test="${production.inspection eq 'PASS'}">
																	<span class="text-success">${production.inspection}</span>
																</c:if>
																<c:if test="${production.inspection eq 'FAIL'}">
																	<span class="text-danger">${production.inspection}</span>
																</c:if>
															</td>
															<td><a class="btn btn-primary btn-sm shadow-sm updateStatus" data-bs-toggle="modal" data-bs-target="#updateStatus">${production.productionStatus}</a></td>
															<td>
																<c:if test="${production.inspection ne 'PASS'}">
																	<button type="button" class="btn-sm btn-secondary" disabled>
																		<i class="bi bi-bookmark-fill"></i>&nbsp;GatePass
																	</button>
																</c:if>	
																
																<c:if test="${production.inspection eq 'PASS' and production.productionStatus eq 'COMPLETED'}">
																	<button type="button" class="btn-sm btn-info issueGatePass" data-bs-toggle="modal" data-bs-target="#issueGatePass">
																		<i class="bi bi-bookmark-fill"></i>&nbsp;GatePass
																	</button>
																</c:if>	
																
																<c:if test="${production.inspection eq 'PASS' and production.productionStatus eq 'GATE-PASSED'}">
																	<button type="button" class="btn-sm btn-success" disabled>
																		<i class="bi bi-patch-check"></i>&nbsp;Issued
																	</button>
																</c:if>
																
															</td>
															<td>
																<a href="<%=request.getContextPath()%>/production-edit?pid=${production.productionID}">
																	<button class="btn-sm btn-warning">
																		<i class="bi bi-arrow-repeat"></i>&nbsp;Update
																	</button>
																</a>
															</td>
															<td>
																<button class="btn-sm btn-danger deleteProduction" data-bs-toggle="modal" data-bs-target="#deleteProduction">
																<i class="bi bi-power"></i>&nbsp;Drop
																</button>
															</td>
															
															<input type="hidden" id="productionID" name="productionID" value="${production.productionID}" />
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
	
	<!-- Delete Modal Start-->
	<div class="modal fade" id="deleteProduction" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Are you sure you want to drop this
					production?</div>
				<form method="get" action="production-delete">
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger" id="productDelete">Drop</button>
						<input type="hidden" name="pid" id="pid">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Delete Modal End -->
	
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
				<form method="post" action="productionStatus-update">
					
					<div class="mx-3 mb-4">
						<select class="form-select" aria-label="Default select example" id="productionStatus" name="productionStatus">
						  <option value="scheduled">Scheduled</option>
						  <option value="running">Running</option>
						  <option value="completed">Completed</option>
						</select>
						
						<input type="hidden" name="productionID" id="productionID">
					</div>
				
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-success" id="updateStatus">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Status Modal End -->
	
	<!-- Gate Pass Modal Start-->
	<div class="modal fade" id="issueGatePass" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Issue Gatepass</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">PRODUCTION&nbsp;<span id="prnID"></span></div>
				<form method="post" action="productionStatus-update">
						
				<input type="hidden" name="productionStatus" id="productionStatus" value="gate-passed" hidden>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
					<button type="submit" class="btn btn-success" id="updateStatus">Gatepass</button>
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
			$("#productionStatus").addClass("active");
		});
	</script>


</body>
</html>