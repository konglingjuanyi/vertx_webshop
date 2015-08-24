app.controller('ProductModalController', function ($scope, $modalInstance, $location, product, ShoppingCart) {
    $scope.product = product;
    $scope.shoppingCart = ShoppingCart;
    
    $scope.order = function(){
        $location.path('/order');
        $modalInstance.close();
    }
    
    $scope.ok = function () {
        $modalInstance.close();
    };
    
    //Removes the added product from the cartr
    $scope.cancel = function () {
        ShoppingCart.remove($scope.product);
        $modalInstance.dismiss('cancel');
    };
});