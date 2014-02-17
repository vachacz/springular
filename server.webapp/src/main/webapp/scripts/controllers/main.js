var mainPage = angular.module('module.main', [ 'springular.rest' ]);
mainPage.controller('controller.main', ['$scope', function ($scope) {

	$scope.login = function () {
	    alert($scope.userLogin + $scope.userPassword);
	    alert('TODO Register');
	};
	
	$scope.register = function () {
	    alert('TODO Register');
	};

}]);