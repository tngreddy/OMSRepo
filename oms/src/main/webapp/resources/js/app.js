
var omsApp = angular.module('omsApp', ['ui.router','datatables','flash','ui.bootstrap','ngAnimate','anim-in-out','angularValidator','ngFlatDatepicker','date-picker']);


omsApp.config(function($stateProvider, $urlRouterProvider) {
	    
	    $urlRouterProvider.otherwise('login');
	    
	    $stateProvider
		    .state('login', {
	        	url: '/login',
	            templateUrl: 'resources/templates/login.html',
	            controller: 'LoginController'
	        })
	         .state('base', {
	        	url: '',
	            templateUrl: 'resources/templates/common/navbar.html',
	            controller: 'BaseController'
	            
	        })
		    .state('base.dashboard', {
	        	url: '/dashboard',
	            templateUrl: 'resources/templates/dashboard.html',
	            controller: 'CommonController'
	        })
	         .state('base.category', {
	        	url: '/categoryHome',
	            templateUrl: 'resources/templates/category.html',
	            controller: 'CategoryController'
	        })
	        
	        .state('base.product', {
	        	url: '/productHome',
	            templateUrl: 'resources/templates/product.html',
	            controller: 'ProductController'
	        })
	        
	        .state('base.customer', {
	        	url: '/customerHome',
	            templateUrl: 'resources/templates/customer.html',
	            controller: 'CustomerController'
	        })
	        
	        .state('base.supplier', {
	        	url: '/supplierHome',
	            templateUrl: 'resources/templates/supplier.html',
	            controller: 'SupplierController'
	        })
	         .state('base.gin', {
	        	url: '/goodsInwardNote',
	            templateUrl: 'resources/templates/gin.html',
	            controller: 'GINController'
	        })
	        
	         .state('base.rin', {
	        	url: '/ReturnedInwardNote',
	            templateUrl: 'resources/templates/rin.html',
	            controller: 'RINController'
	        })
	        
	         .state('base.grc', {
	        	url: '/goodsReturnableChallan',
	            templateUrl: 'resources/templates/grc.html',
	            controller: 'GRCController'
	        })
	        
	         .state('base.goc', {
	        	url: '/goodsOutwardChallan',
	            templateUrl: 'resources/templates/goc.html',
	            controller: 'GOCController'
	        })
	        
	        .state('base.stockSummary', {
	        	url: '/stockSummary',
	            templateUrl: 'resources/templates/productStock.html',
	            controller: 'ReportsController'
	        })
	        
	        .state('base.stockRecord', {
	        	url: '/stockRecord',
	            templateUrl: 'resources/templates/stockRecord.html',
	            controller: 'ReportsController'
	        })
	        
	        .state('base.pendingGRCs', {
	        	url: '/pendingGRCs',
	            templateUrl: 'resources/templates/pendingGRCs.html',
	            controller: 'ReportsController'
	        })
	        
	        .state('base.user', {
	        	url: '/userHome',
	            templateUrl: 'resources/templates/user.html',
	            controller: 'UserController'
	        })
	        ;
	        
	});








