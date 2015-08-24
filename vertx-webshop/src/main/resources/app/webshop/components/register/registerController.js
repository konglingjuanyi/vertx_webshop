function RegisterController ($scope, $modal, Users){
    $scope.user = {};
    $scope.available = false;
    $scope.checkAvailable = function(){
        Users.get({ username: $scope.user.userName }, function(){
            $scope.available = false;
        }, function(result){
           $scope.available = true;
        });
        
    };
    
    $scope.saveUser = function(){
        if($scope.available){
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
}