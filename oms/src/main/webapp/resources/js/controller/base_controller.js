'use strict';

omsApp.controller('BaseController', ['$scope','CommonService','$state', '$stateParams', function($scope,CommonService, $state, $stateParams) {
	

	CommonService.setPreviousState();
	
	$scope.user = CommonService.checkAuth();
	
	if($scope.user!=null && typeof data != 'undefined')
	CommonService.checkAdminStates($scope.user.maverick);
	
	$scope.signOut = function(){
		
		CommonService.clearUserContext();
		CommonService.removePreviousState();
		$state.go('login');
	
		};
		
	  $scope.reloadState = function() {
			setTimeout(function(){
				$state.transitionTo($state.current, $stateParams, {
					reload: true,
					inherit: false,
					notify: true
				});

			}, 300);
		};
		
		$scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function () {
		    $scope.dt = null;
		  };

		  // Disable weekend selection
		  $scope.disabled = function(date, mode) {
		    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		  };

		  $scope.toggleMin = function() {
		    $scope.minDate = $scope.minDate ? null : new Date();
		  };
		  $scope.toggleMin();
		 // $scope.maxDate = new Date(2020, 5, 22);
		  $scope.maxDate = new Date();
		  
		  $scope.open = function($event) {
		    $scope.status.opened = true;
		  };
		  
		  $scope.fromMinDate = function() {
			  return new Date($scope.dt);
		  }
		  
		  
		  $scope.openRange = function($event,type) {
			  if(type =='from') {
				  $scope.status.fromOpen = true;
			  } else if(type =='to') {
				  $scope.status.toOpen = true;
			  }
			  };
				  
		  $scope.setDate = function(year, month, day) {
		    $scope.dt = new Date(year, month, day);
		  };

		  $scope.dateOptions = {
		    formatYear: 'yyyy',
		    startingDay: 1
		  };

		  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd/MM/yyyy', 'shortDate'];
		  $scope.format = $scope.formats[2];

		  $scope.status = {
		    opened: false
		  };

		  var tomorrow = new Date();
		  tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date();
		  afterTomorrow.setDate(tomorrow.getDate() + 2);
		  $scope.events =
		    [
		      {
		        date: tomorrow,
		        status: 'full'
		      },
		      {
		        date: afterTomorrow,
		        status: 'partially'
		      }
		    ];

		  $scope.getDayClass = function(date, mode) {
		    if (mode === 'day') {
		      var dayToCheck = new Date(date).setHours(0,0,0,0);

		      for (var i=0;i<$scope.events.length;i++){
		        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

		        if (dayToCheck === currentDay) {
		          return $scope.events[i].status;
		        }
		      }
		    }

		    return '';
		  };
}]);































































