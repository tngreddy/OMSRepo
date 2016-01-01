'use strict';

omsApp.factory('GRCService', ['$http', '$q', function($http, $q){

	return {

		fetchAllGRCs: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/grc/')
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
			return $http.post('http://ntiyyagura:8080/oms/service/grc/', grc)
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
			return $http.put('http://ntiyyagura:8080/oms/service/grc/', grc)
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
			return $http.delete('http://ntiyyagura:8080/oms/service/grc/'+grcNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while deleting grc');
						return $q.reject(errResponse);
					});

		},
		
		fetchGRCNos: function(fromName) {
			return $http.get('http://ntiyyagura:8080/oms/service/grc/grcNos/'+fromName)
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching GRC nos');
						return $q.reject(errResponse);
					}
			);
		},
		
		fetchGRCData: function(grcNo,fromToInfo) {
			return $http.get('http://ntiyyagura:8080/oms/service/grc/details/'+grcNo+'/'+fromToInfo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching grcdata');
						return $q.reject(errResponse);
					}
			);
		},
		
		fetchPendingGRCs: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/grc/pending')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching pending GRCs ');
						return $q.reject(errResponse);
					}
			);
		},
  
	};

}]);
