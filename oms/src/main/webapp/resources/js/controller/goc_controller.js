'use strict';

omsApp.controller('GOCController', ['$scope', 'GOCService', 'CommonService','ReportsService', '$state', '$stateParams', function($scope, GOCService, CommonService, ReportsService, $state, $stateParams) {
	$scope.gocs=[];
	$scope.goc={};
	$scope.basicInfoMap = {};
	$scope.productsInfo={};
	var products = "PRODUCTS";
	$scope.showGOCDetails = false;
	$scope.showGOCData = false;
	$scope.gocData = {};
	$scope.productStocks = {};
	$scope.selectedProds = [];
	$scope.goodBalance=[];
	//$scope.gin.prodInfoList = [{}];
	
	
	 $scope.datepickerConfig = {
	            allowFuture: false,
	           // dateFormat: 'DD/MM/YYYY'
	            dateFormat: 'yyyy-MM-dd'
	        };

	
	 $scope.addNewChoice = function() {
		    var newItemNo = $scope.goc.prodInfoList.length+1;
		    if($scope.goc.prodInfoList.length<10) {
		    	 $scope.goc.prodInfoList.push({});
		    } else {
		       	CommonService.showMsg('danger',"Only 10 Products are allowed. Please create a new GOC");
		    }
		   
		  };
		    
		  $scope.removeChoice = function() {
		    var lastItem = $scope.goc.prodInfoList.length-1;
		    if (lastItem>0) {
		    	$scope.goc.prodInfoList.splice(lastItem);
		    	$scope.selectedProds.splice(lastItem);
		    } else {
		    	CommonService.showMsg('danger',"Atleast one product is required to create a GOC");
		    }
		    
		  };
		
	
	
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
		if(typeof $scope.goc.docDateValue != 'undefined') {
			$scope.goc.docDate = $scope.goc.docDateValue.toLocaleDateString();
		}
		GOCService.createGOC($scope.goc)
		.then(
				function(data) {
					if(!(typeof data === 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							CommonService.showMsg('success',"Successfully created <strong>"+data.object+"</Strong>");
							$scope.showAddModal = false;
							$scope.fetchGOCData(data.object,true);
						}
					}	
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
	
	
	$scope.fetchGOCData = function(gocNo,fromToInfo){

		if(!(typeof gocNo === 'undefined')){
		
		GOCService.fetchGOCData(gocNo,fromToInfo)
		.then(
				function(data) {
					if(data.error!= null){
						CommonService.showMsg('danger',data.error.message);
					} else {
						$scope.gocData = data.object;
						if(fromToInfo){
							$scope.showGOCData = false;
							$scope.showGOCDetails = true;
						} else {
							$scope.showGOCDetails = false;
							$scope.showGOCData = true;
						}
					}
				},
				function(errResponse){
					console.error('Error while fetching GOC data');
				}
		);
		
		};
	};
	
	
	$scope.fetchProductStock = function(productId, index){
		
		if($scope.validateProdList(productId,index)){
			
			ReportsService.fetchProductStock(productId)
			.then(
					function(data) {
						$scope.productStocks[productId]=data;
						$scope.goodBalance[index] = data.product.goodBalance;
						angular.element('#goodOut'+index).attr('max',data.product.goodBalance);
						angular.element('#defOut'+index).attr('max',data.product.defBalance);
					},
					function(errResponse){
						console.error('Error while fetching product stock');
					}
			);
		}
		
	
	};
	
	/*$scope.fetchGOCData = function(gocNo,fromToInfo){

		if(!(typeof gocNo === 'undefined')){
		
		GOCService.fetchGOCData(gocNo,fromToInfo)
		.then(
				function(data) {
					$scope.gocData = data;
					if(fromToInfo){
						$scope.showGOCData = false;
						$scope.showGOCDetails = true;
					} else {
						$scope.showGOCDetails = false;
						$scope.showGOCData = true;
					}
					
					$scope.loading = false;
				},
				function(errResponse){
					console.error('Error while fetching GOC data');
				}
		);
		
		};
	};*/
	
	
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
	
	
	$scope.validateProdOuts = function(outType,outValue){
		
	/* var prodStock = $scope.productStocks[productId];
		if(null!=prodStock){
			if (outType =='goodOut') {
				if(outValue>prodStock.product.goodBalance){
					return false;
				}
				
			 } else if(outType =='defOut') {
				 if(outValue>prodStock.product.defBalance){
						return false;
					}
			 }
		}
			
		return true;*/
	}; 
	
	$scope.validateProdList = function(productId,index){
			
		if(typeof productId != 'undefined') {
			
			
			
			if (($.inArray(productId, $scope.selectedProds) > -1) && $scope.selectedProds[index] != productId) {
				CommonService.showMsg('danger',"Product already selected");
				angular.element('#productName'+index).addClass('has-error');
				return false;
			} else {
				/*if(index>$scope.selectedProds.length){
					$scope.selectedProds.push();
				}*/
				$scope.selectedProds[index]=productId;
				
			}
			
			/*if((0 in selectedProds)){
				$scope.selectedProds[index] = productId;
			} else {
				var values = $.map($scope.selectedProds, function(v) { return v; });
				if ($.inArray(productId, values) > -1) {
					return false
				}
				
			}*/
			
			
		}				
			return true;
		}; 
	
	
	//angular.element('#goodOut'+index).attr('max',data.product.goodBalance);
	//angular.element('#defOut'+index).attr('max',data.product.defBalance);
	
	$scope.fetchBasicInfoToPopulate();
	
	

$scope.createGOCModal = function(){
	$scope.goc={};
	$scope.goc.prodInfoList = [{}];
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


}]);































































