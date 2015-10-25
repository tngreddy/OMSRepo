'use strict';

omsApp.controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
	$scope.products=[];

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
					$scope.fetchAllProducts();

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
					$scope.fetchAllProducts();
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
					$scope.fetchAllProducts();
				},
				function(errResponse){
					console.error('Error while deleting product');
				}
		);
	};

	

	$scope.addProductModal = function(){
		$scope.showAddModal = true;
		
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
