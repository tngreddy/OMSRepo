'use strict';

omsApp.factory('LoginService', ['$http', '$q','CommonService', function($http, $q, CommonService){

	return {

		authenticateUser: function(user) {
			return $http.post(CommonService.getBaseUrl()+'/service/auth/signIn',user)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while authenticating user');
						return $q.reject(errResponse);
					}
			);
		},
 
	};

}]);
