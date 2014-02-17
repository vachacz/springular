var mainPage = angular.module('module.main', [ 'springular.rest' ]);
mainPage.controller('controller.main', ['$scope', '$modal', '$location', function ($scope, $modal, $location) {

	$scope.userLoggedIn = false;
	$scope.credentials = {
		login : "",
		password : ""
	};
	
	$scope.signIn = function () {
	    $scope.userLoggedIn = true;
	};
	
	$scope.register = function () {
	    var modalInstance = $modal.open({
	      templateUrl: 'views/register.html',
	      controller: 'controller.modal.register',
	    });
	};
	
	$scope.signOut = function () {
		$scope.userLoggedIn = false;
		$location.path( "/" );
	};
	
    $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };

}]);

mainPage.controller('controller.modal.register', ['$scope', '$modalInstance', function($scope, $modalInstance) {

    $scope.closeUser = function () {
    	$modalInstance.dismiss('cancel');
    };

}]);