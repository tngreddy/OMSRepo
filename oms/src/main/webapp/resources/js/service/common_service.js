'use strict';

omsApp.factory('CommonService', ['$http', '$q', 'Flash','$state', function($http, $q, Flash, $state){

	var userContext = null;
	var adminOnlyStates = ['base.category','base.product','base.customer','base.supplier','base.user'];
	return {
		 
		 fetchCounts: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/common/count')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching counts');
						return $q.reject(errResponse);
					}
			);
		},

		fetchInfoToPopulate: function() {
			return $http.get('http://ntiyyagura:8080/oms/service/common/basicInfo')
			.then(
					function(response){
						return response.data.object;
					}, 
					function(errResponse){
						console.error('Error while fetching basicInfo');
						return $q.reject(errResponse);
					}
			);
		},

		showMsg: function(msgType,msg) {
			if(msgType == 'danger') {
				
				Flash.create(msgType, "<i class='fa fa-exclamation-circle'></i> "+msg);
				
			} else if (msgType == 'success'){
				
				Flash.create(msgType, "<i class='fa fa-thumbs-o-up'></i> "+msg);
			} else if (msgType == 'info'){
			
				Flash.create(msgType,msg);
			}
			
		},
		setUserContext: function(user) {
			if(null!=user) {
				 userContext = user;
				 if(user.userRole.roleName == 'ADMIN') {
					 userContext.isAdmin = true;
				 } else {
					 userContext.isAdmin = false;
				 }
				 sessionStorage.setItem('userContext',JSON.stringify(userContext));
			}
			     
		},
		getUserContext: function() {
			if(null==userContext) {
				userContext = $.parseJSON(sessionStorage.getItem('userContext'));
			}
		     return userContext;
		},
		clearUserContext: function(user) {
		     userContext = null;
		     sessionStorage.removeItem('userContext');
		},

		checkAuth: function() {
				
				if (null==this.getUserContext()) {
					$state.go('login');
				} else {
					return this.getUserContext();
				}
		
		},
		checkAdminStates: function(isAdmin) {
			
			if (!isAdmin) {
				if ($.inArray($state.current.name, adminOnlyStates) > -1){
					$state.go('base.dashboard');
				}
			} 
	
	},
		
		initializeDataTable:function(elmnt,btnContainerElmnt) {
			
			$(elmnt).dataTable().fnDestroy();
			 setTimeout(function(){
			    	var table = $(elmnt).DataTable( {
			    		  
			    	        buttons: [
			    	          'copy',
			    	                 
			    	            {
			    	                extend: 'pdf',
			    	                exportOptions: {
			    	                    columns: ':visible'
			    	                }
			    	            },
			    	            {
			    	                extend: 'excel',
			    	                exportOptions: {
			    	                    columns: ':visible'
			    	                }
			    	            },
			    	            {
			    	                extend: 'print',
			    	                exportOptions: {
			    	                    columns: ':visible'
			    	                }
			    	            },
			    	             'colvis'
			    	            
			    	            
			    	        ]
			    	       	    } );
			    	
			    	table.buttons().container()
			        .appendTo( $(btnContainerElmnt) );
			      
			      }, 1000);
		}
		
		

	};

}]);



	    
