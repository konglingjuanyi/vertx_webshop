function RegisterController ($scope, $modal, Users){
    $scope.user = { userName: null };
    $scope.available = false;
    $scope.checkAvailable = function(){
        if($scope.user.userName !== null && $scope.user.userName.length >= 3){
            Users.get({ username: $scope.user.userName }, function(){
                $scope.available = false;
            }, function(result){
               $scope.available = true;
            });
        }
    };
    
    $scope.saveUser = function(){
        if($scope.available){
            Users.save($scope.user, 
                function(){
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
            );
        }
    }
}