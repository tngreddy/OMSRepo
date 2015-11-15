'use strict';

omsApp.factory('CategoryService', ['$http', '$q', function($http, $q){

	return {

		fetchAllCategories: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/category/')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching categories');
						return $q.reject(errResponse);
					}
			);
		},

		addCategory: function(category) {
			return $http.post('http://ntiyyagura:8080/oms/service/category/', category)
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
			return $http.put('http://ntiyyagura:8080/oms/service/category/', category)
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
			return $http.delete('http://ntiyyagura:8080/oms/service/category/'+categoryId)
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
			return $http.get('http://ntiyyagura:8080/oms/service/category/count')
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
