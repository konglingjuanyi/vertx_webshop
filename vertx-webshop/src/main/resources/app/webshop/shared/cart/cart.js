app.directive('cdCart', ['ShoppingCart', 'Orders', function(ShoppingCart, Orders){
    return {
        restrict: 'E',
        scope: {
        },
        templateUrl: 'app/webshop/shared/cart/cart.html',
        link: function(scope, element, attrs){
            scope.shoppingCart = ShoppingCart;
        }
    };
}]);