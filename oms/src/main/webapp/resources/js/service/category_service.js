'use strict';

omsApp.factory('CategoryService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCategories: function() {
					return $http.get('http://localhost:8080/oms/service/category/')
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
				return $http.post('http://localhost:8080/oms/service/category/', category)
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
		return $http.get('http://localhost:8080/oms/service/category/count')
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
