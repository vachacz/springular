'use strict';

var SpringularApp = angular.module('springular.app', [
   'springular.rest',
   'module.main',
   'module.userAdministration',
   'ngRoute',
   'ngAnimate',
   ])
.config(function ($routeProvider, $httpProvider) {
   $routeProvider.when('/users', {templateUrl: 'views/users.html', controller: 'controller.users'});
   $routeProvider.when('/todo', {templateUrl: 'views/todo.html'});
});

var SpringularRest = angular.module('springular.rest', ['ngResource']);