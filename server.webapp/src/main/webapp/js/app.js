'use strict';

angular.module('angular.app', [
   'angular.rest',
   'modul.userAdministration',
   'ngRoute',
   'ngAnimate'
   ])
.config(function ($routeProvider, $httpProvider) {
   $routeProvider.when('/users', {templateUrl: 'views/users.html', controller: 'controller.users'});
});