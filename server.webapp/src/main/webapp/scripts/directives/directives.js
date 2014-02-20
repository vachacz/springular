SpringularApp.directive('activateOn', [ '$location', function(location) {
	return {
		restrict : 'A',
		scope : {
			path : '@path',
		},
		link : function(scope, element, attrs) {
			scope.$watch(function() {
				return location.path();
			}, function(newPath) {
				if (attrs.path === newPath) {
					element.addClass("active");
				} else {
					element.removeClass("active");
				}
			});
		}
	}
}]);
