var SpringularEmployee = angular.module('module.employeeAdministration', [ 'springular.rest', 'ui.bootstrap' ]);

SpringularEmployee.controller('controller.employees', ['$scope', '$modal', '$location', 'RestApiEmployee', 'filterFilter', function ($scope, $modal, $location, RestApiEmployee, filterFilter) {

	$scope.employeeOrderReverse = false; 
	$scope.employeeOrderPredicate = '';
	
	$scope.employeesPage = 1;
	$scope.employeesPerPage = 10;
	$scope.employeesFilteredList = [];
	
	$scope.filterCriteria = { login : "", firstName : "", lastName : "" };
	
	RestApiEmployee.query({}, function (employees) {
        $scope.employees = employees;
        $scope.employeesFilteredList = employees;
        $scope.employeesTotal = employees.length;
        
        $scope.$watch('filterCriteria', function(newCriteria) {  
        	$scope.filterEmployees();
        }, true)
    });
	
	$scope.sortEmployees = function(sortAttributeName) {
		if (sortAttributeName != $scope.employeeOrderPredicate) {
			$scope.employeeOrderReverse = false;
		} else {
			$scope.employeeOrderReverse = !$scope.employeeOrderReverse;
		}
		$scope.employeeOrderPredicate = sortAttributeName;
	}
	
	$scope.createEmployee = function() {
      $scope.openEmployeeModalWindow({ employee: new RestApiEmployee, reload: true });
	}
	
    $scope.editEmployee = function ($employee) {
      $scope.openEmployeeModalWindow({ employee: $employee, reload: false });
    };
    
    $scope.deleteEmployee = function (employee) {
    	employee.$delete(function() {
    		$scope.employees.splice( $scope.employees.indexOf(employee), 1 );
    		$scope.filterEmployees();
    	});
    }
    
    $scope.showSalaries = function (employee) {
    	$location.search({ firstName: employee.firstName, lastName: employee.lastName }).path( "/salary" );
    }
    
    $scope.filterEmployees = function() {
    	$scope.employeesFilteredList =
    		filterFilter($scope.employees, { 
    			login:     $scope.filterCriteria.login, 
    			firstName: $scope.filterCriteria.firstName,
    			lastName:  $scope.filterCriteria.lastName 
    		});
    	$scope.employeesTotal = $scope.employeesFilteredList.length;
    }
    
	$scope.sortClass = function(sortAttributeName) {
		if ($scope.employeeOrderPredicate == sortAttributeName) {
			return $scope.employeeOrderReverse == false ? 'sort-false' : 'sort-true';
		}
		return '';
	}
    
    $scope.openEmployeeModalWindow = function (params) {
      var modalInstance = $modal.open({
        templateUrl: 'views/employeeDetails.html',
        controller: 'controller.modal.employee',
        resolve: {
          params: function () {
            return params;
          }
        }
      });
    };
	
}]);

SpringularEmployee.controller('controller.modal.employee', ['$scope', '$route', '$modalInstance', '$modal', '$location', 'RestApiMasterdata', 'RestApiEmployee', 'params', function($scope, $route, $modalInstance, $modal, $location, RestApiMasterdata, RestApiEmployee, params) {

    $scope.employee = angular.copy(params.employee);
    $scope.employeeOrig = params.employee;
    $scope.errorMessages = [];
    
    RestApiMasterdata.getNationalities().success(function(result) {
    	$scope.nationalities = result;
    });
    
	$scope.saveEmployee = function() {
		$scope.employee.$save(function() {
			angular.copy($scope.employee, $scope.employeeOrig);
			$modalInstance.close();
			$location.path( "/employees" );
			if (params.reload) {
				$route.reload();
			}
		}, function(response) {
			$scope.errorMessages = response.data.messages;
		});
	};

    $scope.closeEmployee = function () {
    	$modalInstance.dismiss('cancel');
    };
}]);