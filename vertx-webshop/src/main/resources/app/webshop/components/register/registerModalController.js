app.controller('RegisterModalController', function ($scope, $modalInstance, $location, user) {
    $scope.user = user;
    $scope.ok = function () {
        $modalInstance.close();
        $location.path('/shop');
    };
   
});