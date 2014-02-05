'use strict';

/* Controllers */

var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('TodoListCtrl', ['$scope', '$http', function ($scope, $http) {
    $http.get('data/todos.json').success(function(data) {
        $scope.todos = data;
    });
}]);

todoControllers.controller('TodoDetailCtrl', ['$scope', '$http', function ($scope, $http) {

}]);

