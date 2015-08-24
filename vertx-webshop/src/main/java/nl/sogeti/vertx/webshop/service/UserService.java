package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

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
	
	public void addUser(RoutingContext rc){
		User user = new Gson().fromJson(rc.getBodyAsJson().toString(), User.class);
		repository.addUser(result ->{
			if(result != null){
				rc.response().setStatusCode(200).end();
			}
			else{
				//This is a result of the username not being unique, return 409:CONFLICT
				rc.response().setStatusCode(409);
			}
		}, user);
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
