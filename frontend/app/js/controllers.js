'use strict';

/* Controllers */

var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('TodoListCtrl', ['$scope', 'Todo', function ($scope, Todo) {
    $scope.todos = Todo.query();
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

