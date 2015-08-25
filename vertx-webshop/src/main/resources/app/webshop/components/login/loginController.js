function LoginController ($scope, $location, Users, Login){
    $scope.username;
    $scope.password;
    $scope.loginSucceeded = false;
    $scope.login = function(){
        var user = Users.logIn({ username: $scope.username, password: $scope.password }, function(){
            //Success. Set the user in the login service
            Login.setUser(user);
            $scope.loginSucceeded = true;
            $location.path('/shop');
        }, function(result){
            if(result.status === 400 || result.status === 401){
                $scope.loginSucceeded = false;
                reset();
            }
        });
    }
    
    var reset = function(){
        $scope.username = null;
        $scope.password = null;
    }
}


