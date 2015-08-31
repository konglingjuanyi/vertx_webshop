function ProductController($scope, Products){
    var refresh = function(){
        $scope.products = Products.query();
    }
    
    refresh();
    
    $scope.delete = function(id){
        Products.delete({id:id});
        refresh();
    }      
}