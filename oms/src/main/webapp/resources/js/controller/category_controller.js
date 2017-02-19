'use strict';

omsApp.controller('CategoryController', ['$scope', 'CategoryService', 'CommonService','$uibModal','$log', '$state', '$stateParams', function($scope, CategoryService,CommonService, $uibModal, $log, $state, $stateParams) {
	$scope.categories=[];
	$scope.category={categoryId:null,categoryName:''};
	$scope.reload = false;
	$scope.type = 'Category';
	$scope.tabElmnt = angular.element('#categoryTable');
	$scope.btnsContainer = '.addbuttoncontainer';
		
	$scope.fetchAllCategories = function(){
		
		CategoryService.fetchAllCategories()
		.then(
				function(data) {
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
						} else {
							$scope.categories = data.object;
							CommonService.initializeDataTable($scope.tabElmnt,$scope.btnsContainer);
							
						}
					
				    }
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
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
							
						} else {
							$scope.showAddModal = false;
							CommonService.showMsg('success',CommonService.buildSuccessMsg($scope.type,$scope.category.categoryName));
							$scope.fetchAllCategories();
						}
					
				    }
					
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
					
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
							
						} else {
							$scope.showEditModal = false;
							CommonService.showMsg('success',CommonService.buildUpdateMsg($scope.type,category.categoryName));
							$scope.fetchAllCategories();
							
						}
					
				    }
					
						
				},
				function(errResponse){
					console.error('Error while updating category');
				}
		);
	};

	$scope.deleteCategory = function(category){
		CategoryService.deleteCategory(category.categoryId)
		.then(
				function(data) {
					if((typeof data != 'undefined')){
						
						if(data.error!= null){
							CommonService.showMsg('danger',data.error.message);
							
						} else {
							$scope.showDeleteModal = false;
							CommonService.showMsg('success',CommonService.buildDeleteMsg($scope.type,category.categoryName));
							$scope.fetchAllCategories();
						}
					
				    }
					
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
		$scope.category={categoryId:null,categoryName:''};
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



}]);































































