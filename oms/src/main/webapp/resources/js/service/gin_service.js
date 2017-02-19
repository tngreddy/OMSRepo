'use strict';

omsApp.factory('GINService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {

		fetchAllGINs: function() {
			return $http.get(CommonService.getBaseUrl()+'/service/gin/')
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
			return $http.post(CommonService.getBaseUrl()+'/service/gin/', gin)
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
			return $http.put(CommonService.getBaseUrl()+'/service/gin/', gin)
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
			return $http.delete(CommonService.getBaseUrl()+'/service/gin/'+ginNo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching gin count');
						return $q.reject(errResponse);
					});

		},
		
		fetchGINData: function(ginNo,fromToInfo) {
			return $http.get(CommonService.getBaseUrl()+'/service/gin/details/'+ginNo+'/'+fromToInfo)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching gindata');
						return $q.reject(errResponse);
					}
			);
		}
		  
	};

}]);
