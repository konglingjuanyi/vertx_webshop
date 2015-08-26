package nl.sogeti.vertx.webshop.data;

import java.util.List;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.util.JsonConverter;
import nl.sogeti.vertx.webshop.util.MongoClientProvider;

public class MongoCategoryRepository implements ICategoryRepository {

	private final MongoClient mongo;
	private final String CATEGORY = "category";

	public MongoCategoryRepository( ){
		this.mongo = MongoClientProvider.getClient();
	}
	
	@Override
	public void getCategories(Handler<List<Category>> handler) {
		mongo.find(this.CATEGORY, new JsonObject(), result -> {
	        // error handling
	        if (result.failed()) {
	          //todo: error handling
	        }
	        List<Category> lookupResults = JsonConverter.fromJsonList(result.result(), Category.class);
	        handler.handle(lookupResults);
		});	
	}
}
