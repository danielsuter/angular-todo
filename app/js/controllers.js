'use strict';

/* Controllers */

var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('TodoListCtrl', ['$scope', '$http', function ($scope, $http) {
    $http.get('data/todos.json').success(function(data) {
        $scope.todos = data;
    });
}]);

todoControllers.controller('TodoDetailCtrl', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.todo = {};
    $scope.todo.done = false;

    $scope.addTodo = function() {
        console.log($scope.todo);
    };

    $scope.cancel = function() {
        $scope.todo = {};
        $location.path('/');
    };

    $scope.setDone = function(isDone) {
        $scope.todo.done = isDone;
    };
}]);

