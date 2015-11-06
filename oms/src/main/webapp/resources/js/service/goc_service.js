'use strict';

omsApp.factory('GOCService', ['$http', '$q', function($http, $q){

	return {

		fetchAllGOCs: function() {
			return $http.get('http://localhost:8080/oms/service/goc/')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching GOCs');
						return $q.reject(errResponse);
					}
			);
		},

		createGOC: function(goc) {
			return $http.post('http://localhost:8080/oms/service/goc/', goc)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding goc');
						return $q.reject(errResponse);
					}
			);

		},
		updateGOC: function(goc) {
			return $http.put('http://localhost:8080/oms/service/goc/', goc)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating goc');
						return $q.reject(errResponse);
					}
			);

		},

		deleteGOC: function(gocNo) {
			return $http.delete('http://localhost:8080/oms/service/goc/'+gocNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching goc count');
						return $q.reject(errResponse);
					});

		}
  
	};

}]);
