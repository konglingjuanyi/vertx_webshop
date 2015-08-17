/**
 * 
 */

function ShopController($scope, Products, Categories){
	$scope.products = Products.query();
    $scope.categories = Categories.query();
    
    $scope.getProducts = function(categoryName){
        $scope.products = Products.query({'categoryName': categoryName})
        
    }
}