'use strict';

omsApp.factory('GINService', ['$http', '$q', function($http, $q){

	return {

		fetchAllGINs: function() {
			return $http.get('http://localhost:8080/oms/service/gin/')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching GINs');
						return $q.reject(errResponse);
					}
			);
		},

		createGIN: function(gin) {
			return $http.post('http://localhost:8080/oms/service/gin/', gin)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding gin');
						return $q.reject(errResponse);
					}
			);

		},
		updateGIN: function(gin) {
			return $http.put('http://localhost:8080/oms/service/gin/', gin)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating gin');
						return $q.reject(errResponse);
					}
			);

		},

		deleteGIN: function(ginNo) {
			return $http.delete('http://localhost:8080/oms/service/gin/'+ginNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching gin count');
						return $q.reject(errResponse);
					});

		}
  
	};

}]);
