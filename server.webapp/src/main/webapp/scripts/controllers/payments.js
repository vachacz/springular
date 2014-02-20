var mainPage = angular.module('module.payments', [ 'springular.rest' ]);
mainPage.controller('controller.payments', ['$scope', '$modal', '$location', 'RestApiPayments', function ($scope, $modal, $location, RestApiPayments) {

	RestApiPayments.query({}, function (payments) {
        $scope.payments = payments;
    });
	
}]);
