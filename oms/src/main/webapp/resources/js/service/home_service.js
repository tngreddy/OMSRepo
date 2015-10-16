'use strict';

omsApp.factory('HomeService', ['$http', '$q', function($http, $q){

	return {
		
			fetchCounts: function() {
					return $http.get('http://localhost:8080/oms/service/home/count')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching counts');
										return $q.reject(errResponse);
									}
							);
			}

	 
	};

}]);
