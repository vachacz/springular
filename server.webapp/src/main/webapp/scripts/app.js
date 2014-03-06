'use strict';

var SpringularApp = angular.module('springular.app', [
   'springular.rest',
   'module.security',
   'module.salary',
   'module.employeeAdministration',
   'ngRoute',
   'ngAnimate',
   ])
.factory('authHttpResponseInterceptor',['$q','$location', '$injector', function($q, $location, $injector) {
   return {
	   responseError: function(rejection) {
		   if (rejection.status === 401) {
			   $location.path('/notAuthenticated');
			   $injector.get('AuthService').markAsSignedOut();
		   }
		   return $q.reject(rejection);
	   }
   }
}])
.config(function ($routeProvider, $httpProvider) {
   $routeProvider.when('/employees',        {templateUrl: 'views/employees.html', controller: 'controller.employees'});
   $routeProvider.when('/todo',             {templateUrl: 'views/todo.html'});
   $routeProvider.when('/salary',           {templateUrl: 'views/salary.html', controller: 'controller.salary'});
   $routeProvider.when('/notAuthenticated', {templateUrl: 'views/notauthenticated.html'});
   
   $httpProvider.interceptors.push('authHttpResponseInterceptor');
})
.run(function ($rootScope, AuthService) {
    $rootScope.authService = AuthService;
});

var SpringularRest = angular.module('springular.rest', ['ngResource', 'ngCookies']);