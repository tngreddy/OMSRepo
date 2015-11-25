
var omsApp = angular.module('omsApp', ['ui.router','datatables', 'ui.bootstrap','ngAnimate','anim-in-out','angularValidator','ngFlatDatepicker']);

omsApp.config(function($stateProvider, $urlRouterProvider) {
	    
	    $urlRouterProvider.otherwise('/oms/');
	    
	    $stateProvider
	    
	         .state('category', {
	        	url: '/categoryHome',
	            templateUrl: 'resources/templates/category.html',
	            controller: 'CategoryController'
	        })
	        
	        .state('product', {
	        	url: '/productHome',
	            templateUrl: 'resources/templates/product.html',
	            controller: 'ProductController'
	        })
	        
	        .state('customer', {
	        	url: '/customerHome',
	            templateUrl: 'resources/templates/customer.html',
	            controller: 'CustomerController'
	        })
	        
	        .state('supplier', {
	        	url: '/supplierHome',
	            templateUrl: 'resources/templates/supplier.html',
	            controller: 'SupplierController'
	        })
	         .state('gin', {
	        	url: '/goodsInwardNote',
	            templateUrl: 'resources/templates/gin.html',
	            controller: 'GINController'
	        })
	        
	         .state('rin', {
	        	url: '/ReturnedInwardNote',
	            templateUrl: 'resources/templates/rin.html',
	            controller: 'RINController'
	        })
	        
	         .state('grc', {
	        	url: '/goodsReturnableChallan',
	            templateUrl: 'resources/templates/grc.html',
	            controller: 'GRCController'
	        })
	        
	         .state('goc', {
	        	url: '/goodsOutwardChallan',
	            templateUrl: 'resources/templates/goc.html',
	            controller: 'GOCController'
	        })
	        
	        .state('stockSummary', {
	        	url: '/stockSummary',
	            templateUrl: 'resources/templates/productStock.html',
	            controller: 'ReportsController'
	        })
	        
	        .state('stockRecord', {
	        	url: '/stockRecord',
	            templateUrl: 'resources/templates/stockRecord.html',
	            controller: 'ReportsController'
	        })
	        ;
	        
	});








