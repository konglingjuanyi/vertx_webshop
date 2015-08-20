package nl.sogeti.vertx.webshop.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Category;

public class MongoCategoryRepository implements ICategoryRepository {

	private final MongoClient mongo;
	private final String CATEGORY = "category";

	public MongoCategoryRepository(MongoClient mongo){
		this.mongo = mongo;
	}
	
	@Override
	public void getCategories(Handler<List<Category>> handler) {
		mongo.find(this.CATEGORY, new JsonObject(), result -> {
	        // error handling
	        if (result.failed()) {
	          //todo: error handling
	        }
	        List<Category> lookupResults = new ArrayList<Category>();
	        for (JsonObject o : result.result()) {
	        	Category category = new Gson().fromJson(o.toString(), Category.class);
	        	lookupResults.add(category);
	        }       
	        handler.handle(lookupResults);
		});	
	}
}
