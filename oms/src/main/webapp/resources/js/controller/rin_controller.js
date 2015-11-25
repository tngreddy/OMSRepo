'use strict';

omsApp.controller('RINController', ['$scope', 'RINService', 'CommonService', '$state', '$stateParams', function($scope, RINService, CommonService, $state, $stateParams) {
	$scope.rins=[];
	$scope.rin={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	var products = "PRODUCTS";
	
	 $scope.datepickerConfig = {
	            allowFuture: false,
	           // dateFormat: 'DD/MM/YYYY'
	            dateFormat: 'yyyy-MM-dd'
	        };

	
	 $scope.addNewChoice = function() {
		    var newItemNo = $scope.rin.prodInfoList.length+1;
		    if($scope.rin.prodInfoList.length<10) {
		    	 $scope.rin.prodInfoList.push({});
		    } else {
		    	window.alert("Only 10 Products are allowed. Please create a new RIN");
		    }
		   
		  };
		    
		  $scope.removeChoice = function() {
		    var lastItem = $scope.rin.prodInfoList.length-1;
		    if (lastItem>0) {
		    	$scope.rin.prodInfoList.splice(lastItem);
		    } else {
		    	window.alert("Atleast one product is required to create a RIN");
		    }
		    
		  };
		
	
	
	
	$scope.fetchAllRINs = function(){

		RINService.fetchAllRINs()
		.then(
				function(data) {
					$scope.rins = data;
				},
				function(errResponse){
					console.error('Error while fetching RINs');
				}
		);
	};



	$scope.createRIN = function(){
		RINService.createRIN($scope.rin)
		.then(
				function(data) {
					$scope.showAddModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while create RIN');
				}
		);
	};

	$scope.updateRIN = function(rin){
		RINService.updateRIN(rin)
		.then(
				function(data) {
					$scope.showEditModal = false;
					$scope.reloadState();		
				},
				function(errResponse){
					console.error('Error while updating RIN');
				}
		);
	};

	$scope.deleteRIN = function(rinNo){
		RINService.deleteRIN(rinNo)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();			
				},
				function(errResponse){
					console.error('Error while deleting RIN');
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
	
	$scope.fetchAllRINs();
	
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createRINModal = function(){
	$scope.rin={};
	$scope.rin.prodInfoList = [{}];
	$scope.showAddModal = true;

};

$scope.editRINModal = function (rin) {
	$scope.showEditModal = true;
	$scope.rin = rin;
};

$scope.deleteRINModal = function(rin){
	$scope.showDeleteModal = true;
	$scope.rin = rin;
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































































