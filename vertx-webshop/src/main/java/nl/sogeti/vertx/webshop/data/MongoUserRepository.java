package nl.sogeti.vertx.webshop.data;

import com.google.gson.Gson;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.User;

public class MongoUserRepository implements IUserRepository {
	private final MongoClient mongo;
	private final String USER = "user";
	
	public MongoUserRepository(MongoClient mongo ) {
		this.mongo = mongo;
	}
	
	@Override
	public void addUser(User user) {
		String json = new Gson().toJson(user);
		JsonObject document = new JsonObject(json);
		mongo.insert(USER, document, res -> {
			if (res.succeeded()) {
			    String id = res.result();
			    System.out.println("Inserted order with id " + id);
			} 
			else {
				res.cause().printStackTrace();
			}
		});		
	}

}
