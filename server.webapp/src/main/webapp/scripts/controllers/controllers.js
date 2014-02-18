var SpringularUser = angular.module('module.userAdministration', [ 'springular.rest', 'ui.bootstrap' ]);

SpringularUser.controller('controller.users', ['$scope', '$modal', '$location', '$http', 'RestApiUser', function ($scope, $modal, $location, $http, RestApiUser) {

	$scope.usersPage = 1;
	$scope.usersPerPage = 10;
	$scope.usersPagedList = [];
	
	RestApiUser.query({}, function (users) {
        $scope.users = users;
        $scope.usersTotal = users.length;
        $scope.computePagedUsers();
    });
	
	$scope.usersPageSet = function (pageNumber) {
	    $scope.usersPage = pageNumber;
	    $scope.computePagedUsers();
	};
	
	$scope.computePagedUsers = function () {
		var begin = (($scope.usersPage - 1) * $scope.usersPerPage), 
	    end   = begin + $scope.usersPerPage;
	    
		$scope.usersPagedList = $scope.users.slice(begin, end);
    };
	
    $scope.editUser = function ($user) {
      var modalInstance = $modal.open({
        templateUrl: 'views/editUser.html',
        controller: 'controller.modal.user',
        resolve: {
          user: function () {
            return $user;
          }
        }
      });
    };
}]);

SpringularUser.controller('controller.modal.user', ['$scope', '$modalInstance', '$modal', '$location', 'RestApiMasterdata', 'RestApiUser', 'user', function($scope, $modalInstance, $modal, $location, RestApiMasterdata, RestApiUser, user) {

    $scope.user = user;
    $scope.errorMessages = [];
    
    RestApiMasterdata.getNationalities().success(function(result) {
    	$scope.nationalities = result;
    });
    
	$scope.saveUser = function($user) {
		$user.$save(function() {
			$modalInstance.close();
			$location.path( "/users" );
		}, function(errors) {
			$scope.errorMessages = errors.data.messages;
		});
	};

    $scope.closeUser = function () {
    	$modalInstance.dismiss('cancel');
    };
}]);