'use strict';

omsApp.controller('GOCController', ['$scope', 'GOCService', 'CommonService', '$state', '$stateParams', function($scope, GOCService, CommonService, $state, $stateParams) {
	$scope.gocs=[];
	$scope.goc={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	var products = "PRODUCTS";
	$scope.fetchAllGOCs = function(){

		GOCService.fetchAllGOCs()
		.then(
				function(data) {
					$scope.gocs = data;
				},
				function(errResponse){
					console.error('Error while fetching GOCs');
				}
		);
	};



	$scope.createGOC = function(){
		GOCService.createGOC($scope.goc)
		.then(
				function(data) {
					$scope.showAddModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while create GOC');
				}
		);
	};

	$scope.updateGOC = function(goc){
		GOCService.updateGOC(goc)
		.then(
				function(data) {
					$scope.showEditModal = false;
					$scope.reloadState();		
				},
				function(errResponse){
					console.error('Error while updating GOC');
				}
		);
	};

	$scope.deleteGOC = function(gocNo){
		GOCService.deleteGOC(gocNo)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();			
				},
				function(errResponse){
					console.error('Error while deleting GOC');
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
	
	$scope.fetchAllGOCs();
	
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createGOCModal = function(){
	$scope.goc={};
	$scope.showAddModal = true;

};

$scope.editGOCModal = function (goc) {
	$scope.showEditModal = true;
	$scope.goc = goc;
};

$scope.deleteGOCModal = function(goc){
	$scope.showDeleteModal = true;
	$scope.goc = goc;
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































































