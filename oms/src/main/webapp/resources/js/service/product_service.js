'use strict';

omsApp.factory('ProductService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllProducts: function() {
					return $http.get('http://localhost:8080/oms/service/product/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching products');
										return $q.reject(errResponse);
									}
							);
			},
			
			
			getProductCount: function() {
			return $http.get('http://localhost:8080/oms/service/product/count')
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while fetching category count');
								return $q.reject(errResponse);
							}
					);
		    
			}
		  
	};

}]);
