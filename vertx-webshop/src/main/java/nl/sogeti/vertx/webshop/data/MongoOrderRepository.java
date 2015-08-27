package nl.sogeti.vertx.webshop.data;

import com.google.gson.Gson;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Order;
import nl.sogeti.vertx.webshop.util.MongoClientProvider;

public class MongoOrderRepository implements IOrderRepository {
	private final String ORDER = "order";
	private final MongoClient mongo;
	
	public MongoOrderRepository() {
		this.mongo = MongoClientProvider.getClient();
	}
	
	@Override
	public void addOrder(Handler<String> handler, Order order) {
		String json = new Gson().toJson(order);
		JsonObject document = new JsonObject(json);

		mongo.insert(ORDER, document, res -> {
		  if (res.succeeded()) {
		    handler.handle(res.result());
		  } else {
		    res.cause().printStackTrace();
		  }
		});
	}

}
