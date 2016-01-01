//directive to initiate datatables
omsApp.directive('myTabs', ['CommonService', function(CommonService) {
  return {
	  
    // angular passes the element reference to you
    compile: function(element) {
    
      CommonService.initializeDataTable(element,'.addbuttoncontainer');
      }
  };
}]);

omsApp.directive('myDate', function() {
	  return {
		  
	    // angular passes the element reference to you
	    link: function(element) {
	      	var today = new Date().toISOString().split('T')[0];
	    	(element)[0].setAttribute('max', today);
	      
	    }
	  };
	});




// directive to display modals
omsApp.directive('modal', function () {
	return {
		template: '<div class="modal fade">' +
		'<div class="modal-dialog {{size}}">' +
		'<div class="modal-content" ng-transclude>' +
		'</div>' +
		'</div>' +
		'</div>',
		restrict: 'E',
		transclude: true,
		replace:true,
		scope: true,
		link: function postLink(scope, element, attrs) {
			
			scope.size = attrs.size;
			
			scope.$watch(attrs.visible, function(value){
				if(value == true)
					$(element).modal('show');
				else
					$(element).modal('hide');
			});

			$(element).on('shown.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = true;
				});
			});

			$(element).on('hidden.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = false;
				});
			});
		}
	};
});



/*omsApp.directive('validateCategory', function() {
	  return {
	    // angular passes the element reference to you
	    compile: function(element) {
	    	$(element).formValidation({
	            framework: 'bootstrap',
	            icon: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            excluded: ':disabled',
	            fields: {
	            	categoryName: {
	                    validators: {
	                        notEmpty: {
	                            message: 'The categoryName is required'
	                        }
	                    }
	                }
	            }
	        });
	    	*/
/*omsApp.directive('validateCategory', function() {
	  return {
	    // angular passes the element reference to you
	    link: function(element) {
	    	$(element).formValidation();
	
	    	      
	    }
	  };
	});*/


//directive to display modals
/*omsApp.directive('deleteModalContent', function () {
	return {
		template: '<div class="modal-header with-border">'+
		'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
			'<span aria-hidden="true">&times;</span>'+
		'</button>'+
		'<h4 class="modal-title" id="myModalLabel">Delete {{type}}</h4>'+
	'</div>'+
	'<div class="modal-body">'+
	  '<form class="form-horizontal">'+
	    '<div class="box-body">'+
	     '<div class="form-group">'+
	        '<div class="col-sm-12 text-center">'+
	         ' Do you want to delete this {{type}} <strong>{{value}}</strong>?'+
	        '</div>'+
	     '</div>'+
	    '</div>'+
	  '</form>'+
	  '</div>'+
	  '<div class="modal-footer">'+
	      '<button type="button" class="btn btn-flat btn-success" data-dismiss="modal">No</button>'+
	      '<button type="button" class="btn btn-flat btn-danger" ng-click="{{method}}">Yes</button>'+
	    '</div>',
		restrict: 'E',
		replace:false,
		scope:true,
		link: function postLink(scope, element, attrs) {
			scope.heading = attrs.heading;
			scope.type = attrs.type;
			scope.value = scope.$eval(attrs.value);
			scope.method = scope.$eval(attrs.method);
		}
	};
});*/
    
