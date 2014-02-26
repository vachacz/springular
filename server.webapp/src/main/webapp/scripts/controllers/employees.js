var SpringularEmployee = angular.module('module.employeeAdministration', [ 'springular.rest', 'ui.bootstrap' ]);

SpringularEmployee.controller('controller.employees', ['$scope', '$modal', '$location', '$http', 'RestApiEmployee', 'filterFilter', function ($scope, $modal, $location, $http, RestApiEmployee, filterFilter) {

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
        	$scope.employeesFilteredList = 
        		filterFilter($scope.employees, { 
        			login:     $scope.filterCriteria.login, 
        			firstName: $scope.filterCriteria.firstName,
        			lastName:  $scope.filterCriteria.lastName 
        		});
        	$scope.employeesTotal = $scope.employeesFilteredList.length;  
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
	
	$scope.sortClass = function(sortAttributeName) {
		if ($scope.employeeOrderPredicate == sortAttributeName) {
			return $scope.employeeOrderReverse == false ? 'sort-false' : 'sort-true';
		}
		return '';
	}
	
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