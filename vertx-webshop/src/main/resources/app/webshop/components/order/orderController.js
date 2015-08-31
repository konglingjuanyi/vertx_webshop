function OrderController($scope, $location, $modal, Orders, ShoppingCart, Login){
    $scope.shoppingCart = ShoppingCart;
    $scope.order = {};
    $scope.user = Login.getUser();
    if($scope.user !== null){
        $scope.order.shippingAddress = $scope.user.address;
        $scope.order.shippingCity = $scope.user.city;
    }
    
    $scope.placeOrder = function(){
        var orderedProducts = [];
        //Only pass the product id's
        for(index = 0; index < ShoppingCart.products.length; index++){
            var orderedProduct = {};
            orderedProduct.amount = ShoppingCart.products[index].amount;
            orderedProduct.productId = ShoppingCart.products[index].product._id;
            orderedProducts.push(orderedProduct);
        }
        $scope.order.orderedProducts = orderedProducts;
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