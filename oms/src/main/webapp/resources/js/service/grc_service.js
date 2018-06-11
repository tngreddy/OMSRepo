'use strict';

omsApp.factory('GRCService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {

		fetchAllGRCs: function() {
			return $http.get(CommonService.baseUrl+'/service/grc/')
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
			return $http.post(CommonService.getBaseUrl()+'/service/grc/', grc)
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
			return $http.put(CommonService.getBaseUrl()+'/service/grc/', grc)
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
			return $http.delete(CommonService.getBaseUrl()+'/service/grc/'+grcNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while deleting grc');
						return $q.reject(errResponse);
					});

		},
		
		fetchGRCNos: function(fromName, type) {
			return $http.get(CommonService.getBaseUrl()+'/service/grc/grcNos?toName='+fromName+'&type='+type)
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
			return $http.get(CommonService.getBaseUrl()+'/service/grc/details/'+grcNo+'/'+fromToInfo)
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
			return $http.get(CommonService.getBaseUrl()+'/service/grc/pending')
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching pending GRCs ');
						return $q.reject(errResponse);
					}
			);
		},
  
	};

}]);
