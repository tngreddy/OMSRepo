'use strict';

omsApp.factory('GRCService', ['$http', '$q', function($http, $q){

	return {

		fetchAllGRCs: function() {
			return $http.get('http://localhost:8080/oms/service/grc/')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching GRCs');
						return $q.reject(errResponse);
					}
			);
		},

		createGRC: function(grc) {
			return $http.post('http://localhost:8080/oms/service/grc/', grc)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding grc');
						return $q.reject(errResponse);
					}
			);

		},
		updateGRC: function(grc) {
			return $http.put('http://localhost:8080/oms/service/grc/', grc)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating grc');
						return $q.reject(errResponse);
					}
			);

		},

		deleteGRC: function(grcNo) {
			return $http.delete('http://localhost:8080/oms/service/grc/'+grcNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while deleting grc');
						return $q.reject(errResponse);
					});

		}
  
	};

}]);
