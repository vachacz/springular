var module = angular.module('module.security', [ 'springular.rest' ]);
module.controller('controller.security', ['$scope', '$modal', '$location', 'AuthService', function ($scope, $modal, $location, AuthService) {

	$scope.credentials = {
		login : "",
		password : ""
	};
	
	$scope.signIn = function () {
		AuthService.signIn($scope.credentials).then(function (loginResult) {
			if (!loginResult) {
				showLoginFailedPopover();
			}
		});
	};
	
	$scope.signOut = function () {
		AuthService.signOut();
		
		$scope.credentials.login = "";
		$scope.credentials.password = "";
		
		$location.path( "/" );
	};
	
	$scope.register = function () {
		var modalInstance = $modal.open({
			templateUrl: 'views/register.html',
			controller: 'controller.modal.register',
		});
	};

	/** TODO: extract into jQuery plugin */
	showLoginFailedPopover = function() {
		$("#signInButton")
		.popover({
		    html: 'true',
		    title : '<span class="text-info"><strong>Login failed</strong></span> <button type="button" id="closeLoginFailed" class="close">&times;</button>',
			content: "Please, reenter your credentials.",
			placement: "bottom"
		})
		.on('shown.bs.popover', function () {
		    var $popup = $(this);
		    $(this).next('.popover').find('#closeLoginFailed').click(function (e) {
		        $popup.popover('hide');
		    });
		})
		.popover('show');
	};
	
}]);

module.controller('controller.modal.register', ['$scope', '$modalInstance', function($scope, $modalInstance) {

    $scope.closeUser = function () {
    	$modalInstance.dismiss('cancel');
    };

}]);