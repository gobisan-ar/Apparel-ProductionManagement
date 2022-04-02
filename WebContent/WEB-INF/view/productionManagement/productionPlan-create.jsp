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

<title>Create Production Plan</title>

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
									<h1 class="h3 mb-0 text-gray-800">Create Production Plan</h1>
									<button class="d-none d-sm-inline-block btn btn-danger shadow" id="fillPlan">
										<i class="bi bi-pencil-square"></i>&nbsp; Demo
									</button>
								</div>
							</div>
							<!--  Page Title && Description End -->

							<!-- Main content start -->
							<div class="col-md-12 mt-3 mb-3">
								<div class="card">
									<div class="card-body px-4">
										<form class="row g-4" action="plan-create" id="planForm" name="planForm">
										
										<c:if test="${not empty sessionScope.nid}">
											<input type="text" id="nid" name ="nid" value="${sessionScope.nid}" hidden>
											<%session.removeAttribute("nid");%>
										</c:if>
									
										
											<div class = "row">
												<h1 class="h4 mt-4 mb-0">Production Route</h1>
											</div>

											<div class="row mt-4">
												<div class="col-md-6">
													<label for="lineType">Select Production Line:</label>
													<select class="form-select mt-1" id="line" name="line" aria-label="Default select example">
														<option selected></option>
														<c:forEach var="line" items="${lineList}">
															<option value="${line.lineID}">Line ${line.lineID}</option>
														</c:forEach>
													</select>
												</div>
	
												<div class="col-md-6">
													<label for="supervisor">Select Production Supervisor:</label> 
													<select class="form-select mt-1" id="supervisor" name="supervisor" aria-label="Default select example">
														<option selected></option>
														<c:forEach var="sup" items="${supervisorlist}">
															<option value="${sup.supID}">${sup.supName}</option>
														</c:forEach>
													</select>
												</div>
											</div>

											<div class="row mt-4">
												<label class="form-check-label" for="lineUnits">Route Units:</label>
												
												<div class="col-md-2 mt-2">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" id="cuttingUnit" name="unit[]" value="cutting"> 
														<label class="form-check-label" for="cuttingUnit">Cutting</label>
													</div>
												</div>
												
												<div class="col-md-2 mt-2">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" id="printingUnit" name="unit[]" value="printing"> 
														<label class="form-check-label" for="printingUnit">Printing</label>
													</div>
												</div>
												
												<div class="col-md-2 mt-2">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" id="sewingUnit" name="unit[]" value="sewing">
														<label class="form-check-label" for="sewingUnit">Sewing</label>
													</div>
												</div>
												
												<div class="col-md-2 mt-2">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" id="washingUnit" name="unit[]"  value="washing">
														<label class="form-check-label" for="washingUnit">Washing</label>
													</div>
												</div>

												<div class="col-md-2 mt-2">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" id="pressingUnit" name="unit[]" value="pressing"> 
														<label class="form-check-label" for="pressingUnit">Pressing</label>
													</div>
												</div>

												<div class="col-md-2 mt-2">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" id="packagingUnit" name="unit[]" value="packaging"> 
														<label class="form-check-label" for="packagingUnit">Packaging</label>
													</div>
												</div>
												
											</div>

											<div class="row mt-5">
												<h1 class="h4 mb-0">Production Schedule</h1>
											</div>
											
											<div class = "row mt-4">
												<div class="col-md-6">
													<label for="startDate" class="form-label">Start Date:</label> 
													<input type="Date" class="form-control" id="startDate" name="startDate">
												</div>
	
												<div class="col-md-6">
													<label for="endDate" class="form-label">End Date:</label> 
													<input type="Date" class="form-control" id="endDate" name="endDate">
												</div>
											</div>
											
											<div class= "row mt-5">
												<label class="form-check-label" for="equipments"><strong>Unit Days Allocation:</strong></label>
											</div>
											
											<div class= "row mt-5">
												<div class="col-md-2 cuttingDays">
													<div class="form-check">
														<label class="form-check-label" for="cuttingDays">Cutting</label>
													</div>
												</div>
												
												<div class="col-md-3 cuttingDays">
													<div class="form-floating mb-3 cuttingDays">
														<input type="number" class="form-control" id="cuttingDays" name="cuttingDays" value = "0">
														<label for="cuttingDays">Number of Days</label>
													</div>
												</div>
												
												<div class="col-md-1 cuttingDays">&nbsp;</div>

												<div class="col-md-2 printingDays">
													<div class="form-check">
														<label class="form-check-label" for="printingDays">Printing</label>
													</div>
												</div>
											
												<div class="col-md-3 printingDays">
													<div class="form-floating mb-3">
														<input type="number" class="form-control" name="printingDays" id="printingDays" value = "0">
														<label for="printingDays">Number of Days</label>
													</div>
												</div>
												
												<div class="col-md-1 printingDays">&nbsp;</div>
											</div>
											
											<div class="row mt-4">
												<div class="col-md-2 sewingDays">
													<div class="form-check">
														<label class="form-check-label" for="sewingDays">Sewing</label>
													</div>
												</div>

												<div class="col-md-3 sewingDays">
													<div class="form-floating mb-3">
														<input type="number" class="form-control" id="sewingDays" name="sewingDays" value = "0">
														<label for="sewingDays">Number of Days</label>
													</div>
												</div>
	
												<div class="col-md-1 sewingDays">&nbsp;</div>
	
												<div class="col-md-2 washingDays">
													<div class="form-check">
														<label class="form-check-label" for="washingDays">Washing</label>
													</div>
												</div>
	
												<div class="col-md-3 washingDays">
													<div class="form-floating mb-3">
														<input type="number" class="form-control" id="washingDays" name="washingDays" value = "0">
														<label for="sewingDays">Number of Days</label>
													</div>
												</div>
	
												<div class="col-md-1 washingDays">&nbsp;</div>
											</div>
											
											<div class="row mt-4">
												
												<div class="col-md-2 pressingDays">
													<div class="form-check">
														<label class="form-check-label" for="pressingDays">Pressing</label>
													</div>
												</div>
	
												<div class="col-md-3 pressingDays">
													<div class="form-floating mb-3">
														<input type="number" class="form-control" id="pressingDays" name="pressingDays" value = "0">
														<label for="pressingDays">Number of Days</label>
													</div>
												</div>
	
												<div class="col-md-1 pressingDays">&nbsp;</div>
	
												<div class="col-md-2 packagingDays">
													<div class="form-check">
														<label class="form-check-label" for="packagingDays">Packaging</label>
													</div>
												</div>
	
												<div class="col-md-3 packagingDays">
													<div class="form-floating mb-4">
														<input type="number" class="form-control" id="packagingDays" name="packagingDays" value = "0">
														<label for="cuttingQty">Number of Days</label>
													</div>
												</div>
	
												<div class="col-md-1 packagingDays">&nbsp;</div>
											</div>
											
											<div class= "row mt-5 totalDays d-flex flex-row">
												<div class="col-md-auto text-dark bg-light">
													<span class="text-dark bg-light">Total Days:&nbsp;</span>
													<input class="text-dark bg-light mx-1" type="text" id="totalDays"  name="totalDays" value = "0" style="border:none; width:30px;" readonly/>
												</div>
												
												<div class="col-md-10">
													&nbsp;
												</div>
											</div>

											<div class= "row mt-4 countDays d-flex flex-row">
												<div class="col-md-auto text-dark bg-light">
													<span class="text-dark bg-light">Days Allocated:&nbsp;</span>
													<input class="text-dark bg-light mx-1" type="text" id="countDays"  name="countDays" value = "0" style="border:none; width:30px;" readonly/>
												</div>
												
												<div class="col-md-10">
													&nbsp;
												</div>
											</div>

											<div class="row mt-4">
												<div class="col-md-9">&nbsp;</div>

												<div class="col-3 mb-4">
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
					$("#controlNav, #lineNav").addClass("active");
					$("#bar").toggleClass("open");
					$("#page-content-wrapper, #sidebar-wrapper").toggleClass(
							"toggled");
				});
		
		$(".cuttingDays").hide();
		
		$(".printingDays").hide();

		$(".sewingDays").hide();

		$(".washingDays").hide();

		$(".pressingDays").hide();

		$(".packagingDays").hide();
		
				$("#cuttingUnit").click(function() {
		            if ($(this).is(":checked")) {
		                $(".cuttingDays").show();
		            } else {
		            	 $(".cuttingDays").hide();
		            }
		        });
				
				$("#printingUnit").click(function() {
		            if ($(this).is(":checked")) {
		                $(".printingDays").show();
		            } else {
		            	 $(".printingDays").hide();
		            }
		        });
				
				$("#sewingUnit").click(function() {
		            if ($(this).is(":checked")) {
		                $(".sewingDays").show();
		            } else {
		            	 $(".sewingDays").hide();
		            }
		        });
				
				$("#washingUnit").click(function() {
		            if ($(this).is(":checked")) {
		                $(".washingDays").show();
		            } else {
		            	 $(".washingDays").hide();
		            }
		        });
				
				$("#pressingUnit").click(function() {
		            if ($(this).is(":checked")) {
		                $(".pressingDays").show();
		            } else {
		            	 $(".pressingDays").hide();
		            }
		        });
				
				$("#packagingUnit").click(function() {
		            if ($(this).is(":checked")) {
		                $(".packagingDays").show();
		            } else {
		            	 $(".packagingDays").hide();
		            }
		        });
				
				
				$("#startDate, #endDate").on('change',function(key) {
					   var startDate = $("#startDate").val();
					   var endDate = $("#endDate").val();
					     
					   // To calculate the time difference of two dates
					   var Difference_In_Time = new Date(endDate).getTime() - new Date(startDate).getTime();
					     
					   // To calculate the no. of days between two dates
					   var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
					   
					   $("#totalDays").val(Difference_In_Days);
		        });
				
				$("#cuttingDays, #printingDays, #sewingDays, #washingDays, #pressingDays, #packagingDays").on('change',function(key) {
					   var cuttingDays = parseInt($("#cuttingDays").val());
					   var printingDays = parseInt($("#printingDays").val());
					   var sewingDays = parseInt($("#sewingDays").val());
					   var washingDays = parseInt($("#washingDays").val());
					   var pressingDays = parseInt($("#pressingDays").val());
					   var packagingDays = parseInt($("#packagingDays").val());
					     
					   var sumDays = cuttingDays + printingDays + sewingDays + washingDays + pressingDays + packagingDays;
					   
					   $("#countDays").val(sumDays); 
		        });
				
				
	</script>

</body>
</html>