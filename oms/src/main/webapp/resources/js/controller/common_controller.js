'use strict';

omsApp.controller('CommonController', ['$scope', 'CommonService', function($scope, CommonService) {
			
			$scope.getCounts = function(){
				$scope.homeResponseDto ;
              CommonService.fetchCounts()
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


