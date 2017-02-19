'use strict';

omsApp.factory('CustomerService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {
		
			fetchAllCustomers: function() {
					return $http.get(CommonService.getBaseUrl()+'/service/customer/')
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
			
			addCustomer: function(customer) {
				return $http.post(CommonService.getBaseUrl()+'/service/customer/', customer)
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
				return $http.put(CommonService.getBaseUrl()+'/service/customer/', customer)
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
				return $http.delete(CommonService.getBaseUrl()+'/service/customer/'+customerId)
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
			return $http.get(CommonService.getBaseUrl()+'/service/customer/count')
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
