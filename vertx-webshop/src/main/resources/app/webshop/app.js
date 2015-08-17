var app = angular.module('WebShop', ['ngRoute', 'ngResource', 'ui.bootstrap']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
    	when('/shop',{ 
    		templateUrl: 'app/webshop/components/shop/shopView.html',
    		controller: ShopController
    	}).
    	otherwise({redirectTo: '/shop'});
}]);

app.controller('DropdownCtrl', function ($scope, $log) {
  $scope.status = {
    isopen: false
  };
});