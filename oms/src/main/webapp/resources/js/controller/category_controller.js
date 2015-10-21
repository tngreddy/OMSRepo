'use strict';

omsApp.controller('CategoryController', ['$scope', 'CategoryService','$uibModal','$log', function($scope, CategoryService, $uibModal, $log) {
			$scope.categories=[];
			$scope.category={categoryId:null,categoryName:''};
			$scope.load = false;
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
					$scope.fetchAllCategories();
					
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
					$scope.fetchAllCategories();
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
					$scope.fetchAllCategories();
					$scope.load = !$scope.load;
				},
				function(errResponse){
					console.error('Error while deleting category');
				}
		);
	};


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

						$scope.editCategoryModal = function (category) {
							$scope.showEditModal = true;
							$scope.category = category;
						};
	$scope.fetchAllCategories();

						$scope.deleteCategoryModal = function(category){
							$scope.showDeleteModal = true;
							$scope.category = category;
						};

         }]);































































