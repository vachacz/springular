var mainPage = angular.module('module.main', [ 'springular.rest' ]);
mainPage.controller('controller.main', ['$scope', '$modal', '$location', '$cookieStore', 'AuthService', function ($scope, $modal, $location, $cookieStore, AuthService) {

	$scope.userLoggedIn = $cookieStore.get('userLoggedIn');
	
	$scope.credentials = {
		login : "",
		password : ""
	};
	
	$scope.signIn = function () {
		AuthService.signIn($scope.credentials).then(function (loginResult) {
			$scope.userLoggedIn = loginResult;
		});
		$cookieStore.put('userLoggedIn', 'true');
	};
	
	$scope.signOut = function () {
		AuthService.signOut();
		$scope.userLoggedIn = false;
		$cookieStore.remove('userLoggedIn');
		$scope.credentials = {
			login : "",
			password : ""
		};
		$location.path( "/" );
	};
	
	$scope.register = function () {
		var modalInstance = $modal.open({
			templateUrl: 'views/register.html',
			controller: 'controller.modal.register',
		});
	};
	
}]);

mainPage.controller('controller.modal.register', ['$scope', '$modalInstance', function($scope, $modalInstance) {

    $scope.closeUser = function () {
    	$modalInstance.dismiss('cancel');
    };

}]);