package nl.sogeti.vertx.webshop.data;

import io.vertx.core.Handler;
import nl.sogeti.vertx.webshop.model.Order;

public interface IOrderRepository {
	void addOrder(Handler<String> handler, Order order);
}
