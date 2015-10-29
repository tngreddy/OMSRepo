'use strict';

omsApp.factory('CustomerService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCustomers: function() {
					return $http.get('http://localhost:8080/oms/service/customer/')
							.then(
									function(response){
										return response.data.object;
									}, 
									function(errResponse){
										console.error('Error while fetching customers');
										return $q.reject(errResponse);
									}
							);
			},
			
			addCustomer: function(customer) {
				return $http.post('http://localhost:8080/oms/service/customer/', customer)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while adding customer');
							return $q.reject(errResponse);
						}
				);

			},
			updateCustomer: function(customer) {
				return $http.put('http://localhost:8080/oms/service/customer/', customer)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while updating customer');
							return $q.reject(errResponse);
						}
				);

			},

			deleteCustomer: function(customerId) {
				return $http.delete('http://localhost:8080/oms/service/customer/'+customerId)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetching customer count');
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
								console.error('Error while fetching customer count');
								return $q.reject(errResponse);
							}
					);
		    
			}
		  
	};

}]);
