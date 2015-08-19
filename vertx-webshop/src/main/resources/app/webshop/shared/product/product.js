app.directive('cdProduct', ['ShoppingCart', '$modal', function(ShoppingCart, $modal){
    return {
        restrict: 'E',
        scope: {
            product: '=info'
        },
        templateUrl: 'app/webshop/shared/product/product.html',
        link: function(scope, element, attrs){
            scope.addProduct = function(product){
                ShoppingCart.addProduct(product);
                var modalInstance = $modal.open({
                    animation: true,
                    templateUrl: 'app/webshop/shared/product/productModal.html',
                    controller: 'ProductModalController', 
                    resolve: {
                         product: function(){
                            return scope.product;
                        }
                    }
                });
            }
        }
    };
}]);