app.factory('ShoppingCart', function(){
    return {
        products: [],

        total: 0,

        addProduct: function(product){
            //Check if the product is already in the cart, if it is the amount is increased
            var productExists = false;
            var cartProduct = {};
            for(index = 0; index < this.products.length; index++){
              if(this.products[index].product === product){
                productExists = true;
                cartProduct = this.products[index]; 
                cartProduct.amount++;
                break;
              }
            }
            //If the cart item does not exist, add it, and add it to the collection
            if(!productExists){
                cartProduct.product = product;
                cartProduct.amount = 1;
                this.products.push(cartProduct);   
            }
            cartProduct.total = cartProduct.amount * cartProduct.product.price;
            //Update the total cart value
            this.calculateTotal();
        },

        removeProduct: function(index){
            //TODO
            //Remove the item
            //Subtract the item's costs
        },
        
        calculateTotal: function(){
            var total = 0;
            for(index = 0; index < this.products.length; index++){
                total += this.products[index].total;
            }
            this.total = total;
        },
        
        clear: function(){
            this.total = 0;
            this.products = [];
        }
    }
});
