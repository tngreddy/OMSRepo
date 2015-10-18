'use strict';

omsApp.controller('CategoryController', ['$scope', 'CategoryService', function($scope, CategoryService) {
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

          $scope.fetchAllCategories();

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
