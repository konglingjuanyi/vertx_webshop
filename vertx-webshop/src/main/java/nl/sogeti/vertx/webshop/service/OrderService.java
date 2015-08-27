package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IOrderRepository;
import nl.sogeti.vertx.webshop.data.MongoOrderRepository;
import nl.sogeti.vertx.webshop.model.Order;

public class OrderService {
	private IOrderRepository repository;

	public OrderService(){
		repository = new MongoOrderRepository();
	}
	
	public void addOrder(RoutingContext rc){
		Order order = new Gson().fromJson(rc.getBodyAsJson().toString(), Order.class);
		if(!order.isValid()){
			rc.response().setStatusCode(400).end();
			return;
		}
		repository.addOrder(result -> {
			//The operation returned an id and thus it succeeded
			if(result != null){
				rc.response().setStatusCode(200).end();
			}
		}, order);
	}
}
