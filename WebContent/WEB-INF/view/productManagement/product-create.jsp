<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Create Product</title>

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
								<div class="d-sm-flex align-items-center justify-content-between mt-3 mb-3">
									<h1 class="h3 mb-0 text-gray-800">Create New Product</h1>
									<button class="d-none d-sm-inline-block btn btn-danger shadow" id="fillProduct">
										<i class="bi bi-pencil-square"></i>&nbsp; Demo
									</button>
								</div>
							</div>
							<!--  Page Title && Description End -->

							<!-- Main content start -->
							<div class="col-md-12 mt-3 mb-3">
								<div class="card">
									<div class="card-body px-5">
										<form action="productInsert" method="post" name = "productForm" id="productForm" enctype="multipart/form-data">
										
											<div class="row mt-3">
												<div class="col-md-6">
													<label for="productTilte" class="form-label">Product Title: </label> 
													<input type="text" class="form-control" id="productTitle" name="productTitle">
													
												</div>
												
												<div class="col-md-6">
													<label for="lineType"  class="form-label">Product Collection:</label> 
													<select class="form-select" id="productCollection" name="productCollection" aria-label="Default select example">
														<option></option>
														<c:forEach var="collection" items="${collectionList}">
															<option value="${collection.collectionID}">${collection.collectionTitle}</option>
														</c:forEach>
													</select>
													
												</div>
											</div>

											<div class="row mt-5">
												<div class="col-md-6">
													<label for="exampleColorInput" class="form-label">Product Type:</label>
													
													<div class="form-check col-md-2" style="display: flex;">
														<div>
															<input class="form-check-input" type="radio" name="productType" id="mens" value="0" checked="checked"> 
															<label class="form-check-label" for="exampleRadios1"> Mens </label>
														</div>
											
														<div class="mx-5">	
															<input class="form-check-input" type="radio" name="productType" id="womens" value="1"> 
															<label class="form-check-label" for="exampleRadios2"> Womens </label>
														</div>
														
														
													</div>
												</div>
												
												<div class="col-md-6">
													<label class="form-label" for="estBudget">Manufacturing Cost per Unit (LKR): </label>
													<input type="text" class="form-control" id="unitCost" name="unitCost"/>	
												</div>
											</div>

											<div class="row mt-5">
												<div class="col-md-6">
													<label for="exampleColorInput" class="form-label">Colors:</label>
													<div class="color_wrapper" style="display: flex;">
														<div style="display: flex;">
																<input class="form-control form-control-color mx-2" type="color" name="color[]" value="#303C6C"/>&nbsp;
																<label for="color[]" class="addColor" title="Add field" style="border-radius: 100%;"><i class="bi bi-plus-lg text-success"></i></label>		
														</div>
													</div>
												</div>
												
												<div class="col-md-6">
													<label for="sizeRange" class="form-label">Size Range:</label>
													
													<div class="mt-1" style="display: flex;">
														<div>
															<div class="form-check">
																<label class="form-check-label" for="s">S</label>
																<input class="form-check-input" type="checkbox" id="s" value="s" name="size[]">
															</div>
														</div>
														
														<div class=" mx-5">
															<div class="form-check">
																<label class="form-check-label" for="m">M</label>
																<input class="form-check-input" type="checkbox" id="m" value="m" name="size[]"> 
															</div>
														</div>
														
														<div>
															<div class="form-check">
																<label class="form-check-label" for="l">L</label>
																<input class="form-check-input" type="checkbox" id="l" value="l" name="size[]"> 
															</div>
														</div>														
													</div>
												</div>

											<div class="row mt-5">
												<div class="col-md-6">
													<label class="form-label" for="productImg">Choose sample image: </label> 
													<input type="file" onchange="readUrl(this);" class="form-control" id="productImg", name="productImg"/>
												</div>
	
												<div class="col-md-6">&nbsp;</div>
											</div>
											
											<div class="row mt-3">
												<div class="col-md-3">
													<img id="prev_img"/>
												</div>
											
												<div class="col-md-9">
													&nbsp;
												</div>
											</div>
											
											<div class="row mt-3">
												<div class="col-md-9 mt-3">
													&nbsp;
												</div>
											
												<div class="col-3 mb-4 mt-3">
													<button type="reset" class="btn btn-warning mx-4">
														<i class="bi bi-arrow-repeat"></i>&nbsp;Reset
													</button>
													<button type="submit" class="btn btn-success">
														<i class="bi bi-save"></i>&nbsp;Save
													</button>
												</div>
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
					$("#allProducts").addClass("active");
					$("#bar").toggleClass("open");
					$("#page-content-wrapper, #sidebar-wrapper").toggleClass(
							"toggled");
				});
	</script>
	
	<script>
			function readUrl(input) {
				if (input.files && input.files[0]) {
					// open file reader
					var reader = new FileReader();

					reader.onload = function(e)
					{
						$("#prev_img").attr("src", e.target.result)
    						.css("width", "80px")
    						.css("height", "80px")
    						.css("border", "3px solid black");
					}

					// execute reader
					reader.readAsDataURL(input.files[0]);
				}
			}
		</script>
</body>
</html>