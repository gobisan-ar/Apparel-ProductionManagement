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

<title>Products</title>

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
									<h1 class="h3 mb-0 text-gray-800">All Products</h1>
									<a href="<%=request.getContextPath()%>/product-create"
										class="d-none d-sm-inline-block btn-sm btn-primary btn-blue shadow-sm">
										<i class="bi bi-plus-lg"></i>&nbsp; Create New Product
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
											<table class="table data-table table-striped table-hover"
												id="lineTable">
												<thead>
													<tr>
														<th scope="col"></th>
														<th scope="col">Product ID</th>
														<th scope="col">Title</th>
														<th scope="col">Type</th>
														<th scope="col">Collection</th>
														<th scope="col">Colors</th>
														<th scope="col">Size Range</th>
														<th scope="col">MFG Cost (LKR)</th>
														<th scope="col">Manufacture</th>
														<th scope="col">Update</th>
														<th scope="col">Remove</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="product" items="${productList}">
														<tr>
															<td><img
																src="<%=request.getContextPath()%>/images/apparel/${product.productImage}"
																class="img-fluid table-img" alt="product"></td>
															<th scope="row">PRD${product.productID}</th>

															<td>${product.productTitle}</td>
															<td>${product.typeName}</td>
															<td>${product.collectionName}</td>
															<td><c:forEach var="color" items="${product.colors}">
																	<span class="color-box"
																		style="background-color: ${color};"></span>
																</c:forEach></td>
															<td><c:forEach var="size"
																	items="${product.sizeRange}">
																	${size}
																</c:forEach></td>
																<td>${product.unitCost}</td>
																<td>
																<a href="<%=request.getContextPath()%>/productNotice-form?pid=${product.productID}">
																	<button class="btn-sm btn-success">
																	<i class="bi bi-box-seam"></i>&nbsp; Manufacture
																</button>
																</a>
															</td>
															<td>
																<a href="<%=request.getContextPath()%>/product-edit?pid=${product.productID}">
																	<button class="btn-sm btn-warning">
																	<i class="bi bi-arrow-repeat"></i>&nbsp;Update
																</button>
																</a>
															</td>
															<td>
																<button class="btn-sm btn-danger deleteProduct"
																	data-bs-toggle="modal" data-bs-target="#deleteProduct">
																	<i class="bi bi-x-square"></i>&nbsp;Remove
																</button>
															</td>

															<input type="hidden" id="pid" value="${product.productID}" />

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
	<div class="modal fade" id="deleteProduct" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Are you sure you want to remove this
					product?</div>
				<form method="get" action="product-delete">
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger" id="productDelete">Delete</button>
						<input type="hidden" name="pid" id="pid">
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
		src="<%=request.getContextPath()%>/js/commonScript/alertTimeoutScript.js"></script>	
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/dataTableScript.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionDemoScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionModalScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionValidationScript.js"></script>


	<script>
		$(document).ready(
				function() {
					$("#allProducts").addClass("active");					
				});
	</script>


</body>
</html>