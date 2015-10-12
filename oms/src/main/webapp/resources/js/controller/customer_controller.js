'use strict';

omsApp.controller('CustomerController', ['$scope', 'CustomerService', function($scope, CustomerService) {
			$scope.customers=[];
                        
			$scope.fetchAllCustomers = function(){
				CustomerService.fetchAllCustomers()
                  .then(
      					       function(data) {
      					    	 $scope.customers = data;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Customers');
            					}
      			       );
          };
          
          $scope.fetchAllCustomers();
           
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
