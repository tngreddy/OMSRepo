'use strict';

omsApp.controller('ReportsController', ['$scope', 'ReportsService', 'CommonService',  '$state', function($scope, ReportsService, CommonService, $state) {
	$scope.productsStockList=[];
	$scope.stockRecordList=[];
	var prodStock = 'stockSummary';
	var stkRecord = 'stockRecord';
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	var products = "PRODUCTS";
	
	$scope.fetchAllProductsStock = function(){
	
		ReportsService.fetchAllProductsStock()
		.then(
				function(data) {
					$scope.productsStockList = data;
				},
				function(errResponse){
					console.error('Error while fetching products stock');
				}
		);
	};
	
   $scope.fetchAllStockRecords = function(){
		
		ReportsService.fetchAllStockRecords()
		.then(
				function(data) {
					$scope.stockRecordList = data;
				},
				function(errResponse){
					console.error('Error while fetching stock records');
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

	if($state.current.name == prodStock) {
		$scope.fetchBasicInfoToPopulate();
		$scope.fetchAllProductsStock();
		
	} else if ($state.current.name == stkRecord) {
		$scope.fetchBasicInfoToPopulate();
		$scope.fetchAllStockRecords();
	}
	

		
}]);































































