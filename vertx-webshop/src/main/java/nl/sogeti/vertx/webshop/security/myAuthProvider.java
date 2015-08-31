package nl.sogeti.vertx.webshop.security;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import nl.sogeti.vertx.webshop.data.MongoUserRepository;

public class MyAuthProvider implements AuthProvider {
	private final MongoUserRepository repository = new MongoUserRepository();
	
	@Override
	public void authenticate(JsonObject userData, Handler<AsyncResult<User>> userLookupHandler) {
		String userName = userData.getString("username");
		String password = userData.getString("password");
		
		repository.findUser(user -> {
			userLookupHandler.handle(new AsyncResult<User>() {
				private boolean succeeded = false;
				@Override
				public boolean succeeded() {
					if(user != null && (user.getPassword().equals(password))){
						return true;
					}
					return false;
				}
				
				@Override
				public User result() {
					if(!succeeded()){
						return null;
					}
					else{
						return user;
					}
				}

				@Override
				public boolean failed() {
					return !succeeded;
				}
				
				@Override
				public Throwable cause() {
					// TODO Auto-generated method stub
					return null;
				}
			});
		}, userName);
	}

}
