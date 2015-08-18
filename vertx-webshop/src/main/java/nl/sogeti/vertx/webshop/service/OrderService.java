package nl.sogeti.vertx.webshop.service;

import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.data.IOrderRepository;
import nl.sogeti.vertx.webshop.data.MongoOrderRepository;

public class OrderService {
	private IOrderRepository repository;

	public OrderService(MongoClient mongo){
		repository = new MongoOrderRepository(mongo);
	}
}
