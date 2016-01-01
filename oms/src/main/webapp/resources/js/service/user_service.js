'use strict';

omsApp.factory('UserService', ['$http', '$q', function($http, $q){

	return {

		fetchAllUsers: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/user/')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching users');
						return $q.reject(errResponse);
					}
			);
		},

		addUser: function(user) {
			return $http.post('http://ntiyyagura:8080/oms/service/user/', user)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while adding user');
						return $q.reject(errResponse);
					}
			);

		},
		updateUser: function(user) {
			return $http.put('http://ntiyyagura:8080/oms/service/user/', user)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating user');
						return $q.reject(errResponse);
					}
			);

		},

		deleteUser: function(userId) {
			return $http.delete('http://ntiyyagura:8080/oms/service/user/'+userId)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching user count');
						return $q.reject(errResponse);
					}
			);

		}



	};

}]);
