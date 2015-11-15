'use strict';

omsApp.factory('SupplierService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllSuppliers: function() {
					return $http.get('http://ntiyyagura:8080/oms/service/supplier/')
							.then(
									function(response){
										return response.data.object;
									}, 
									function(errResponse){
										console.error('Error while fetching suppliers');
										return $q.reject(errResponse);
									}
							);
			},
			
			addSupplier: function(supplier) {
				return $http.post('http://ntiyyagura:8080/oms/service/supplier/', supplier)
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
				return $http.put('http://ntiyyagura:8080/oms/service/supplier/', supplier)
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
				return $http.delete('http://ntiyyagura:8080/oms/service/supplier/'+supplierId)
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
				return $http.get('http://ntiyyagura:8080/oms/service/supplier/count')
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
