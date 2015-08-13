app.directive('cdProduct', function(){
    return {
        restrict: 'E',
        scope: {
            product: '=info'
        },
        templateUrl: 'app/webshop/shared/product/product.html'
    };
});