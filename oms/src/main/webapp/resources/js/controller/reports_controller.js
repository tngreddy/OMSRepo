'use strict';

omsApp.controller('ReportsController', ['$scope', 'ReportsService', 'CommonService',  '$state', function($scope, ReportsService, CommonService, $state) {
	$scope.productsStockList=[];
	$scope.stockRecordList=[];
	var prodStock = 'base.stockSummary';
	var stkRecord = 'base.stockRecord';
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	$scope.categories = [];
	$scope.products = [];
	$scope.catProdMap = {};
	$scope.showResults = false;
	$scope.searchObj= {};
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
	
	 $scope.fetchStockRecords = function(){
			$scope.showResults = false;
			
			if(typeof $scope.searchObj.fromDateValue != 'undefined' && typeof $scope.searchObj.toDateValue != 'undefined' ) {
				if($scope.searchObj.fromDateValue != null && $scope.searchObj.toDateValue != null) {
					$scope.searchObj.fromDate = $scope.searchObj.fromDateValue.toLocaleDateString();
					$scope.searchObj.toDate = $scope.searchObj.toDateValue.toLocaleDateString();
				}
			}
			
			ReportsService.fetchStockRecords($scope.searchObj)
			.then(
					function(data) {
						$scope.stockRecordList = data;
						$scope.showResults = true;
						var elmnt = angular.element('#resultsTable');
						CommonService.initializeDataTable(elmnt,'.addbuttoncontainer');
			      					
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
					$scope.categories = $scope.basicInfoMap["CATEGORIES"];
					$scope.catProdMap = $scope.basicInfoMap["CAT_PRODUCTS"];
				},
				function(errResponse){
					console.error('Error while fetching basicInfo');
				}
		);
	};

	$scope.populateProductList = function(categoryName){

		$scope.products = $scope.catProdMap[categoryName];
		$scope.searchObj.productName = null;
		
	};
	
	
	if($state.current.name == prodStock) {
		$scope.fetchBasicInfoToPopulate();
		$scope.fetchAllProductsStock();
		
	} else if ($state.current.name == stkRecord) {
		$scope.fetchBasicInfoToPopulate();
		//$scope.fetchAllStockRecords();
	}
	
	
	$scope.minFromDate = new Date($scope.searchObj.toDateValue);
	$scope.currDate  = new Date();

		
}]);































































