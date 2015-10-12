'use strict';

omsApp.controller('SupplierController', ['$scope', 'SupplierService', function($scope, SupplierService) {
			$scope.suppliers=[];
                        
			$scope.fetchAllSuppliers = function(){
				SupplierService.fetchAllSuppliers()
                  .then(
      					       function(data) {
      					    	 $scope.suppliers = data;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Suppliers');
            					}
      			       );
          };
          
          $scope.fetchAllSuppliers();
           
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
