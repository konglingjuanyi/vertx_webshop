package nl.sogeti.vertx.webshop.data;

import java.util.List;

import io.vertx.core.Handler;
import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public interface IProductsRepository {
	void getProducts(Handler<List<Product>> handler);
	void getProducts(Handler<List<Product>> handler, String categoryName);
}
