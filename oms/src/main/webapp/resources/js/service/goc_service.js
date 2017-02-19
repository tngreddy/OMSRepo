'use strict';

omsApp.factory('GOCService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {

		fetchAllGOCs: function() {
			return $http.get(CommonService.getBaseUrl()+'/service/goc/')
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
			return $http.post(CommonService.getBaseUrl()+'/service/goc/', goc)
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
			return $http.put(CommonService.getBaseUrl()+'/service/goc/', goc)
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
			return $http.delete(CommonService.getBaseUrl()+'/service/goc/'+gocNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching goc count');
						return $q.reject(errResponse);
					});

		},
		
		fetchGOCData: function(gocNo,fromToInfo) {
			return $http.get(CommonService.getBaseUrl()+'/service/goc/details/'+gocNo+'/'+fromToInfo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching gocdata');
						return $q.reject(errResponse);
					}
			);
		}
  
	};

}]);
