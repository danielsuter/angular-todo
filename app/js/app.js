'use strict';

// Declare app level module which depends on filters, and services
angular.module('angular-todo', [
  'ngRoute',
  'angular-todo.filters',
  'angular-todo.services',
  'angular-todo.directives',
  'todoControllers'
]);
//config(['$routeProvider', function($routeProvider) {
//  $routeProvider.when('/', {templateUrl: 'partials/overview.html', controller: 'TodoListCtrl'});
//  $routeProvider.otherwise({redirectTo: '/'});
//}]);
