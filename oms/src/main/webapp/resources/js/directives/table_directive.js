
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

