package nl.sogeti.vertx.webshop.data;

import java.util.List;

import com.google.gson.Gson;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.User;
import nl.sogeti.vertx.webshop.util.JsonConverter;
import nl.sogeti.vertx.webshop.util.MongoClientProvider;

public class MongoUserRepository implements IUserRepository {
	private final MongoClient mongo;
	private final String USER = "user";
	
	public MongoUserRepository() {
		this.mongo = MongoClientProvider.getClient();
	}
	
	@Override
	public void addUser(Handler<String> handler, User user) {
		String json = new Gson().toJson(user);
		JsonObject document = new JsonObject(json);
		
		mongo.insert(USER, document, res -> {
			//TODO
			//Check if the username already exists
			
			if (res.succeeded()) {
			   handler.handle(res.result());
			} 
			else {
				res.cause().printStackTrace();
			}
		});		
	}

	@Override
	public void findUser(Handler<User> handler, String userName) {
		findUsers(result -> {
				User user;
				if(result.size() == 0){
					user = null;
				}
				else{
					//Should only be one with this name
					user = result.get(0);
				}
				handler.handle(user);
			},
			new JsonObject().put("userName", userName));		
	}
	
	private void findUsers(Handler<List<User>> handler, JsonObject query){
		mongo.find(USER, query, result -> {
			if(result.failed()){
				//error handling
			}
			List<User> lookupResults = JsonConverter.fromJsonList(result.result(), User.class);
	        handler.handle(lookupResults);
		});
	}

}
