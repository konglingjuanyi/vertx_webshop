var app = angular.module('WebShop', ['ngRoute', 'ngAnimate', 'ngResource', 'ui.bootstrap']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/register', {
            templateUrl: 'app/webshop/components/register/registerView.html',
            controller: RegisterController
        }).
        when('/login', {
            templateUrl: 'app/webshop/components/login/loginView.html',
            controller: LoginController
        }).     
        when('/order', {
            templateUrl: 'app/webshop/components/order/orderView.html',
    		controller: OrderController
        }).
        when('/shop',{ 
    		templateUrl: 'app/webshop/components/shop/shopView.html',
    		controller: ShopController
    	}).
    	otherwise({redirectTo: '/shop'});
}]);

app.controller('NavbarCtrl', function ($scope, ShoppingCart, Login) {
    $scope.login = Login;
    $scope.status = {
        isopen: false
    };
});