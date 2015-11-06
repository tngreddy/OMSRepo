'use strict';

omsApp.controller('ReportsController', ['$scope', 'ReportsService', '$state', function($scope, ReportsService, $state) {
	$scope.productsStockList=[];
	$scope.stockRecordList=[];
	var prodStock = 'productStock';
	var stkRecord = 'stockRecord';
	
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

	if($state.current.name == prodStock) {
		$scope.fetchAllProductsStock();
	} else if ($state.current.name == stkRecord) {
		$scope.fetchAllStockRecords();
	}
		
}]);































































