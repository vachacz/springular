var services = angular.module('angular.rest', ['ngResource']);

services.factory('UsersFactory', function ($resource) {
    return $resource('http://localhost:8081/angular/users', {}, {
    	get: { method: 'GET', isArray: true },
        query: { method: 'GET', isArray: true }
    });
});

services.factory('UserFactory', function ($resource) {
    return $resource('http://localhost:8081/angular/user', {}, {
    	update: { method: 'POST' }
    });
});

var app = angular.module('modul.userAdministration', [ 'angular.rest', 'ui.bootstrap' ]);

app.controller('controller.users', ['$scope', '$modal', '$location', '$http', 'UsersFactory', 'UserFactory', function ($scope, $modal, $location, $http, UsersFactory, UserFactory) {

	$scope.usersPage = 1;
	$scope.usersPerPage = 10;
	$scope.usersPagedList = [];
	
	UsersFactory.get({}, function (users) {
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

app.controller('controller.modal.user', ['$scope', '$modalInstance', '$modal', '$location', '$http', 'UserFactory', 'user', function($scope, $modalInstance, $modal, $location, $http, UserFactory, user) {

    $scope.user = user;
    $scope.errorMessages = [];
    
    $http.get('http://localhost:8081/angular/masterdata/nationalities').
	  success(function(data) {
	    $scope.nationalities = data;
	  });
    
	$scope.saveUser = function($user) {
		UserFactory.update($user, 
			function() {
			    $scope.errorMessages = [];
				$modalInstance.close();
				$location.path( "/users" );
			}, 
			function(errors) {
				$scope.errorMessages = errors.data.messages;
			} 
		);
	};

    $scope.closeUser = function () {
    	$modalInstance.dismiss('cancel');
    };
}]);