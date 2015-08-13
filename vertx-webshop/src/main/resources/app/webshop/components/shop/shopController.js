/**
 * 
 */

function ShopController($scope, Products, Categories){
	$scope.products = Products.query();
    $scope.categories = Categories.query();
}