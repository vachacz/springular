var mainPage = angular.module('module.salary', [ 'springular.rest' ]);
mainPage.controller('controller.salary', ['$scope', '$modal', '$location', 'RestApiSalary', function ($scope, $modal, $location, RestApiSalary) {

	$scope.searchPopoverVisible = false;
	$scope.filterCriteria = { employeeFirstName : "", employeeLastName : "", year : "", month : "", amount : "" };

	$scope.filterCriteria.employeeFirstName = $location.search().firstName;
	$scope.filterCriteria.employeeLastName = $location.search().lastName;
	
	RestApiSalary.query($scope.filterCriteria).success(function (salaries) {
		$scope.salaries = salaries;
	});
	
	$('#salarySearchCriteria').popover({
		html: true,
		content: 'Click <i><b>Search</b></i> to refresh the results.',
		placement: 'right',
		trigger: 'manual'
	});
	
	$scope.executeSalarySearch = function () {
		$('#salarySearchCriteria').popover('hide');
		$scope.searchPopoverVisible = false;
		
		RestApiSalary.query($scope.filterCriteria).success(function (salaries) {
			$scope.salaries = salaries;
		});
	}
	
	$scope.$watch('filterCriteria', function (before, after) {
		if (before != after) {
			if (! $scope.searchPopoverVisible) {
				$('#salarySearchCriteria').popover('show');
				$scope.searchPopoverVisible = true;
			}
		}
	}, true);
	
}]);
