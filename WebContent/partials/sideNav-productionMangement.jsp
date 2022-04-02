<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <nav class="fixed-top align-top" id="sidebar-wrapper" role="navigation">
			<div class="simplebar-content" style="padding: 0px;">
				<a class="sidebar-brand d-flex justify-content-center" href="#">
					<span class="align-middle brand-name">VOKOT</span>
				</a>

				<ul class="navbar-nav align-self-stretch sidenav-link">
					<li>
						<div class="border-right">
				            <div class="d-flex flex-column align-items-center text-center user-profile">
				            	<img class="user-image rounded-circle mt-3" src="<%=request.getContextPath()%>/images/user/productionManager.png">
				            	<span class="fw-bold mt-3">Gobisan</span><br/>
				            	<span class="mt-1">Production Manager</span>
				            </div>
       					</div>
					</li>

					<li class="mt-1">&nbsp;</li>

					<li class="sidebar-header">Dashboard</li>

					<li>
						<a class="nav-link text-left mx-2" id="analyticsNav" href="<%=request.getContextPath()%>/productionAnalyticsDashboard" role="button">
							<i class="bi bi-pie-chart-fill"></i>Analytics Dashboard
						</a>
					</li>

					<li class="sidebar-header mt-1">Productions</li>
					
					<li>
						<a href="<%=request.getContextPath()%>/productionStatus-view" class="nav-link text-left mx-2" id="productionStatus" role="button">
							<i class="bi bi-stack"></i>All Productions
						</a>
					</li>
					
					<li>
						<a href="<%=request.getContextPath()%>/productionRequest-view" class="nav-link text-left mx-2" id="productionRequest" role="button">
							<i class="bi bi-envelope-fill"></i>Production Requests
						</a>
					</li>
				</ul>
			</div>
			<div class="d-flex justify-content-center mt-5 mb-4">
	      		<button class="btn btn-danger mt-5" type="button" ><i class="bi bi-box-arrow-right"></i>&nbsp; Logout</button>
	   		</div>
		</nav>