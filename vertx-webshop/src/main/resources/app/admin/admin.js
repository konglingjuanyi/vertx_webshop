var app = angular.module('Admin', ['ngRoute', 'ngAnimate', 'ngResource', 'ui.bootstrap']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
       otherwise({redirectTo: '/'});
}]);