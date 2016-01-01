'use strict';

omsApp.controller('LoginController', ['$scope', 'LoginService', 'CommonService','$uibModal','$log', '$state', '$stateParams', function($scope, LoginService,CommonService, $uibModal, $log, $state, $stateParams) {
	
	$scope.user={};
	$scope.validUser={};
	
	if (null != CommonService.getUserContext()) {
		$state.go('base.dashboard');
	} else {
		CommonService.showMsg('info','Welcome to <Strong>SMS</Strong>, Please login');
	}
	
	
	$scope.authenticateUser = function(){
		
		LoginService.authenticateUser($scope.user)
		.then(
				function(data) {
			
					if(data.error!= null){
						CommonService.showMsg('danger',data.error.message);
					} else {
						$scope.validUser = data.object;
						CommonService.setUserContext(data.object);
						CommonService.showMsg('success','Welcome <Strong>'+data.object.userName+'</Strong>');
						$state.go('base.dashboard');
					}
					
				},
				function(errResponse){
					console.error('Error while authenticating user');
				}
		);
	};
	
	

	$scope.reloadState = function() {
		setTimeout(function(){
			$state.transitionTo($state.current, $stateParams, {
				reload: true,
				inherit: false,
				notify: true
			});

		}, 100);
	};

}]);































































