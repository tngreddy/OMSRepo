'use strict';

omsApp.controller('SupplierController', ['$scope', 'SupplierService','$state', '$stateParams', function($scope, SupplierService ,$state, $stateParams) {
			$scope.suppliers=[];
			$scope.supplier={};
                        
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
      					$scope.showAddModal = false;
    					$scope.reloadState();	

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
      					$scope.showEditModal = false;
    					$scope.reloadState();	
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
      					$scope.showDeleteModal = false;
    					$scope.reloadState();	
      				},
      				function(errResponse){
      					console.error('Error while deleting supplier');
      				}
      		);
      	};
      	
      	
      	$scope.addSupplierModal = function(){
    		$scope.showAddModal = true;
    		$scope.supplier = {};
    		
    	};
    	
    	$scope.editSupplierModal = function(supplier) {
    		$scope.showEditModal = true;
    		$scope.supplier = supplier;
    	};
      	
      //Method to show delete overlay
    	$scope.deleteSupplierModal = function(supplier){
    		$scope.showDeleteModal = true;
    		$scope.supplier = supplier;
    	};
           
    	$scope.reloadState = function() {
    		setTimeout(function(){
    			$state.transitionTo($state.current, $stateParams, {
    				reload: true,
    				inherit: false,
    				notify: true
    			});

    		}, 100);
    	};
         }]);

