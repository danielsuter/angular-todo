'use strict';

/* Filters */

angular.module('angular-todo.filters', []).
    filter('interpolate', ['version', function (version) {
        return function (text) {
            return String(text).replace(/\%VERSION\%/mg, version);
        }
    }]).filter('ja_nein', function () {
        return function(flag) {
            if (flag) {
                return "Ja";
            } else {
                return "Nein";
            }
        }
    });
