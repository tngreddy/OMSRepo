'use strict';

omsApp.factory('CommonService', ['$http', '$q', function($http, $q){

	return {

		fetchCounts: function() {
			return $http.get('http://localhost:8080/oms/service/common/count')
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching counts');
						return $q.reject(errResponse);
					}
			);
		},

		fetchInfoToPopulate: function() {
			return $http.get('http://localhost:8080/oms/service/common/basicInfo')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching basicInfo');
						return $q.reject(errResponse);
					}
			);
		}


	};

}]);
