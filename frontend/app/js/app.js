'use strict';

// Declare app level module which depends on filters, and services
angular.module('angular-todo', [
        'ngRoute',
        'angular-todo.filters',
        'angular-todo.services',
        'angular-todo.directives',
        'todoControllers',
        'angularTodoServices'
    ])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/', {templateUrl: 'partials/overview.html', controller: 'TodoListCtrl'});
        $routeProvider.when('/add-todo', {templateUrl: 'partials/add-todo.html', controller: 'TodoDetailCtrl'});

        $routeProvider.otherwise({redirectTo: '/'});
    }]);
