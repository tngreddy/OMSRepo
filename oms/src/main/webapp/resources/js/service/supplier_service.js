'use strict';

omsApp.factory('SupplierService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllSuppliers: function() {
					return $http.get('http://localhost:8080/oms/service/supplier/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching suppliers');
										return $q.reject(errResponse);
									}
							);
			}	    
		  
	};

}]);
