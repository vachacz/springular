var mainPage = angular.module('module.salary', [ 'springular.rest' ]);
mainPage.controller('controller.salary', ['$scope', '$modal', '$location', 'RestApiSalary', function ($scope, $modal, $location, RestApiSalary) {

	$scope.searchPopoverVisible = false;
	$scope.filterCriteria = { employeeFirstName : "", employeeLastName : "", year : "", month : "", amount : "" };
	
	RestApiSalary.query($scope.filterCriteria).success(function (salaries) {
		$scope.salaries = salaries;
	});
	
	$('#salarySearchCriteria').popover({
		html: true,
		content: 'Click <i><b>Search</b></i> to refresh the results.',
		placement: 'right',
		trigger: 'manual'
	});
	
	$scope.$watch('filterCriteria', function () {
		if (! $scope.searchPopoverVisible) {
			$('#salarySearchCriteria').popover('show');
			$scope.searchPopoverVisible = true;
		}
	}, true);
	
	$scope.executeSalarySearch = function () {
		$('#salarySearchCriteria').popover('hide');
		$scope.searchPopoverVisible = false;
		
		RestApiSalary.query($scope.filterCriteria).success(function (salaries) {
			$scope.salaries = salaries;
		});
	}
	
}]);
