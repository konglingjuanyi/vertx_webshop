package nl.sogeti.vertx.webshop.data;

import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Order;

public class MongoOrderRepository implements IOrderRepository {
	private final String ORDER = "ORDER";
	private final MongoClient mongo;
	
	public MongoOrderRepository(MongoClient mongo) {
		this.mongo = mongo;
	}
	
	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub

	}

}
