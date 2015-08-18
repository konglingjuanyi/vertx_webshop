package nl.sogeti.vertx.webshop.data;

import nl.sogeti.vertx.webshop.model.Order;

public interface IOrderRepository {
	void addOrder(Order order);
}
