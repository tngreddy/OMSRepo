'use strict';

omsApp.factory('ReportsService', ['$http', '$q', function($http, $q){

	return {

		fetchAllProductsStock: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/reports/productStock')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching products stock');
						return $q.reject(errResponse);
					}
			);
		},
		fetchAllStockRecords: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/reports/stockRecord')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching stock records');
						return $q.reject(errResponse);
					}
			);
		}
		
		
	};

}]);
