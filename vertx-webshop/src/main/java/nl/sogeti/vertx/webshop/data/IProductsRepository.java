package nl.sogeti.vertx.webshop.data;

import java.util.List;

import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public interface IProductsRepository {
	List<Product> getProducts();
	List<Category> getCategories();
	List<Product> getProducts(String categoryName);
}
