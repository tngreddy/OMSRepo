
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>Stock Management System </title>
	
        <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />">
        
             
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<!-- Font Awesome -->
	    <link rel="stylesheet" href="<c:url value="resources/css/font-awesome.css"/>"/>
	     <link href="<c:url value="/resources/css/angular-flash.css" />" rel="stylesheet">
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
       
       <link rel="stylesheet" href="<c:url value="/resources/css/daterangepickerCompat.css" />" >
       <link rel="stylesheet" href="<c:url value="/resources/css/ng-flat-datepicker.css" />" >
       
       <link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css" />" >
       
       
       <link href="<c:url value="/resources/css/formValidation.min.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/anim-in-out.css" />" rel="stylesheet">
       <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
       <!-- <link href='//fonts.googleapis.com/css?family=Roboto:400,100,400italic,700italic,700' rel='stylesheet' type='text/css'> -->
       <link href="<c:url value="/resources/css/roboto.css" />" rel="stylesheet">
       
      
       <link href="<c:url value="/resources/css/app.css" />" rel="stylesheet">
      <script type="text/javascript">
      if (!sessionStorage.length) {
  		// Ask other tabs for session storage
  		localStorage.setItem('getSessionStorage', Date.now());
  		};

  		window.addEventListener('storage', function(event) {

  		//console.log('storage event', event);

  		if (event.key == 'getSessionStorage') {
  			// Some tab asked for the sessionStorage -> send it

  			localStorage.setItem('sessionStorage', JSON.stringify(sessionStorage));
  			localStorage.removeItem('sessionStorage');

  		} else if (event.key == 'sessionStorage' && !sessionStorage.length) {
  			// sessionStorage is empty -> fill it

  			var data = JSON.parse(event.newValue),
  						value;

  			for (key in data) {
  				sessionStorage.setItem(key, data[key]);
  			}

  		}

  	});
      </script>
	</head>
<body ng-app="omsApp" class="hold-transition skin-blue sidebar-mini">

<div flash-message="6000" ></div> 	



<div class="se-pre-con"></div>


	
    <div id="main" >
		<div ui-view ></div>

	</div>
	<!-- ... -->

<!-- jQuery 2.1.4 -->
<script src="<c:url value="/resources/js/jquery/jQuery-2.1.4.min.js"/>"></script>
<!-- 
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> -->
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap3-wysihtml5.all.min.js" />"></script>
<script src="<c:url value="/resources/js/angular/moment.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-route.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-animate.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-validator.js" />"></script>
<script src="<c:url value="/resources/js/angular/angular-ui-router.js"/>"></script>
<script src="<c:url value="/resources/js/angular/ng-flat-datepicker.js"/>"></script>
<script src="<c:url value="/resources/js/angular/angular-flash.js"/>"></script>
<%-- <script src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/dataTables.bootstrap.min.js" />"></script> --%>
<script src="<c:url value="/resources/js/datatables/datatables.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/angular-datatables.min.js"/>"></script>
<script src="<c:url value="/resources/js/datatables/angular-datatables.bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/ui-bootstrap-tpls-0.14.2.min.js"/>"></script>
<script src="<c:url value="/resources/js/formValidation.min.js"/>"></script>
<script src="<c:url value="/resources/js/framework/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/anim-in-out.js" />"></script>
<script src="<c:url value="/resources/js/angular/datepicker.js" />"></script>




 

<%-- <script src="<c:url value="/resources/js/datatables/dataTables.buttons.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/buttons.bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/datatables/buttons.colVis.min.js" />"></script>

<script src="<c:url value="https://cdn.datatables.net/buttons/1.0.3/js/buttons.html5.min.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/buttons/1.0.3/js/buttons.flash.min.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/buttons/1.0.3/js/buttons.print.min.js" />"></script> --%>





<script src="<c:url value="/resources/js/app.js" />"></script>
<script src="<c:url value="/resources/js/service/common_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/base_controller.js" />"></script>
<script src="<c:url value="/resources/js/directives/custom_directives.js" />"></script>
<script src="<c:url value="/resources/js/controller/category_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/category_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/product_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/product_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/customer_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/customer_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/supplier_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/supplier_service.js" />"></script>

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
<script src="<c:url value="/resources/js/controller/user_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/user_service.js" />"></script>
<script src="<c:url value="/resources/js/controller/login_controller.js" />"></script>
<script src="<c:url value="/resources/js/service/login_service.js" />"></script>


<script type="text/javascript">
$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");
	
	
	
});



</script>
</body>
</html>
