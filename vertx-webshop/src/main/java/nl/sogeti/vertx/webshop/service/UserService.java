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
		repository.addUser(user);
	}
}
