'use strict';

App.controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
			$scope.products=[];
                        
			$scope.fetchAllProducts = function(){
				ProductService.fetchAllCategories()
                  .then(
      					       function(data) {
      					    	 $scope.products = data;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Products');
            					}
      			       );
          };
          
          $scope.fetchAllProducts();
           
         }]);
