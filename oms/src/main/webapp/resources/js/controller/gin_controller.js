'use strict';

omsApp.controller('GINController', ['$scope', 'GINService', 'CommonService', '$state', '$stateParams','Flash', function($scope, GINService, CommonService, $state, $stateParams,Flash) {
	$scope.gins=[];
	$scope.gin={};
	$scope.basicInfoMap = {};
	$scope.showGINData =false;
	$scope.showGINDetails = false;
	$scope.productsInfo={};
	$scope.loading = false ;
	$scope.showSuccess = false;	
	$scope.showError = false;
	$scope.modalShowSuccess = false;	
	$scope.modalShowError = false;	
	
	var products = "PRODUCTS";
	$scope.ginData = {}
	//$scope.gin.prodInfoList = [{"product":{"productId":0},"unitBasicRate":0,"goodIn":0,"defIn":0}];
	//$scope.gin.prodInfoList = [{}];
	
	  $scope.datepickerConfig = {
            allowFuture: false,
            dateFormat: 'DD/MM/YYYY'
        };

	  
	  $scope.addNewChoice = function() {
	    var newItemNo = $scope.gin.prodInfoList.length+1;
	    if($scope.gin.prodInfoList.length<10) {
	    	 $scope.gin.prodInfoList.push({});
	    } else {
	    	CommonService.showMsg('danger',"Only 10 Products are allowed. Please create a new GIN");
	    }
	   
	  };
	    
	  $scope.removeChoice = function() {
	    var lastItem = $scope.gin.prodInfoList.length-1;
	    if (lastItem>0) {
	    	$scope.gin.prodInfoList.splice(lastItem);
	    } else {
	    	CommonService.showMsg('danger',"Atleast one product is required to create a GIN");
	    }
	    
	  };
	
	
	
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
		if(typeof $scope.gin.docDateValue != 'undefined') {
			$scope.gin.docDate = $scope.gin.docDateValue.toLocaleDateString();
		}
	    GINService.createGIN($scope.gin)
		.then(
				function(data) {
					if(!(typeof data === 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
							//$scope.errorMessage = data.error.message;
							//$scope.showError = true;
						} else {
							CommonService.showMsg('success',CommonService.buildTransactionSuccessMsg(data.object));
							//$scope.successMessage = "Successfully created "+data.object;
							//$scope.modalShowSuccess = false;
							//$scope.modalShowSuccess = true;
							
						      //setTimeout(function(){
							
						    	//  $scope.modalShowSuccess = false;	
								  $scope.showAddModal = false;
								  $scope.fetchGINData(data.object,true);
						      //  }, 2000);
							
							
						}
						
					}	
				    //$scope.reloadState();	
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
					if(!(typeof data === 'undefined')){
						
						if(data.error!= null){
							$scope.errorMessage = error.message;
						} else {
							$scope.showEditModal = false;
							$scope.reloadState();	
						}
					}
						
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
	
	
	$scope.fetchGINData = function(ginNo,fromToInfo){

		if(!(typeof ginNo === 'undefined')){
		
		GINService.fetchGINData(ginNo,fromToInfo)
		.then(
				function(data) {
					
					if(data.error!= null){
						CommonService.showMsg('danger',data.error.message);
					} else {
						$scope.ginData = data.object;
						if(fromToInfo){
							$scope.showGINData = false;
							$scope.showGINDetails = true;
						} else {
							$scope.showGINDetails = false;
							$scope.showGINData = true;
						}
						
					}
					
					
				},
				function(errResponse){
					console.error('Error while fetching GIN data');
				}
		);
		
		};
	};


	$scope.populateFromData = function(from){
		
		$scope.dataList = $scope.basicInfoMap[from];
		
	};
	
	$scope.fetchGINInfo = function(ginNo,option){

		if(option == "search") {
			$scope.fetchGINData(ginNo, false);
		} else if(option == "printFormat") {
			$scope.fetchGINData(ginNo, true);
		}
		
	};
	
		
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createGINModal = function(){
	$scope.gin={};
	$scope.gin.prodInfoList = [{}];
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



}]);































































