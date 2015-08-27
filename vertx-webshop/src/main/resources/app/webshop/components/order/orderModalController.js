app.controller('OrderModalController', function ($scope, $modalInstance, $location) {
       
    $scope.ok = function () {
        $modalInstance.close();
        $location.path('/shop');
    };
});