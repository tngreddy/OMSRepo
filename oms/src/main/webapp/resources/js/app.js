
alert("gowtham");

var app = angular.module('omsApp', ['ngRoute']);

//configure our routes
app.config(function($routeProvider) {
    $routeProvider

        .when('/oms/categoryHome', {
            templateUrl : 'views/category.html',
            controller  : 'controller/categoryController'
        })

        .when('/oms/productHome', {
            templateUrl : 'views/product.html',
            controller  : 'controller/productController'
        })

        .when('/customerHome', {
            templateUrl : 'views/customer.html',
            controller  : 'controller/customerController'
        })
        
        .when('/supplierHome', {
            templateUrl : 'views/supplier.html',
            controller  : 'controller/supplierController'
         
        })
        .otherwise({
				redirectTo: '/'
			
        });
});



/*app.controller('mainController', function($scope, $http) {
	
	
	
});*/


/*
//configure our routes
scotchApp.config(function($routeProvider) {
    $routeProvider

        .when('/category', {
            templateUrl : 'views/category.html',
            controller  : 'controller/categoryController'
        })

        .when('/product', {
            templateUrl : 'views/product.html',
            controller  : 'controller/productController'
        })

        .when('/customer', {
            templateUrl : 'views/customer.html',
            controller  : 'controller/customerController'
        })
        
        .when('/supplier', {
            templateUrl : 'views/supplier.html',
            controller  : 'controller/supplierController'
         
        })
        .otherwise({
				redirectTo: '/'
			
        });
});

*/