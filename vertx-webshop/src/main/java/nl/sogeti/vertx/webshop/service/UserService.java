package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IUserRepository;
import nl.sogeti.vertx.webshop.data.MongoUserRepository;
import nl.sogeti.vertx.webshop.model.User;

public class UserService {
	private IUserRepository repository;
	
	public UserService(MongoClient mongo){
		repository = new MongoUserRepository(mongo);
	}
	
	public void logIn(RoutingContext rc){
		JsonObject json = rc.getBodyAsJson();
		String userName = json.getString("username");
		String password = json.getString("password");
		if(userName == null || userName.isEmpty() || password == null || userName.isEmpty()){
			rc.response().setStatusCode(400).end();
			return;
		}
		repository.findUser(res -> {
			if(res != null){
				if(res.getPassword().equals(password)){
					//Correctly authenticated, return user object
					rc.response().end(new Gson().toJson(res));
				}
				else{
					//Wrong password for an existing user, return error code
					rc.response().setStatusCode(401).end();
				}
			}
			else{
				//No user found with the supplied username
				rc.response().setStatusCode(400).end();
			}			
		}, userName);
	}
	
	public void addUser(RoutingContext rc){
		User user = new Gson().fromJson(rc.getBodyAsJson().toString(), User.class);
		repository.findUser(result -> {
			if(result == null){
				repository.addUser(addResult ->{
					if(addResult != null){
						rc.response().setStatusCode(200).end();
					}
					else{
						//This is a result of the username not being unique, return 409:CONFLICT
						rc.response().setStatusCode(409);
					}
				}, user);
			}
			else{
				rc.response().setStatusCode(409).end();
			}
		}, user.getUserName());
	}
	
	public void findUser(RoutingContext rc){
		String userName = rc.request().getParam("username");
		repository.findUser(result -> {
			if(result != null){
				rc.response().end(new Gson().toJson(result));
			}
			else{
				rc.response().setStatusCode(404).end();
			}
		}, userName);
	}
}
