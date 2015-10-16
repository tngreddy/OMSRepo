
var omsApp = angular.module('omsApp', ['ui.router','datatables']);

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
	        });
	        
	});




