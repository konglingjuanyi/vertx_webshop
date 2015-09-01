var app = angular.module('Admin', ['ngRoute', 'ngAnimate', 'ngResource', 'ui.bootstrap']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/products', {
            templateUrl: 'app/admin/components/product/productView.html',
            controller: ProductController
        }).
        when('/newproduct', {
            templateUrl: 'app/admin/components/product/productCreationView.html',
            controller: ProductController
        }).
        otherwise({redirectTo: '/products'});
}]);