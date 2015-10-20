//directive to initiate datatables
omsApp.directive('myTabs', function() {
  return {
    // angular passes the element reference to you
    compile: function(element) {
       setTimeout(function(){
    	  $(element).dataTable();
    	}, 1000);
      
    }
  };
});

//directive to display modals
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
		scope:true,
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
