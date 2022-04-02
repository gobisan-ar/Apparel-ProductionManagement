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

<title>Product Notices</title>

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
									<h1 class="h3 mb-0 text-gray-800">All Product Notices</h1>
									<a href="<%=request.getContextPath()%>/productNotice-create"
										class="d-none d-sm-inline-block btn-sm btn-primary btn-blue shadow-sm">
										<i class="bi bi-plus-lg"></i>&nbsp; Product Notice
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
														<th scope="col">Notice ID</th>
														<th scope="col">Title</th>
														<th scope="col">Reference No</th>
														<th scope="col">Origin</th>
														<th scope="col">Est Budget(LKR)</th>
														<th scope="col">Exp Delivery</th>
														<th scope="col">Status</th>
														<th scope="col">Product Details</th>
														<th scope="col">Update</th>
														<th scope="col">Revoke</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="notice" items="${noticeList}">
														<tr>
															<th scope="row">PRN${notice.noticeID}</th>
															<td>${notice.noticeTitle}</td>
															<td>${notice.reference}</td>
															<td>${notice.origin}</td>
															<td>${notice.estBudget}</td>
															<td>${notice.completion}</td>
															
															<c:if test="${notice.status eq -1}">
																<td class="text-danger fw-bold">${notice.noticeStatus}</td>
															</c:if>
															
															<c:if test="${notice.status eq 0}">
																<td class="text-warning fw-bold">${notice.noticeStatus}</td>
															</c:if>
															
															<c:if test="${notice.status eq 1}">
																<td class="text-success fw-bold">${notice.noticeStatus}</td>
															</c:if>
															
															<td><button class="btn-sm btn-success productDetails" data-bs-toggle="modal" data-bs-target="#productDetails">
																<i class="bi bi-search"></i>&nbsp;Product Details
															</button></td>
															<td>
																<a href="<%=request.getContextPath()%>/productNotice-edit?nid=${notice.noticeID}">
																<button class="btn-sm btn-warning">
																	<i class="bi bi-arrow-repeat"></i>&nbsp;Update
																</button>
															</td>
															<td><button class="btn-sm btn-danger deleteNotice"
																	data-bs-toggle="modal" data-bs-target="#deleteNotice">
																<i class="bi bi-x-square"></i>&nbsp;Revoke
															</button></td>
															
															<input type="hidden" id="nid" value="${notice.noticeID}" />
															<input type="hidden" id="mfgProductID" value="${notice.productID}">
															<input type="hidden" id="quantity" value="${notice.quantity}">
															<input type="hidden" id="noticeID" value="${notice.manufacturingProduct.noticeID}"> 
															<input type="hidden" id="color" value="${notice.manufacturingProduct.color}">  
															<input type="hidden" id="size" value="${notice.manufacturingProduct.size}">
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
	
	<!-- Product Modal Start-->
	<div class="modal fade" id="productDetails" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Notice : <span id="noticeID"><span></span></h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<table class="table">
					  <thead class="table-dark">
					    <tr>
					    	<th style="color: #fff;">Product ID</th>
					    	<th style="color: #fff;">Color</th>
					    	<th style="color: #fff;">Size</th>
					    	<th style="color: #fff;">Quantity</th>
					    </tr>
					  </thead>
					  <tbody>
					   		<td><span id="mfgProductID"></span></td>
					    	<td><span id="color"></span>  </td>
					    	<td><span id="size"></span></td>
					    	<td><span id="quantity"></span></td>
					  </tbody>
					</table>	
				</div>
			</div>
		</div>
	</div>
	<!-- Product Modal End -->
	
	<!-- Delete Modal Start-->
	<div class="modal fade" id="deleteNotice" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Are you sure you want to revoke this
					notice?</div>
				<form method="get" action="notice-delete">
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger" id="productDelete">Revoke</button>
						<input type="hidden" name="nid" id="nid">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Delete Modal End -->

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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/alertTimeoutScript.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionDemoScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionModalScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionValidationScript.js"></script>

	<script>
		$(document).ready(function() {
			$("#productNotices").addClass("active");
		});
	</script>
</body>
</html>