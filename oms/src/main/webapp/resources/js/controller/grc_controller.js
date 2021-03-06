'use strict';

omsApp.controller('GRCController', ['$scope', 'GRCService', 'CommonService', '$state', '$stateParams', function($scope, GRCService, CommonService, $state, $stateParams) {
	$scope.grcs=[];
	$scope.grc={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	var products = "PRODUCTS";
	$scope.fetchAllGRCs = function(){

		GRCService.fetchAllGRCs()
		.then(
				function(data) {
					$scope.grcs = data;
				},
				function(errResponse){
					console.error('Error while fetching GRCs');
				}
		);
	};



	$scope.createGRC = function(){
		GRCService.createGRC($scope.grc)
		.then(
				function(data) {
					$scope.showAddModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while create GRC');
				}
		);
	};

	$scope.updateGRC = function(grc){
		GRCService.updateGRC(grc)
		.then(
				function(data) {
					$scope.showEditModal = false;
					$scope.reloadState();		
				},
				function(errResponse){
					console.error('Error while updating GRC');
				}
		);
	};

	$scope.deleteGRC = function(grcNo){
		GRCService.deleteGRC(grcNo)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();			
				},
				function(errResponse){
					console.error('Error while deleting GRC');
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
	
	$scope.fetchAllGRCs();
	
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createGRCModal = function(){
	$scope.grc={};
	$scope.showAddModal = true;

};

$scope.editGRCModal = function (grc) {
	$scope.showEditModal = true;
	$scope.grc = grc;
};

$scope.deleteGRCModal = function(grc){
	$scope.showDeleteModal = true;
	$scope.grc = grc;
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































































