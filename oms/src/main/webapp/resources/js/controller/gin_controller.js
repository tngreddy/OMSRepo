'use strict';

omsApp.controller('GINController', ['$scope', 'GINService', 'CommonService', '$state', '$stateParams', function($scope, GINService, CommonService, $state, $stateParams) {
	$scope.gins=[];
	$scope.gin={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	var products = "PRODUCTS";
	$scope.fetchAllGINs = function(){

		GINService.fetchAllGINs()
		.then(
				function(data) {
					$scope.gins = data;
				},
				function(errResponse){
					console.error('Error while fetching GINs');
				}
		);
	};



	$scope.createGIN = function(){
		GINService.createGIN($scope.gin)
		.then(
				function(data) {
					$scope.showAddModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while create GIN');
				}
		);
	};

	$scope.updateGIN = function(gin){
		GINService.updateGIN(gin)
		.then(
				function(data) {
					$scope.showEditModal = false;
					$scope.reloadState();		
				},
				function(errResponse){
					console.error('Error while updating GIN');
				}
		);
	};

	$scope.deleteGIN = function(ginNo){
		GINService.deleteGIN(ginNo)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();			
				},
				function(errResponse){
					console.error('Error while deleting GIN');
				}
		);
	};
	
	$scope.fetchBasicInfoToPopulate = function(){

		CommonService.fetchInfoToPopulate()
		.then(
				function(data) {
					$scope.basicInfoMap = data;
					$scope.productsInfo = $scope.basicInfoMap[products];
				},
				function(errResponse){
					console.error('Error while fetching basicInfo');
				}
		);
	};


	$scope.populateFromData = function(from){

		$scope.dataList = $scope.basicInfoMap[from];
		
	};
	
	$scope.fetchAllGINs();
	
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createGINModal = function(){
	$scope.gin={};
	$scope.showAddModal = true;

};

$scope.editGINModal = function (gin) {
	$scope.showEditModal = true;
	$scope.gin = gin;
};

$scope.deleteGINModal = function(gin){
	$scope.showDeleteModal = true;
	$scope.gin = gin;
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































































