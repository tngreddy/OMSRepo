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

		addProduct: function(product) {
			return $http.post('http://localhost:8080/oms/service/product/', product)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding product');
						return $q.reject(errResponse);
					}
			);

		},
		updateProduct: function(Product) {
			return $http.put('http://localhost:8080/oms/service/product/', product)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating product');
						return $q.reject(errResponse);
					}
			);

		},

		deleteProduct: function(productId) {
			return $http.delete('http://localhost:8080/oms/service/product/'+productId)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching product count');
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
						console.error('Error while fetching product count');
						return $q.reject(errResponse);
					}
			);

		}

	};

}]);
