var SpringularEmployee = angular.module('module.employeeAdministration', [ 'springular.rest', 'ui.bootstrap' ]);

SpringularEmployee.controller('controller.employees', ['$scope', '$modal', '$location', '$http', 'RestApiEmployee', function ($scope, $modal, $location, $http, RestApiEmployee) {

	$scope.employeesPage = 1;
	$scope.employeesPerPage = 10;
	$scope.employeesPagedList = [];
	
	RestApiEmployee.query({}, function (employees) {
        $scope.employees = employees;
        $scope.employeesTotal = employees.length;
        $scope.computePagedEmployees();
    });
	
	$scope.employeesPageSet = function (pageNumber) {
	    $scope.employeesPage = pageNumber;
	    $scope.computePagedEmployees();
	};
	
	$scope.computePagedEmployees = function () {
		var begin = (($scope.employeesPage - 1) * $scope.employeesPerPage), 
	    end   = begin + $scope.employeesPerPage;
	    
		$scope.employeesPagedList = $scope.employees.slice(begin, end);
    };
	
    $scope.editEmployee = function ($employee) {
      var modalInstance = $modal.open({
        templateUrl: 'views/employeeEdit.html',
        controller: 'controller.modal.employee',
        resolve: {
          employee: function () {
            return $employee;
          }
        }
      });
    };
    
    $scope.deleteEmployee = function (employee) {
    	employee.$delete(function() {
    		$scope.employees.splice( $scope.employees.indexOf(employee), 1 );
			$scope.employeesTotal--;
			$scope.computePagedEmployees();
    	});
    }
    
}]);

SpringularEmployee.controller('controller.modal.employee', ['$scope', '$modalInstance', '$modal', '$location', 'RestApiMasterdata', 'RestApiEmployee', 'employee', function($scope, $modalInstance, $modal, $location, RestApiMasterdata, RestApiEmployee, employee) {

    $scope.employee = employee;
    $scope.errorMessages = [];
    
    RestApiMasterdata.getNationalities().success(function(result) {
    	$scope.nationalities = result;
    });
    
	$scope.saveEmployee = function($employee) {
		$employee.$save(function() {
			$modalInstance.close();
			$location.path( "/employees" );
		}, function(response) {
			$scope.errorMessages = response.data.messages;
		});
	};

    $scope.closeEmployee = function () {
    	$modalInstance.dismiss('cancel');
    };
}]);