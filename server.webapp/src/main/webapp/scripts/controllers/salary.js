var mainPage = angular.module('module.salary', [ 'springular.rest' ]);
mainPage.controller('controller.salary', ['$scope', '$modal', '$location', 'RestApiSalary', function ($scope, $modal, $location, RestApiSalary) {

	RestApiSalary.query({}, function (salaries) {
        $scope.salaries = salaries;
    });
	
}]);
