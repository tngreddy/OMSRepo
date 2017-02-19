'use strict';

omsApp.controller('GRCController', ['$scope', 'GRCService', 'CommonService', '$state', '$stateParams', function($scope, GRCService, CommonService, $state, $stateParams) {
	$scope.grcs=[];
	$scope.grc={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	$scope.grcData = {};
	$scope.showGRCData = false;
	$scope.showGRCDetails = false;
	$scope.showPendingGRCs = false;
	$scope.pendingGRCs = [];
	var products = "PRODUCTS";
	
	 $scope.datepickerConfig = {
	            allowFuture: false,
	           // dateFormat: 'DD/MM/YYYY'
	            dateFormat: 'yyyy-MM-dd'
	        };

	
	$scope.addNewChoice = function() {
	    var newItemNo = $scope.grc.prodInfoList.length+1;
	    if($scope.grc.prodInfoList.length<10) {
	    	 $scope.grc.prodInfoList.push({});
	    } else {
	    	CommonService.showMsg('danger',"Only 10 Products are allowed. Please create a new GRC");
	    }
	   
	  };
	    
	  $scope.removeChoice = function() {
	    var lastItem = $scope.grc.prodInfoList.length-1;
	    if (lastItem>0) {
	    	$scope.grc.prodInfoList.splice(lastItem);
	    } else {
	    	CommonService.showMsg('danger',"Atleast one product is required to create a GOC");
	    }
	    
	  };
	
	
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
		if(typeof $scope.grc.docDateValue != 'undefined') {
			$scope.grc.docDate = $scope.grc.docDateValue.toLocaleDateString();
		}
		GRCService.createGRC($scope.grc)
		.then(
				function(data) {
					
					if(!(typeof data === 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							CommonService.showMsg('success',"Successfully created <strong>"+data.object+"</Strong>");
							$scope.showAddModal = false;
							$scope.fetchGRCData(data.object,true);	
						}
					}	
					
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
	
	$scope.fetchGRCData = function(grcNo,fromToInfo){

		if(!(typeof grcNo === 'undefined')){
		
		GRCService.fetchGRCData(grcNo,fromToInfo)
		.then(
				function(data) {
					if(data.error!= null){
						CommonService.showMsg('danger',data.error.message);
					} else {
						$scope.grcData = data.object;
						$scope.showPendingGRCs = false;	
						if(fromToInfo){
							$scope.showGRCData = false;
							$scope.showGRCDetails = true;
						} else {
							$scope.showGRCDetails = false;
							$scope.showGRCData = true;
						}
					}
				},
				function(errResponse){
					console.error('Error while fetching GRC data');
				}
		);
		
		};
	};
	
	$scope.fetchPendingGRCs = function(){

		GRCService.fetchPendingGRCs()
		.then(
				function(data) {
					$scope.pendingGRCs = data.object;
					$scope.showGRCData = false;
					$scope.showGRCDetails = false;
					$scope.showPendingGRCs = true;					
				},
				function(errResponse){
					console.error('Error while fetching GRC data');
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
	
	
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createGRCModal = function(){
	$scope.grc={};
	$scope.grc.prodInfoList = [{}];
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


}]);































































