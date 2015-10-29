'use strict';

omsApp.controller('CustomerController', ['$scope', 'CustomerService', '$state', '$stateParams', function($scope, CustomerService, $state, $stateParams) {
	$scope.customers=[];
	$scope.customer={};

	$scope.fetchAllCustomers = function(){
		CustomerService.fetchAllCustomers()
		.then(
				function(data) {
					$scope.customers = data;
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
					$scope.showAddModal = false;
					$scope.reloadState();	

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
					$scope.showEditModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while updating customer');
				}
		);
	};

	$scope.deleteCustomer = function(customerId){
		CustomerService.deleteCustomer(customerId)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();
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

