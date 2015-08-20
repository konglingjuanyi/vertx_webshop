app.factory('ShoppingCart', function(){
    return {
        products: [],

        total: 0,

        addProduct: function(theProduct){
            //Check if the product is already in the cart, if it is the amount is increased
            var productExists = false;
            var cartProduct;
            var index = this.findProduct(theProduct);
            //If the cart item does not exist, add it, and add it to the collection
            if(index === -1){
                cartProduct = {
                    product: theProduct,
                    amount: 1
                };
                this.products.push(cartProduct);   
            }
            //If it does exist, only increase the amount
            else{
                cartProduct = this.products[index];
                cartProduct.amount++;
            }
            //Calculate the cartproduct total
            this.calculateCartProductTotal(cartProduct);
        },

        removeProduct: function(index){
            this.products.splice(index, 1);
            this.calculateTotal();
        },
        
        calculateCartProductTotal: function(product){
            product.total = product.product.price * product.amount;
            this.calculateTotal();
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
        },
        
        findProduct: function(product){
            var index = -1;
            for(i = 0; i < this.products.length; i++){
              if(this.products[i].product === product){
                index = i;
                break;  
              }
            }
            return index;
        }
    }
});
