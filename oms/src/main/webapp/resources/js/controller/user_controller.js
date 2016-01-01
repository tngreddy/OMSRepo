'use strict';

omsApp.controller('UserController', ['$scope', 'UserService', '$state', '$stateParams', function($scope, UserService, $state, $stateParams) {
	$scope.users=[];
	$scope.categories=[];
	$scope.user = {};
	

	$scope.fetchAllUsers = function(){

		UserService.fetchAllUsers()
		.then(
				function(data) {
					$scope.users = data;
				},
				function(errResponse){
					console.error('Error while fetching Currencies');
				}
		);
	};

	$scope.fetchAllUsers();

	$scope.addUser = function(){
		UserService.addUser($scope.user)
		.then(
				function(data) {
					$scope.showaAddModal = false;
					$scope.reloadState();	

				},
				function(errResponse){
					console.error('Error while adding User');
				}
		);
	};

	$scope.updateUser = function(user){
		UserService.updateUser(user)
		.then(
				function(data) {
					$scope.showEditModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while updating user');
				}
		);
	};

	$scope.deleteUser = function(userId){
		UserService.deleteUser(userId)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while deleting user');
				}
		);
	};



	$scope.addUserModal = function(){
		$scope.showAddModal = true;
		$scope.user = {};
	};

	$scope.editUserModal = function(user) {
		$scope.showEditModal = true;
		$scope.user = user;
	};

	//Method to show delete overlay
	$scope.deleteUserModal = function(user){
		$scope.showDeleteModal = true;
		$scope.user = user;
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
