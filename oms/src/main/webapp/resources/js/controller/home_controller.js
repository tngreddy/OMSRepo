'use strict';

omsApp.controller('HomeController', ['$scope', 'HomeService', function($scope, HomeService) {
			
			$scope.getCounts = function(){
				$scope.homeResponseDto ;
              HomeService.fetchCounts()
                  .then(
      					       function(data) {
      					    	 $scope.homeResponseDto=data;
      					       },
            					function(errResponse){
            						console.error('Error while fetching counts');
            					}
      			       );
          };
          
          $scope.getCounts();
 

         }]);


