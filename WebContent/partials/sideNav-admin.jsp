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
				            	<img class="user-image rounded-circle mt-3" src="<%=request.getContextPath()%>/images/profile/avatar1.jpg">
				            	<span class="fw-bold mt-3">Amarasekara Podimalli</span><br/>
				            	<span class="mt-1">Chief Executive Officer</span>
				            </div>
       					</div>
					</li>

					<li class="mt-1">&nbsp;</li>

					<li class="sidebar-header">Dashboard</li>

					<li>
						<a class="nav-link text-left active" role="button">
							<i class="bi bi-pie-chart-fill"></i>Analytics Dashboard
						</a>
					</li>

					<li>
						<a class="nav-link text-left" role="button">
							<i class="bi bi-speedometer"></i>Management Dashboard
						</a>
					</li>

					<li class="sidebar-header">Function 1</li>
					
					<li class="has-sub">
						<a class="nav-link collapsed text-left" data-bs-toggle="collapse" href="#collapseFunction1" role="button" aria-expanded="false" aria-controls="collapseFunction1">
								<i class="bi bi-gear-fill"></i>Function 1.1
								<span class="chevron-icon ms-auto"><i class="bi bi-chevron-down"></i></span>
						</a>
						<div class="collapse menu mega-dropdown" id="collapseFunction1">
							<div class="dropdown" aria-labelledby="navbarDropdown">
								<div class="container-fluid">
									<div class="row">
										<div class="col-lg-12 px-2">
											<div class="submenu-box">
												<ul class="navbar-nav m-0">
													<li><a class="nav-link" href="#"><i class="bi bi-pie-chart-fill"></i>&nbsp;Function 1.1.1 </a></li> 
													<li><a class="nav-link" href="#"><i class="bi bi-speedometer">&nbsp;</i>Function 1.1.2</a></li>  
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>

					<li class="sidebar-header">Function 2</li>

					<li>
						<a class="nav-link text-left" role="button">
							<i class="bi bi-gear-fill"></i>Function 2.1
						</a>
					</li>

					<li>
						<a class="nav-link text-left" role="button">
							<i class="bi bi-gear-fill"></i>Function 2.2
						</a>
					</li>

					<li class="sidebar-header">Function 3</li>

					<li>
						<a class="nav-link text-left" role="button">
							<i class="bi bi-gear-fill"></i>Function 3.1
						</a>
					</li>

					<li>
						<a class="nav-link text-left" role="button">
							<i class="bi bi-gear-fill"></i>Function 3.2
						</a>
					</li>
				</ul>
			</div>
			<div class="d-flex justify-content-center mt-4 mb-4">
	      		<button class="btn btn-danger" type="button" ><i class="bi bi-box-arrow-right"></i>&nbsp; Logout</button>
	   		</div>
		</nav>