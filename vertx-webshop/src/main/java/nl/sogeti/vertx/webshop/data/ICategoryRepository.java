package nl.sogeti.vertx.webshop.data;

import java.util.List;

import io.vertx.core.Handler;
import nl.sogeti.vertx.webshop.model.Category;

public interface ICategoryRepository {
	void getCategories(Handler<List<Category>> handler);
}
