'use strict';

/* Controllers */

var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('TodoListCtrl', ['$scope', 'Todo', function ($scope, Todo) {
    $scope.todos = Todo.query();
}]);

todoControllers.controller('TodoDetailCtrl', ['$scope', 'Todo', '$location', function ($scope, Todo, $location) {
    $scope.todo = new Todo();
    $scope.todo.done = false;

    $scope.addTodo = function() {
        $scope.todo.$save();
        $location.path('/');
    };

    $scope.cancel = function() {
        $scope.todo = {};
        $location.path('/');
    };

    $scope.setDone = function(isDone) {
        $scope.todo.done = isDone;
    };


    // Date picker
    $scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate', 'dd.MM.yyyy'];
    $scope.format = $scope.formats[3];

    $scope.dateOptions = {
        'year-format': "'yy'",
        'starting-day': 1
    };
}]);



