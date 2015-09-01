function ProductController($scope, Products, Categories, $location){
   
    
    $scope.selectedCategory = {};
    $scope.categories = Categories.query();
    
    var refresh = function(){
        $scope.products = Products.query();
        console.log($scope.selectedProduct);
        $scope.product = {};
    }
    
    refresh();
    
    $scope.delete = function(id){
        var confirmed = confirm("Do you want to delete this product?");
        if(confirmed == true){
            Products.delete({id:id}, function(){
                refresh();
            });
        }
    } 
    
    $scope.save = function(){
        $scope.product.category = {"name": $scope.selectedCategory.name};
        Products.save($scope.product, function(){
            $location.path('/products');
            refresh();
        });
    }
}