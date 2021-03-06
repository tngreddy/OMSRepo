
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
        
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<!-- Font Awesome -->
	    <link rel="stylesheet" href="<c:url value="resources/css/font-awesome.css"/>"/>
	    <!-- Ionicons -->
	   <%--  <link rel="stylesheet" href="<c:url value="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"/>"/> --%>
	    <link href="<c:url value="/resources/css/AdminLTE.min.css" />" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value="/resources/css/skins/_all-skins.css" />" />
		<!-- bootstrap wysihtml5 - text editor -->
		<%-- <link href="<c:url value="/resources/css/bootstrap3-wysihtml5.min.css" />" rel="stylesheet"> --%>
	
		<!-- DataTables -->
       <link rel="stylesheet" href="<c:url value="/resources/css/dataTables.bootstrap.css" />" >
       
   <%--     
       <link rel="stylesheet" href="<c:url value="https://cdn.datatables.net/1.10.9/css/dataTables.bootstrap.min.css" />" > --%>
       <link rel="stylesheet" href="<c:url value="/resources/css/buttons.bootstrap.min.css" />" >
     
       
       
       <link href="<c:url value="/resources/css/formValidation.min.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/anim-in-out.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
       <!-- <link href='//fonts.googleapis.com/css?family=Roboto:400,100,400italic,700italic,700' rel='stylesheet' type='text/css'> -->
       <link href="<c:url value="/resources/css/roboto.css" />" rel="stylesheet">
       
       
       <link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
      

</style>
	</head>
<body ng-app="omsApp" class="hold-transition skin-blue sidebar-mini">

	<header class="main-header">
       
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-fixed-top" role="navigation">
        
         <!-- Logo -->
        <a href="/oms" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>OMS</b></span>
        </a>
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>

          <ul class="nav navbar-nav navbar-left">
			<li><a href="/oms"><i class="fa fa-home"></i> Home</a></li>
			<li><a ui-sref="category"><i class="fa fa-th-large"></i> Category</a></li>
			<li><a ui-sref="product"><i class="fa fa-star"></i> Product</a></li>
			<li><a ui-sref="customer"><i class="fa fa-user"></i> Customer</a></li>
			<li><a ui-sref="supplier"><i class="fa fa-user"></i> Supplier</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-exchange"></i> Transaction<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a ui-sref="gin"><i class="fa fa-arrow-left"></i> GIN</a></li> 
						<li><a ui-sref="rin"><i class="fa fa-arrow-left"></i> RIN</a></li>
						<li><a ui-sref="goc"><i class="fa fa-arrow-right"></i> GOC</a></li>
						<li><a ui-sref="grc"><i class="fa fa-arrow-right"></i> GRC</a></li>
						<li><a ui-sref="po"><i class="fa fa-arrow-right"></i> PO</a></li>
					</ul>
			</li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text"></i> Reports<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a ui-sref="stockSummary"></i>Stock Summary</a></li>
						<li><a ui-sref="stockRecord"></i>Stock Record</a></li>
					</ul>
			</li>
		  </ul>
		  



        <!--    <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              Messages: style can be found in dropdown.less
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 4 messages</li>
                  <li>
                    inner menu: contains the actual data
                    <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 200px;"><ul class="menu" style="overflow: hidden; width: 100%; height: 200px;">
                      <li>start message
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>end message
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            AdminLTE Design Team
                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Developers
                            <small><i class="fa fa-clock-o"></i> Today</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Sales Department
                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Reviewers
                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                    </ul><div class="slimScrollBar" style="width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div>
                  </li>
                  <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
              </li>
              Notifications: style can be found in dropdown.less
              <li class="dropdown notifications-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning">10</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 10 notifications</li>
                  <li>
                    inner menu: contains the actual data
                    <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 200px;"><ul class="menu" style="overflow: hidden; width: 100%; height: 200px;">
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-aqua"></i> 5 new members joined today
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-red"></i> 5 new members joined
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-user text-red"></i> You changed your username
                        </a>
                      </li>
                    </ul><div class="slimScrollBar" style="width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div>
                  </li>
                  <li class="footer"><a href="#">View all</a></li>
                </ul>
              </li>
              Tasks: style can be found in dropdown.less
              <li class="dropdown tasks-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-flag-o"></i>
                  <span class="label label-danger">9</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 9 tasks</li>
                  <li>
                    inner menu: contains the actual data
                    <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 200px;"><ul class="menu" style="overflow: hidden; width: 100%; height: 200px;">
                      <li>Task item
                        <a href="#">
                          <h3>
                            Design some buttons
                            <small class="pull-right">20%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">20% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                      <li>Task item
                        <a href="#">
                          <h3>
                            Create a nice theme
                            <small class="pull-right">40%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">40% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                      <li>Task item
                        <a href="#">
                          <h3>
                            Some task I need to do
                            <small class="pull-right">60%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">60% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                      <li>Task item
                        <a href="#">
                          <h3>
                            Make beautiful transitions
                            <small class="pull-right">80%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">80% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                    </ul><div class="slimScrollBar" style="width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div>
                  </li>
                  <li class="footer">
                    <a href="#">View all tasks</a>
                  </li>
                </ul>
              </li>
              User Account: style can be found in dropdown.less
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  <span class="hidden-xs">Gowtham</span>
                </a>
                <ul class="dropdown-menu">
                  User image
                  <li class="user-header">
                    <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    <p>
                      Alexander Pierce - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  Menu Body
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </li>
                  Menu Footer
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              Control Sidebar Toggle Button
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div> 
        </nav>-->
      </header>
     <%-- <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar" style="height: auto;">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="<c:url value="/resources/img/user2-160x160.jpg"/>" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Alexander Pierce</p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="active treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-files-o"></i>
                <span>Layout Options</span>
                <span class="label label-primary pull-right">4</span>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
                <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
                <li><a href="pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
                <li><a href="pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
              </ul>
            </li>
            <li>
              <a href="pages/widgets.html">
                <i class="fa fa-th"></i> <span>Widgets</span> <small class="label pull-right bg-green">new</small>
              </a>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>Charts</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
                <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
                <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
                <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-laptop"></i>
                <span>UI Elements</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/UI/general.html"><i class="fa fa-circle-o"></i> General</a></li>
                <li><a href="pages/UI/icons.html"><i class="fa fa-circle-o"></i> Icons</a></li>
                <li><a href="pages/UI/buttons.html"><i class="fa fa-circle-o"></i> Buttons</a></li>
                <li><a href="pages/UI/sliders.html"><i class="fa fa-circle-o"></i> Sliders</a></li>
                <li><a href="pages/UI/timeline.html"><i class="fa fa-circle-o"></i> Timeline</a></li>
                <li><a href="pages/UI/modals.html"><i class="fa fa-circle-o"></i> Modals</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-edit"></i> <span>Forms</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/forms/general.html"><i class="fa fa-circle-o"></i> General Elements</a></li>
                <li><a href="pages/forms/advanced.html"><i class="fa fa-circle-o"></i> Advanced Elements</a></li>
                <li><a href="pages/forms/editors.html"><i class="fa fa-circle-o"></i> Editors</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-table"></i> <span>Tables</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/tables/simple.html"><i class="fa fa-circle-o"></i> Simple tables</a></li>
                <li><a href="pages/tables/data.html"><i class="fa fa-circle-o"></i> Data tables</a></li>
              </ul>
            </li>
            <li>
              <a href="pages/calendar.html">
                <i class="fa fa-calendar"></i> <span>Calendar</span>
                <small class="label pull-right bg-red">3</small>
              </a>
            </li>
            <li>
              <a href="pages/mailbox/mailbox.html">
                <i class="fa fa-envelope"></i> <span>Mailbox</span>
                <small class="label pull-right bg-yellow">12</small>
              </a>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span>Examples</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li>
                <li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
                <li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i> Login</a></li>
                <li><a href="pages/examples/register.html"><i class="fa fa-circle-o"></i> Register</a></li>
                <li><a href="pages/examples/lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li>
                <li><a href="pages/examples/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
                <li><a href="pages/examples/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
                <li><a href="pages/examples/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-share"></i> <span>Multilevel</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
                <li>
                  <a href="#"><i class="fa fa-circle-o"></i> Level One <i class="fa fa-angle-left pull-right"></i></a>
                  <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                    <li>
                      <a href="#"><i class="fa fa-circle-o"></i> Level Two <i class="fa fa-angle-left pull-right"></i></a>
                      <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
              </ul>
            </li>
            <li><a href="documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
            <li class="header">LABELS</li>
            <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside> --%>

	<div id="main" >
		<div ui-view >
			<div class="content-wrapper" ng-controller="CommonController">
				<section class="content-header">
		          <h1>
		            Dashboard
		           </h1>
		          <ol class="breadcrumb">
		            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		            <li class="active">Dashboard</li>
		          </ol>
		        </section>
				<section class="content">
				<div class="row">
		            <div class="col-lg-3 col-xs-6">
		              <!-- small box -->
		              <div class="small-box bg-aqua">
		                <div class="inner">
		                  <h3 ng-bind="homeResponseDto.categoryCount"></h3>
		                  <p>Categories</p>
		                </div>
		                <div class="icon">
		                  <i class="ion ion-bag"></i>
		                </div>
		                <a ui-sref="category" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		              </div>
		            </div><!-- ./col -->
		            <div class="col-lg-3 col-xs-6">
		              <!-- small box -->
		              <div class="small-box bg-green">
		                <div class="inner">
		                  <h3 ng-bind="homeResponseDto.productCount"></h3>
		                  <p>Products</p>
		                </div>
		                <div class="icon">
		                  <i class="ion ion-stats-bars"></i>
		                </div>
		                <a ui-sref="product" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		              </div>
		            </div><!-- ./col -->
		            <div class="col-lg-3 col-xs-6">
		              <!-- small box -->
		              <div class="small-box bg-yellow">
		                <div class="inner">
		                  <h3 ng-bind="homeResponseDto.customerCount"></h3>
		                  <p>Customers</p>
		                </div>
		                <div class="icon">
		                  <i class="ion ion-person-add"></i>
		                </div>
		                <a ui-sref="customer" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		              </div>
		            </div><!-- ./col -->
		            <div class="col-lg-3 col-xs-6">
		              <!-- small box -->
		              <div class="small-box bg-red">
		                <div class="inner">
		                  <h3 ng-bind="homeResponseDto.supplierCount"></h3>
		                  <p>Suppliers</p>
		                </div>
		                <div class="icon">
		                  <i class="ion ion-pie-graph"></i>
		                </div>
		                <a ui-sref="supplier" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
		              </div>
		            </div><!-- ./col -->
		          </div>
						</section>
			</div>

		
	</div>
	<!-- ... -->

<!-- jQuery 2.1.4 -->
<script src="<c:url value="/resources/js/jquery/jQuery-2.1.4.min.js"/>"></script>
<!-- 
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> -->
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap3-wysihtml5.all.min.js" />"></script>

<script src="<c:url value="/resources/js/angular/angular.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-route.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-animate.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-validator.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-ui-router.js"/>"></script>
<%-- <script src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/dataTables.bootstrap.min.js" />"></script> --%>
<script src="<c:url value="/resources/js/datatables/datatables.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/angular-datatables.min.js"/>"></script>
<script src="<c:url value="/resources/js/datatables/angular-datatables.bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/ui-bootstrap-tpls-0.14.2.min.js"/>"></script>
<script src="<c:url value="/resources/js/formValidation.min.js"/>"></script>
<script src="<c:url value="/resources/js/framework/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/anim-in-out.js" />"></script>

 

<%-- <script src="<c:url value="/resources/js/datatables/dataTables.buttons.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/buttons.bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/buttons.colVis.min.js" />"></script>

<script src="<c:url value="https://cdn.datatables.net/buttons/1.0.3/js/buttons.html5.min.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/buttons/1.0.3/js/buttons.flash.min.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/buttons/1.0.3/js/buttons.print.min.js" />"></script> --%>





<script src="<c:url value="/resources/js/app.js" />"></script>

<script src="<c:url value="/resources/js/directives/custom_directives.js" />"></script>
<script src="<c:url value="/resources/js/controller/category_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/category_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/product_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/product_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/customer_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/customer_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/supplier_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/supplier_service.js" />"></script>
<script src="<c:url value="/resources/js/service/common_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/common_controller.js" />"></script>
<script src="<c:url value="/resources/js/controller/gin_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/gin_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/goc_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/goc_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/rin_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/rin_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/grc_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/grc_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/reports_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/reports_service.js" />"></script>


</body>
</html>
