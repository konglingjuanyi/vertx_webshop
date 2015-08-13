/**
 * 
 */

function ShopController($scope, Products){
	$scope.products = Products.query();
}