'use strict';

omsApp.controller('ProductController', ['$scope', 'ProductService','CategoryService','CommonService', '$state', '$stateParams', function($scope, ProductService, CategoryService,CommonService,$state, $stateParams) {
	$scope.products=[];
	$scope.categories=[];
	$scope.product = {productId:null,productName:'',categoryName:''};
	$scope.type = 'Product';
	$scope.tabElmnt = angular.element('#productTable');
	$scope.btnsContainer = '.addbuttoncontainer';
	

	$scope.fetchAllCategories = function(){

		CategoryService.fetchAllCategories()
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							//CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.categories = data.object;
							
						}
					
				    }
					
				},
				function(errResponse){
					console.error('Error while fetching Currencies');
				}
		);
	};

	$scope.fetchAllCategories();

	$scope.fetchAllProducts = function(){
		ProductService.fetchAllProducts()
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.products = data.object;
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
						}
					
				    }
					
				},
				function(errResponse){
					console.error('Error while fetching products');
				}
		);
	};

	$scope.fetchAllProducts();

	$scope.addProduct = function(){
		ProductService.addProduct($scope.product)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showAddModal = false;
							CommonService.showMsg('success',CommonService.buildSuccessMsg($scope.type,$scope.product.productName));
							$scope.fetchAllProducts();
							
						}
					
				    }
				},
				function(errResponse){
					console.error('Error while adding Product');
				}
		);
	};

	$scope.updateProduct = function(product){
		ProductService.updateProduct(product)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showEditModal = false;
							CommonService.showMsg('success',CommonService.buildUpdateMsg($scope.type,product.productName));
							$scope.fetchAllProducts();
						}
					
				    }
				},
				function(errResponse){
					console.error('Error while updating product');
				}
		);
	};

	$scope.deleteProduct = function(product){
		ProductService.deleteProduct(product.productId)
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.showDeleteModal = false;
							CommonService.showMsg('success',CommonService.buildDeleteMsg($scope.type,product.productName));
							$scope.fetchAllProducts();
						}
					
				    }
					
						
				},
				function(errResponse){
					console.error('Error while deleting product');
				}
		);
	};



	$scope.addProductModal = function(){
		$scope.showAddModal = true;
		$scope.product = {productId:null,productName:'',categoryName:''};
	};

	$scope.editProductModal = function(product) {
		$scope.showEditModal = true;
		$scope.product = product;
	};

	//Method to show delete overlay
	$scope.deleteProductModal = function(product){
		$scope.showDeleteModal = true;
		$scope.product = product;
	};

	
}]);
