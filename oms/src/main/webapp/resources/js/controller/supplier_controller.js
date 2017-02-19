'use strict';

omsApp.controller('SupplierController', ['$scope', 'SupplierService','CommonService','$state', '$stateParams', function($scope, SupplierService ,CommonService,$state, $stateParams) {
	$scope.suppliers=[];
	$scope.supplier={};
	$scope.type = 'Supplier';
	$scope.tabElmnt = angular.element('#supplierTable');
	$scope.btnsContainer = '.addbuttoncontainer';
                        
			$scope.fetchAllSuppliers = function(){
				SupplierService.fetchAllSuppliers()
                  .then(
      					       function(data) {
      					    	   
      					    	 if(data.error!= null){
      								CommonService.showMsg('danger',data.error.message);
      							} else {
      								$scope.suppliers = data.object;
      								CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
      							}
      					    	 
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
      					if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showAddModal = false;
							CommonService.showMsg('success',CommonService.buildSuccessMsg($scope.type,$scope.supplier.name));
							$scope.fetchAllSuppliers();
						}
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
      					if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showEditModal = false;
							CommonService.showMsg('success',CommonService.buildUpdateMsg($scope.type,supplier.name));
							$scope.fetchAllSuppliers();
						}
      						
      				},
      				function(errResponse){
      					console.error('Error while updating supplier');
      				}
      		);
      	};

      	$scope.deleteSupplier = function(supplier){
      		SupplierService.deleteSupplier(supplier.supplierId)
      		.then(
      				function(data) {
      					if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showDeleteModal = false;
							CommonService.showMsg('success',CommonService.buildDeleteMsg($scope.type,supplier.name));
							$scope.fetchAllSuppliers();
						}
      					
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
           
         }]);

