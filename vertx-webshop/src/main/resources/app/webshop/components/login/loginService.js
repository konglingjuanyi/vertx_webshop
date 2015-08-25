app.factory('Login', function(){
    return {
        user: null,
        
        getUser: function(){
            return this.user;
        },
        
        setUser: function(user){
            this.user = user;
        }
    }
});