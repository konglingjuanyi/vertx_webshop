function OrderController($scope, $location, $modal, Orders, ShoppingCart){
    $scope.shoppingCart = ShoppingCart;
    
    $scope.placeOrder = function(){
        var order = {};
        order.orderedProducts = ShoppingCart.products;
        order.total = ShoppingCart.total;
        Orders.save(order, function(){
                ShoppingCart.clear();
                var modalInstance = $modal.open({
                    animation: true,
                    templateUrl: 'app/webshop/components/order/orderModal.html',
                    controller: 'OrderModalController', 
                });
            }, function(result){
                //Error handling
            }
        );
              
    }
}