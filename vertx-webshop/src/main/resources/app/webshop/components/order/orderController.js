function OrderController($scope, $location, Orders, ShoppingCart){
    $scope.shoppingCart = ShoppingCart;
    
    $scope.placeOrder = function(){
        var order = {};
        order.orderedProducts = ShoppingCart.products;
        Orders.save(order);
        ShoppingCart.clear();
        alert("Added order");
        $location.path('/');
    }
}