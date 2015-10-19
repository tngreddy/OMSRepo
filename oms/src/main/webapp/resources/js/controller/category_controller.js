'use strict';

omsApp.controller('CategoryController', ['$scope', 'CategoryService','$uibModal','$log', function($scope, CategoryService, $uibModal, $log) {
			$scope.categories=[];
			$scope.category={categoryId:null,categoryName:''};

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
          
          
          $scope.addCategory = function($scopecategory){
              CategoryService.addCategory($scope.category)
                  .then(
      					       function(data) {
      					    	 $scope.categories = data;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };

          $scope.editCategoryModal = function (size, selectedCategory) {

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
        	  };

          

         }]);

/*$(function () {
  $('#categoryTable').DataTable({
    "paging": true,
    "lengthChange": true,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false
  });
	$('#categoryTable').DataTable();
});*/
