'use strict';

/* Controllers */

var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('TodoListCtrl', ['$scope', 'Todo', 'User', function ($scope, Todo, User) {
    $scope.todos = Todo.query();
    $scope.users = User.query();
    $scope.editedTodo = null;

    $scope.isEmpty = function(string) {
        return string == undefined || string == '' || string == null;
    }

    $scope.delete = function(todoToRemove) {
        Todo.remove({ id:todoToRemove.id });
        $scope.todos.splice($scope.todos.indexOf(todoToRemove), 1);
    }

    $scope.markAsDone = function(todo) {
        todo.done = true;
        fixDeadlineDate(todo);
        todo.$save();
    }

    $scope.markAsUndone = function(todo) {
        todo.done = false;
        fixDeadlineDate(todo);
        todo.$save();
    }

    $scope.isDue = function(todo) {
        if(!todo.done) {
            var dueDate = todo.deadline;
            var increasedByOneDay = new Date(dueDate + (24 * 60 * 60 * 1000));
            return new Date() > increasedByOneDay;
        }
    }

    $scope.activateEditMode = function(todoId) {
        $scope.editedTodo = todoId;
    }

    $scope.isEditMode = function(todoId) {
        return $scope.editedTodo === todoId;
    }

    $scope.resetEditMode = function() {
        $scope.editedTodo = null;
    }

    // Workaround for select
    function fixUserReference(todo) {
        var index;
        for (index = 0; index < $scope.users.length; ++index) {
            var user = $scope.users[index];
            if(todo.assignee.name === user.name) {
                todo.assignee = user;
                console.log('fixed reference');
            }
        }
    }

    // Workaround, since date-picker fails to preserve value
    function fixDeadlineDate(todo) {
        if(!isNaN(todo.deadline)) {
            todo.deadline = new Date(todo.deadline);
        }
    }

    $scope.save = function(todo) {
        fixUserReference(todo);
        fixDeadlineDate(todo);

        todo.$save();
        $scope.resetEditMode();
    }

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate', 'dd.MM.yyyy'];
    $scope.format = $scope.formats[3];

    $scope.dateOptions = {
        'year-format': "'yy'",
        'starting-day': 1
    };
}]);

todoControllers.controller('TodoDetailCtrl', ['$scope', 'Todo', '$location', '$window', 'User', function ($scope, Todo, $location, $window, User) {
    $scope.todo = new Todo();
    $scope.todo.done = false;
    $scope.users = User.query();

    $window.document.getElementById('description').focus();

    $scope.addTodoAndGotoOverview = function() {
        $scope.todo.$save();
        $location.path('/');
    };

    $scope.addTodo = function() {
        $scope.todo.$save();
        $scope.todo = new Todo();
        $window.document.getElementById('description').focus();
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



