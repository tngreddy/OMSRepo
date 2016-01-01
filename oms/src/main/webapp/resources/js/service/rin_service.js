'use strict';

omsApp.factory('RINService', ['$http', '$q', function($http, $q){

	return {

		fetchAllRINs: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/rin/')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching RINs');
						return $q.reject(errResponse);
					}
			);
		},

		createRIN: function(rin) {
			return $http.post('http://ntiyyagura:8080/oms/service/rin/', rin)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding rin');
						return $q.reject(errResponse);
					}
			);

		},
		updateRIN: function(rin) {
			return $http.put('http://ntiyyagura:8080/oms/service/rin/', rin)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating rin');
						return $q.reject(errResponse);
					}
			);

		},

		deleteRIN: function(rinNo) {
			return $http.delete('http://ntiyyagura:8080/oms/service/rin/'+rinNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while deleting RIN');
						return $q.reject(errResponse);
					});

		},
		
		fetchRINData: function(rinNo,fromToInfo) {
			return $http.get('http://ntiyyagura:8080/oms/service/rin/details/'+rinNo+'/'+fromToInfo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching rindata');
						return $q.reject(errResponse);
					}
			);
		}

  
	};

}]);
