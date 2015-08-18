app.factory('ShoppingCart', function(){
    return {
        cartProducts: [],
        
        cartTotal: 0,
        
        addProduct: function(product){
            //Check if the product is already in the cart
            var productExists = false;
            if(this.cartProducts.indexOf(product) > -1){
                productExists = true;
            }
            //If it is not, add it, and add it to the total
            if(!productExists){
                this.cartProducts.push(product);
                this.cartTotal += product.price;
            }
        },
        
        removeProduct: function(index){
            //TODO 
            //Remove the item
            //Subtract the item's costs
        }
    }
});