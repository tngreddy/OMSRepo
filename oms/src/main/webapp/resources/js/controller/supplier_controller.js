'use strict';

omsApp.controller('SupplierController', ['$scope', 'SupplierService', function($scope, SupplierService) {
			$scope.suppliers=[];
                        
			$scope.fetchAllSuppliers = function(){
				SupplierService.fetchAllSuppliers()
                  .then(
      					       function(data) {
      					    	 $scope.suppliers = data;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Suppliers');
            					}
      			       );
          };
          
          $scope.fetchAllSuppliers();
          

      	$scope.addSupplier = function(){
      		SupplierService.addSupplier($scope.supplier)
      		.then(
      				function(data) {
      					$scope.fetchAllSuppliers();

      				},
      				function(errResponse){
      					console.error('Error while adding supplier');
      				}
      		);
      	};

      	$scope.updateSupplier = function(supplier){
      		SupplierService.updateSupplier(supplier)
      		.then(
      				function(data) {
      					$scope.fetchAllSuppliers();
      				},
      				function(errResponse){
      					console.error('Error while updating supplier');
      				}
      		);
      	};

      	$scope.deleteSupplier = function(supplierId){
      		SupplierService.deleteSupplier(supplierId)
      		.then(
      				function(data) {
      					$scope.fetchAllSuppliers();
      				},
      				function(errResponse){
      					console.error('Error while deleting supplier');
      				}
      		);
      	};
      	
      	
      //Method to show delete overlay
    	$scope.deleteSupplierModal = function(supplier){
    		$scope.showDeleteModal = true;
    		$scope.supplier = supplier;
    	};
           
         }]);

