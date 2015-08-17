app.factory('ShoppingCart', function(){
    return {
        cartProducts: [],
        
        addProduct: function(product){
            //Check if the product is already in the cart
            var productExists = false;
            if(this.cartProducts.indexOf(product) > -1){
                productExists = true;
            }
            //If it is not, add it
            if(!productExists){
                this.cartProducts.push(product);
            }
        },
        
        removeProduct: function(index){
            //TODO
        }
    }
});