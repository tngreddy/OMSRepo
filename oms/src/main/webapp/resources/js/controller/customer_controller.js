'use strict';

omsApp.controller('CustomerController', ['$scope', 'CustomerService', function($scope, CustomerService) {
	$scope.customers=[];

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
					$scope.fetchAllCustomers();

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
					$scope.fetchAllCustomers();
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
					$scope.fetchAllCustomers();
				},
				function(errResponse){
					console.error('Error while deleting customer');
				}
		);
	};


	//Method to show delete overlay
	$scope.deleteCustomerModal = function(customer){
		$scope.showDeleteModal = true;
		$scope.customer = customer;
	};

}]);

