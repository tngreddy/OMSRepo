'use strict';

omsApp.factory('SupplierService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {
		
			fetchAllSuppliers: function() {
					return $http.get(CommonService.getBaseUrl()+'/service/supplier/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching suppliers');
										return $q.reject(errResponse);
									}
							);
			},
			
			addSupplier: function(supplier) {
				return $http.post(CommonService.getBaseUrl()+'/service/supplier/', supplier)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while adding supplier');
							return $q.reject(errResponse);
						}
				);

			},
			updateSupplier: function(supplier) {
				return $http.put(CommonService.getBaseUrl()+'/service/supplier/', supplier)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while updating supplier');
							return $q.reject(errResponse);
						}
				);

			},

			deleteSupplier: function(supplierId) {
				return $http.delete(CommonService.getBaseUrl()+'/service/supplier/'+supplierId)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetching supplier count');
							return $q.reject(errResponse);
						}
				);

			},
			
			getSupplierCount: function() {
				return $http.get(CommonService.getBaseUrl()+'/service/supplier/count')
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while fetching supplier count');
									return $q.reject(errResponse);
								}
						);
			    
				}
		  
	};
	
	

}]);
