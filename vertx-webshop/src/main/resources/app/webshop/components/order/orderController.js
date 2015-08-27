function OrderController($scope, $location, $modal, Orders, ShoppingCart, Login){
    $scope.shoppingCart = ShoppingCart;
    $scope.order = {};
    $scope.user = Login.getUser();
    if($scope.user !== null){
        $scope.order.shippingAddress = $scope.user.address;
        $scope.order.shippingCity = $scope.user.city;
    }
    
    $scope.placeOrder = function(){
        $scope.order.orderedProducts = ShoppingCart.products;
        $scope.order.total = ShoppingCart.total;
        Orders.save($scope.order, function(){
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