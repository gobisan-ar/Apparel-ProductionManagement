<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Production Notices</title>

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
									<h1 class="h3 mb-0 text-gray-800">Production Notice</h1>
								</div>
							</div>
							<!--  Page Title && Description End -->

							<!-- Main content start -->
							<div class="col-md-12 mt-3 mb-3">
								<div class="card">
									<div class="card-body px-5">

											<div class="row mt-4">
												<h1 class="h4">Notice Details: &nbsp;${notice.noticeID}</h1>
											</div>
											
											<div class="row mt-4">
												<div class="col-md-6">
													<span class="fw-bold">Product Collection:</span>&nbsp;${notice.originProduct.collectionName}</br></br>
													<span class="fw-bold">Expected Delivery Date:</span>&nbsp;${notice.completion}</br></br>
													<span class="fw-bold">Size:</span>&nbsp;${notice.manufacturingProduct.size}</br></br>
												</div>

												<div class="col-md-6">
												 	<span class="fw-bold">Qunatity (Units):</span>&nbsp;${notice.quantity}</br></br>
													<span class="fw-bold">Estimated Budget (LKR):</span>&nbsp;${notice.estBudget}</br></br>
													<span class="fw-bold">Color:</span>&nbsp;<span style="color: ${notice.manufacturingProduct.color}; background-color:${notice.manufacturingProduct.color}">${notice.manufacturingProduct.color}</span></br></br>
												</div>
											</div>

											<div class="row mt-5">
												<div class="col-md-7">&nbsp;</div>

												<div class="col-5 mb-4">
													<a href="<%=request.getContextPath()%>/noticeStatus-update?nid=${notice.noticeID}&sts=-1" style="text-decoration: none;">
														<button type="reset" class="btn btn-danger mx-4">
															<i class="bi bi-x-square"></i>&nbsp;Reject
														</button>
													</a>
													
													<a href="<%=request.getContextPath()%>/noticeStatus-update?nid=${notice.noticeID}&sts=0" style="text-decoration: none;">
														<button type="reset" class="btn btn-warning mx-4">
															<i class="bi bi-pause-btn"></i>&nbsp;Hold
														</button>
													</a>
													
													<a href="<%=request.getContextPath()%>/planForm-create?nid=${notice.noticeID}" style="text-decoration: none;">
														<button class="btn btn-success mx-4">
															<i class="bi bi-clock"></i>&nbsp;Schedule
														</button>
													</a>
												</div>
											</div>
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
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionDemoScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionModalScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/productionValidationScript.js"></script>

	<script>
		$(document).ready(
				function() {
					$("#controlNav, #lineNav").addClass("active");
					$("#bar").toggleClass("open");
					$("#page-content-wrapper, #sidebar-wrapper").toggleClass(
							"toggled");
				});
	</script>

</body>
</html>