'use strict';

omsApp.factory('CustomerService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCustomers: function() {
					return $http.get('http://localhost:8080/oms/service/customer/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching customers');
										return $q.reject(errResponse);
									}
							);
			},
			

			getCustomerCount: function() {
			return $http.get('http://localhost:8080/oms/service/customer/count')
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while fetching category count');
								return $q.reject(errResponse);
							}
					);
		    
			}
		  
	};

}]);
