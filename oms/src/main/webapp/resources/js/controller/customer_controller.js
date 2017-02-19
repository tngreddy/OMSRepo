'use strict';

omsApp.controller('CustomerController', ['$scope', 'CustomerService','CommonService', '$state', '$stateParams', function($scope, CustomerService,CommonService, $state, $stateParams) {
	$scope.customers=[];
	$scope.customer={};
	$scope.type = 'Customer';
	$scope.tabElmnt = angular.element('#customerTable');
	$scope.btnsContainer = '.addbuttoncontainer';

	$scope.fetchAllCustomers = function(){
		CustomerService.fetchAllCustomers()
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.customers = data.object;
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
							
						}
					
				    }
					
				},
				function(errResponse){
					console.error('Error while fetching Customers');
				}
		);
	};

	$scope.fetchAllCustomers();


	$scope.addCustomer = function(){
		CustomerService.addCustomer($scope.customer)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showAddModal = false;
							CommonService.showMsg('success',CommonService.buildSuccessMsg($scope.type,$scope.customer.name));
							$scope.fetchAllCustomers();
						}
					
				    }
				},
				function(errResponse){
					console.error('Error while adding customer');
				}
		);
	};

	$scope.updateCustomer = function(customer){
		CustomerService.updateCustomer(customer)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showEditModal = false;
							CommonService.showMsg('success',CommonService.buildUpdateMsg($scope.type,customer.name));
							$scope.fetchAllCustomers();
						}
					
				    }
					
				},
				function(errResponse){
					console.error('Error while updating customer');
				}
		);
	};

	$scope.deleteCustomer = function(customer){
		CustomerService.deleteCustomer(customer.customerId)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showDeleteModal = false;
							CommonService.showMsg('success',CommonService.buildDeleteMsg($scope.type,customer.name));
							$scope.fetchAllCustomers();
						}
					
				    }
					
				},
				function(errResponse){
					console.error('Error while deleting customer');
				}
		);
	};


	$scope.addCustomerModal = function(){
		$scope.showAddModal = true;
		$scope.customer = {};
		
	};
	
	$scope.editCustomerModal = function(customer) {
		$scope.showEditModal = true;
		$scope.customer = customer;
	};
	
	//Method to show delete overlay
	$scope.deleteCustomerModal = function(customer){
		$scope.showDeleteModal = true;
		$scope.customer = customer;
	};

}]);

