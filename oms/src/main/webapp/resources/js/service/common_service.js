'use strict';

omsApp.factory('CommonService', ['$http', '$q', 'Flash','$state', function($http, $q, Flash, $state){

	var userContext = null;
	
	var adminOnlyStates = ['base.category','base.product','base.customer','base.supplier','base.user'];
	var baseUrl = 'http://192.168.4.252:8080/sms';
	//var baseUrl = 'http://localhost:8080/sms';
	return {
		 
		 fetchCounts: function() {
			return $http.get(baseUrl+'/service/common/count')
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
			return $http.get(baseUrl+'/service/common/basicInfo')
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
		buildSuccessMsg: function(type,msg) {
					
			return "Successfully added "+type+" <strong>"+msg+"</Strong>"
			
			},
		buildUpdateMsg: function(type,msg) {
				
				return "Successfully Updated "+type+" <strong>"+msg+"</Strong>"
				
			},
		buildDeleteMsg: function(type,msg) {
					
					return "Successfully deleted "+type+" <strong>"+msg+"</Strong>"
					
			},
		
		buildTransactionSuccessMsg: function(msg) {
				
				return "Successfully created <strong>"+msg+"</Strong>"
				
		},
		setUserContext: function(user) {
			if(null!=user) {
				 userContext = user;
				 if(user.userRole.roleName == 'ADMIN') {
					 userContext.maverick = true;
				 } else {
					 userContext.maverick = false;
				 }
				 userContext.userRole = null;
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

		setPreviousState: function() {
			
			sessionStorage.setItem('previousState',JSON.stringify($state.current.name));
				     
		},
		getPreviousState: function() {
			
				return $.parseJSON(sessionStorage.getItem('previousState'));
			
		},
		removePreviousState: function() {
			
			sessionStorage.removeItem('previousState');
		
		},		
		checkAuth: function() {
				
				if (null==this.getUserContext()) {
					$state.go('login');
				} else {
					return this.getUserContext();
				}
		
		},
		checkAdminStates: function(maverick) {
			
			if (!maverick) {
				if ($.inArray($state.current.name, adminOnlyStates) > -1){
					$state.go('base.dashboard');
				}
			} 
	
	},
		getBaseUrl: function() {
		
		return baseUrl;

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



	    
