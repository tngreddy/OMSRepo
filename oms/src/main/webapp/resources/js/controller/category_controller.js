'use strict';

omsApp.controller('CategoryController', ['$scope', 'CategoryService', function($scope, CategoryService) {
			$scope.categories=[];
                        
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
           
         }]);

$(function () {
  $('#categoryTable').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
  });
});
