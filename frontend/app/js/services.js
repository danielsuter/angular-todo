'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('angular-todo.services', []).
  value('version', '0.1');

var angularTodoServices = angular.module('angularTodoServices', ['ngResource']);

angularTodoServices.factory('Todo', ['$resource',
    function($resource) {
      return $resource('/angular-todo/rest/todo/:id', {}, {
        query: {method: 'GET', isArray: true},
        save:   {method:'POST'},
        'remove': {method:'DELETE'}
      });
    }]);

angularTodoServices.factory('User', ['$resource',
    function($resource) {
        return $resource('/angular-todo/rest/user/:id', {}, {
            query: {method: 'GET', isArray: true}
        });
    }]);