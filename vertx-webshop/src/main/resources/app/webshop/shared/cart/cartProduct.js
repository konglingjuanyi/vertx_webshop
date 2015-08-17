app.directive('cdCartProduct', function(){
    return {
        restrict: 'E',
        scope: {
            cartProduct: '=info'
        },
        templateUrl: 'app/webshop/shared/cart/cartProduct.html'
    };
});