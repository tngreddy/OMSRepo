'use strict';

omsApp.factory('CategoryService', ['$http', '$q' ,'CommonService', function($http, $qm, CommonService){

	return {

		fetchAllCategories: function() {
			return $http.get(CommonService.getBaseUrl()+'/service/category/')
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching categories');
						return $q.reject(errResponse);
					}
			);
		},

		addCategory: function(category) {
			return $http.post(CommonService.getBaseUrl()+'/service/category/', category)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding category');
						return $q.reject(errResponse);
					}
			);

		},
		updateCategory: function(category) {
			return $http.put(CommonService.getBaseUrl()+'/service/category/', category)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating category');
						return $q.reject(errResponse);
					}
			);

		},

		deleteCategory: function(categoryId) {
			return $http.delete(CommonService.getBaseUrl()+'/service/category/'+categoryId)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching category count');
						return $q.reject(errResponse);
					}
			);

		},
		getCategoryCount: function() {
			return $http.get(CommonService.getBaseUrl()+'/service/category/count')
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
