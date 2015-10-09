<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<script src="<c:url value="/resources/js/angular/angular.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
<script src="<c:url value="/resources/js/controller/category_controller.js" />"></script>
<script src="<c:url value="/resources/js/controller/product_controller.js" />"></script>

</head>
<body ng-app="omsApp">
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">Operations Management System</a>
				</div>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="/oms/"><i class="fa fa-home"></i>Home</a></li>
					<li><a href="/oms/categoryHome"><i class="fa fa-shield"></i>Category</a></li>
					<li><a href="/oms/productHome"><i class="fa fa-comment">Product</i></a></li>
				</ul>
			</div>
		</nav>
	</header>

	<div id="main">
		<div ng-view></div>
	</div>

</body>
</html>