app.directive('cdCart', ['ShoppingCart', function(ShoppingCart){
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