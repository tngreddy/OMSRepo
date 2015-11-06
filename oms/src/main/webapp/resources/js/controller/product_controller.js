'use strict';

omsApp.controller('ProductController', ['$scope', 'ProductService','CategoryService', '$state', '$stateParams', function($scope, ProductService, CategoryService,$state, $stateParams) {
	$scope.products=[];
	$scope.categories=[];
	$scope.product = {productId:null,productName:'',categoryName:''};
	

	$scope.fetchAllCategories = function(){

		CategoryService.fetchAllCategories()
		.then(
				function(data) {
					$scope.categories = data;
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
					$scope.products = data;
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
					$scope.showaAddModal = false;
					$scope.reloadState();	

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
					$scope.showEditModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while updating product');
				}
		);
	};

	$scope.deleteProduct = function(productId){
		ProductService.deleteProduct(productId)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();	
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
