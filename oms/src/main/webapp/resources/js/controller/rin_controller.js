'use strict';

omsApp.controller('RINController', ['$scope', 'RINService', 'CommonService', 'GRCService', '$state', '$stateParams', function($scope, RINService, CommonService, GRCService, $state, $stateParams) {
	$scope.rins=[];
	$scope.rin={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	$scope.prodsList={};
	$scope.grcs=[];
	$scope.grcData = {};
	$scope.rinData = {};
	$scope.showRINData = false;
	$scope.showRINDetails = false;
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
		    	CommonService.showMsg('danger',"Only 10 Products are allowed. Please create a new RIN");
		    }
		   
		  };
		    
		  $scope.removeChoice = function() {
		    var lastItem = $scope.rin.prodInfoList.length-1;
		    if (lastItem>0) {
		    	$scope.rin.prodInfoList.splice(lastItem);
		    } else {
		    	CommonService.showMsg('danger',"Atleast one product is required to create a RIN");
		    }
		    
		  };
		
		  $scope.fetchGRCNos = function(fromName){

			  GRCService.fetchGRCNos(fromName)
				.then(
						function(data) {
							$scope.grcs = data;
						},
						function(errResponse){
							console.error('Error while fetching GRC numbers');
						}
				);
			};
			
			 $scope.fetchGRCData = function(grcNo){
				 if(grcNo!='NONE'){
					 GRCService.fetchGRCData(grcNo, false)
						.then(
								function(data) {
									$scope.grcData = data.object;
									$scope.setProductsList(data.object.grc);
								},
								function(errResponse){
									console.error('Error while fetching GRC data');
								}
						);
				 } else {
					 $scope.productsInfo = $scope.basicInfoMap[products];
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
		if(typeof $scope.rin.docDateValue != 'undefined') {
			$scope.rin.docDate = $scope.rin.docDateValue.toLocaleDateString();
		}
		RINService.createRIN($scope.rin)
		.then(
				function(data) {
					if(!(typeof data === 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							CommonService.showMsg('success',"Successfully created <strong>"+data.object+"</Strong>");
							$scope.showAddModal = false;
							$scope.fetchRINData(data.object,true);
						}
					}
					
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
	
	
	
	$scope.fetchRINData = function(rinNo,fromToInfo){

		if(!(typeof rinNo === 'undefined')){
		
		RINService.fetchRINData(rinNo,fromToInfo)
		.then(
				function(data) {
					
					if(data.error!= null){
						CommonService.showMsg('danger',data.error.message);
					} else {
						$scope.rinData = data.object;
						if(fromToInfo){
							$scope.showRINData = false;
							$scope.showRINDetails = true;
						} else {
							$scope.showRINDetails = false;
							$scope.showRINData = true;
						}
					}
				},
				function(errResponse){
					console.error('Error while fetching RIN data');
				}
		);
		
		};
	};
	
	$scope.fetchBasicInfoToPopulate = function(){

		CommonService.fetchInfoToPopulate()
		.then(
				function(data) {
					$scope.basicInfoMap = data;
					$scope.productsInfo = $scope.basicInfoMap[products];
					$scope.prodsList = $scope.basicInfoMap[products];
				},
				function(errResponse){
					console.error('Error while fetching basicInfo');
				}
		);
	};


	$scope.populateFromData = function(from){

		$scope.dataList = $scope.basicInfoMap[from];
		
	};
	
	
	$scope.setProductsList = function(data){
		var prodList =[{id:null,name:''}];
			
		var prodsInfoList = data.prodInfoList;
		for (var i = 0; i < prodsInfoList.length; i++) {
			if(prodsInfoList[i].status != 'CLOSED') {
				if(i>0){
					prodList.push({});
				}
			    prodList[i].id = prodsInfoList[i].product.productId;
				prodList[i].name = prodsInfoList[i].product.productName;
			}
			
		}
		$scope.productsInfo = prodList;
	};
	
	
	/*$scope.setSelectedProdInfo = function(selectedProductId){
			
		var prodsInfoList = $scope.grcData.grc.prodInfoList;
		for (var i = 0; i < prodsInfoList.length; i++) {
			
			if(prodsInfoList[i].product.productId == selectedProductId){
				$scope.rin.prodsInfoList[i].product.unitBasicRate = prodsInfoList[i].product.unitBasicRate;
				$scope.rin.prodsInfoList[i].product.goodIn = prodsInfoList[i].product.goodIn;
				$scope.rin.prodsInfoList[i].product.defIn = prodsInfoList[i].product.defIn;
			}
		}
	};*/
	
	
	
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


}]);































































