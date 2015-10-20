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

						$scope.deleteCategoryModal = function(category){
							$scope.showDeleteModal = true;
							$scope.category = category;
						};

         }]);



				 omsApp.directive('modal', function () {
				     return {
				       template: '<div class="modal fade">' +
				           '<div class="modal-dialog">' +
				             '<div class="modal-content">' +
				               '<div class="modal-header">' +
				                 '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
				                 '<h4 class="modal-title">{{ title }}</h4>' +
				               '</div>' +
				               '<div class="modal-body" ng-transclude></div>' +
				             '</div>' +
				           '</div>' +
				         '</div>',
				       restrict: 'E',
				       transclude: true,
				       replace:true,
				       scope:true,
				       link: function postLink(scope, element, attrs) {
				         scope.title = attrs.title;

				         scope.$watch(attrs.visible, function(value){
				           if(value == true)
				             $(element).modal('show');
				           else
				             $(element).modal('hide');
				         });

				         $(element).on('shown.bs.modal', function(){
				           scope.$apply(function(){
				             scope.$parent[attrs.visible] = true;
				           });
				         });

				         $(element).on('hidden.bs.modal', function(){
				           scope.$apply(function(){
				             scope.$parent[attrs.visible] = false;
				           });
				         });
				       }
				     };
				   });
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
