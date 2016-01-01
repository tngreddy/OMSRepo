'use strict';

omsApp.factory('LoginService', ['$http', '$q', function($http, $q){

	return {

		authenticateUser: function(user) {
			return $http.post('http://ntiyyagura:8080/oms/service/auth/signIn',user)
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
