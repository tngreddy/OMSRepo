'use strict';

omsApp.factory('ProductService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {

		fetchAllProducts: function() {
			return $http.get(CommonService.getBaseUrl()+'/service/product/')
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
			return $http.post(CommonService.getBaseUrl()+'/service/product/', product)
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
		updateProduct: function(product) {
			return $http.put(CommonService.getBaseUrl()+'/service/product/', product)
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
			return $http.delete(CommonService.getBaseUrl()+'/service/product/'+productId)
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
			return $http.get(CommonService.getBaseUrl()+'/service/product/count')
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
