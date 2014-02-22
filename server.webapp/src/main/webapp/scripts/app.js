'use strict';

var SpringularApp = angular.module('springular.app', [
   'springular.rest',
   'module.main',
   'module.salary',
   'module.employeeAdministration',
   'ngRoute',
   'ngAnimate',
   ])
.config(function ($routeProvider, $httpProvider) {
   $routeProvider.when('/employees', {templateUrl: 'views/employees.html', controller: 'controller.employees'});
   $routeProvider.when('/todo',      {templateUrl: 'views/todo.html'});
   $routeProvider.when('/salary',    {templateUrl: 'views/salary.html', controller: 'controller.salary'});
})
.run(function ($rootScope, AuthService) {
    $rootScope.authService = AuthService;
});

var SpringularRest = angular.module('springular.rest', ['ngResource', 'ngCookies']);