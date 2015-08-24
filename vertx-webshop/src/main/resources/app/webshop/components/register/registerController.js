function RegisterController ($scope, $modal, Users){
    $scope.user = {};
    $scope.saveUser = function(){
        Users.save($scope.user);
        //handle result codes, make session
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'app/webshop/components/register/registerModal.html',
            controller: 'RegisterModalController', 
            resolve: {
                 user: function(){
                    return $scope.user;
                }
            }
        });
    }
}