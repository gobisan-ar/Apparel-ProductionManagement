<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Update Notice</title>

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
									<h1 class="h3 mb-0 text-gray-800">Update Product Notice: ${notice.noticeID}</h1>
									<button class="d-none d-sm-inline-block btn btn-danger shadow" id="fillNotice">
										<i class="bi bi-pencil-square"></i>&nbsp; Demo
									</button>
								</div>
							</div>
							<!--  Page Title && Description End -->

							<!-- Main content start -->
							<div class="col-md-12 mt-3 mb-3">
								<div class="card">
									<div class="card-body px-4">
										<form action="notice-update" method="post" class="row g-4 px-4" name="noticeForm" id="noticeForm">
											<input type="text" class="form-control" name = "noticeID" id="noticeID"  value="${notice.noticeID}" readonly="readonly" hidden> 
											<input type="text" class="form-control" name = "productID" id="productID"  value="${notice.productID}" readonly="readonly" hidden> 
											
											<div class="row mt-5">
												<div class="col-md-6">
													<label for="productTilte" class="form-label">Product:</label>
													<input type="text" class="form-control" name = "noticeTitle" id="noticeTitle" readonly="readonly" value="${notice.noticeTitle}"> 
												</div>
												
												<div class="col-md-3">
													<label for="exampleColorInput" class="form-label">Product Origin:</label>
													<br/>
													
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio" name="origin" id="inhouse" value="inhouse" checked>
														<label class="form-check-label" for="inhouse">In-house Request </label>
													</div>
													
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio" name="origin" id="order" value="order"> 
														<label class="form-check-label" for="order"> Customer Order </label>
													</div>
												</div>
												
												<div class="col-md-3">
													<label for="productTilte" class="form-label" id="orderIDLabel" hidden>Order ID:</label>
													<input type="text" class="form-control" name="orderID" id="orderID" hidden> 
												</div>
											</div>

											<div class="row mt-5">
												<div class="col-md-6">
													<label class="form-label" for="prodQty">Quantity (Units):</label>
													<input type="number" class="form-control"  name="prodQty" id="prodQty" value="${notice.quantity}">
												</div>
												
												<div class="col-md-6">
													<label class="form-label" for="compDate">Expected Completion Date: </label> 
													<input type="date" class="form-control" name="compDate" id="compDate" value="${notice.completion}">
												</div>
											</div>
											
											<div class="row mt-5">
												<div class="col-md-6">
													<label for="exampleColorInput" class="form-label">Product Color:</label>
													<br/>
													
													<c:forEach var="color" items="${notice.manufacturingProduct.color}">
														<div class="form-check form-check-inline">
															<input class="form-check-input" type="radio" name="color" id="${color}" value="${color}" checked>
															<label class="form-check-label" for="small" style="background-color: ${color}; height:25px; width:25px;"></label>
														</div>
													</c:forEach>
												</div>
											
												<div class="col-md-6">
													<label for="exampleColorInput" class="form-label">Product Size:</label>
													<br/>
													
													<c:forEach var="size" items="${notice.manufacturingProduct.size}">
														<div class="form-check form-check-inline">
															<input class="form-check-input" type="radio" name="size" id="${size}" value="${size}" checked>
															<label class="form-check-label" for="size">${size}</label>
														</div>
													</c:forEach>
												</div>
											</div>
											
											<div class="row mt-5">
												<div class="col-md-6">
													<label for="estBudget" class="form-label">Estimated Budget:</label>
													<input type="text" class="form-control" name = "estBudget" id="estBudget" value="${notice.estBudget}"> 
													<span id="verifyBudget" class = "error" hidden></span>
													<input type="text" class="form-control" name = "unitCost" id="unitCost" value="${product.unitCost}" hidden> 
												</div>
											
												<div class="col-md-6">
													&nbsp;
												</div>
											</div>
											
											<div class="row mt-5">
												<div class="col-md-9">
													&nbsp;
												</div>
												<div class="col-3 mb-4">
													<button type="reset" class="btn btn-danger mx-4">
														<i class="bi bi-x"></i>&nbsp;Cancel
													</button>
													<button type="submit" class="btn btn-success">
														<i class="bi bi-arrow-repeat"></i>&nbsp;Update
													</button>
												</div>
											</div>	
									</form>
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

	<!--  cdn script files -->
	<jsp:include page="/partials/cdnScripts.jsp" />

	<!--  local script files -->
	<jsp:include page="/partials/localScripts.jsp" />

	<!-- custom script -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/toggleScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/chartScript.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/commonScript/dataTableScript.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js" 
	integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg==" 
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionDemoScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionModalScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionValidationScript.js"></script>
		

	<script>
		$(document).ready(
				function() {
					$("#productNotices").addClass("active");
					$("#bar").toggleClass("open");
					$("#page-content-wrapper, #sidebar-wrapper").toggleClass(
							"toggled");
				});
	</script>
</body>
	<script>
		$('#order').click(function () {
		   document.getElementById("orderID").hidden = false;
		   document.getElementById("orderIDLabel").hidden = false;
		});
		
		$('#inhouse').click(function () {
			   document.getElementById("orderID").hidden = true;
			   document.getElementById("orderIDLabel").hidden = true;
			});
		
	$('#prodQty').on('change', function (key) {
			
			var cost = $("#unitCost").val();
			var qty = $("#prodQty").val();
			var minBudget = cost * qty;
				
			document.getElementById("verifyBudget").innerHTML = "Budget should be greater than " + minBudget + " or reduce qunatity";
			document.getElementById("verifyBudget").hidden = true;
		});
		
		$('#estBudget').on('keypress', function (key) {
			var cost = $("#unitCost").val();
			var qty = $("#prodQty").val();
			var minBudget = cost * qty;
			
			  if($('#estBudget').val() < minBudget)
				  document.getElementById("verifyBudget").hidden = false;
			  else
				  document.getElementById("verifyBudget").hidden = true;

		});
	</script>
</html>