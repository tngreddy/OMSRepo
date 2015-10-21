'use strict';

omsApp.controller('CategoryController', ['$scope', 'CategoryService','$uibModal','$log', '$state', '$stateParams', function($scope, CategoryService, $uibModal, $log, $state, $stateParams) {
	$scope.categories=[];
	$scope.category={categoryId:null,categoryName:''};
	$scope.reload = false;
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



	$scope.addCategory = function(){
		CategoryService.addCategory($scope.category)
		.then(
				function(data) {
					$scope.showAddModal = false;
					$scope.reloadState();	
				},
				function(errResponse){
					console.error('Error while adding category');
				}
		);
	};

	$scope.updateCategory = function(category){
		CategoryService.updateCategory(category)
		.then(
				function(data) {
					$scope.showEditModal = false;
					$scope.reloadState();		
				},
				function(errResponse){
					console.error('Error while updating category');
				}
		);
	};

	$scope.deleteCategory = function(categoryId){
		CategoryService.deleteCategory(categoryId)
		.then(
				function(data) {
					$scope.showDeleteModal = false;
					$scope.reloadState();			
				},
				function(errResponse){
					console.error('Error while deleting category');
				}
		);
	};

	$scope.fetchAllCategories();

	/*$scope.editCategoryModal = function (size, selectedCategory) {

        	    var modalInstance = $uibModal.open({
        	      animation: $scope.animationsEnabled,
        	      templateUrl: 'resources/templates/category/editCategory.html',
        	      controller: function ($scope, $modalInstance, category) {
        	           $scope.category = category;
      	        },
        	      size: size,
        	      resolve: {
        	        category: function () {
        	          return selectedCategory;
        	        }
        	      }
        	    });

        	    modalInstance.result.then(function (selectedItem) {
        	      $scope.selected = selectedItem;
        	    }, function () {
        	      $log.info('Modal dismissed at: ' + new Date());
        	    });
        	  };*/

	$scope.addCategoryModal = function(){
		$scope.showAddModal = true;
	};
	
	$scope.editCategoryModal = function (category) {
		$scope.showEditModal = true;
		$scope.category = category;
	};

	$scope.deleteCategoryModal = function(category){
		$scope.showDeleteModal = true;
		$scope.category = category;
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































































