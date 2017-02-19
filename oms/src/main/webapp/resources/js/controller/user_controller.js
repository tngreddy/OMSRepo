'use strict';

omsApp.controller('UserController', ['$scope', 'UserService','CommonService', '$state', '$stateParams', function($scope, UserService,CommonService, $state, $stateParams) {
	$scope.users=[];
	$scope.categories=[];
	$scope.user = {};
	$scope.type = 'User';
	$scope.tabElmnt = angular.element('#userTable');
	$scope.btnsContainer = '.addbuttoncontainer';
	

	$scope.fetchAllUsers = function(){

		UserService.fetchAllUsers()
		.then(
				function(data) {
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.users = data;
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
						}
					
				    }
				
					
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
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showAddModal = false;
							CommonService.showMsg('success',CommonService.buildSuccessMsg($scope.type,$scope.user.userName));
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
							
						}
					
				    }
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
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showEditModal = false;
							CommonService.showMsg('success',CommonService.buildUpdateMsg($scope.type,user.userName));
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
							
						}
				    }
					
				},
				function(errResponse){
					console.error('Error while updating user');
				}
		);
	};

	$scope.deleteUser = function(user){
		UserService.deleteUser(user.userId)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showDeleteModal = false;
							CommonService.showMsg('success',CommonService.buildDeleteMsg($scope.type,user.userName));
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
						}
				    }
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

	

}]);
