app.directive('cdProduct', ['ShoppingCart', function(ShoppingCart){
    return {
        restrict: 'E',
        scope: {
            product: '=info'
        },
        templateUrl: 'app/webshop/shared/product/product.html',
        link: function(scope, element, attrs){
            scope.addProduct = function(product){
                ShoppingCart.addProduct(product);
            }
        }
    };
}]);