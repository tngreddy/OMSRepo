'use strict';

App.factory('CategoryService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCategories: function() {
					return $http.get('http://localhost:8080/oms/category/')
							.then(
									function(response){
										return response;
									}, 
									function(errResponse){
										console.error('Error while fetching categories');
										return $q.reject(errResponse);
									}
							);
			}	    
		  
	};

}]);
