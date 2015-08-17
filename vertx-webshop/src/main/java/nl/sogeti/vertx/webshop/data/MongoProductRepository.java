package nl.sogeti.vertx.webshop.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public class MongoProductRepository implements IProductsRepository {
	private final MongoClient mongo;
	private final String CATEGORY = "category";
	private final String PRODUCT = "product";
	
	public MongoProductRepository(MongoClient mongo){
		this.mongo = mongo;
	}
	
	@Override
	public void getProducts(Handler<List<Product>> handler) {
		mongo.find(this.PRODUCT, new JsonObject(), result -> {
	        // error handling
	        if (result.failed()) {
	          //todo: error handling
	        }
	        List<Product> lookupResults = new ArrayList<Product>();
	        for (JsonObject o : result.result()) {
	          Product product = new Gson().fromJson(o.toString(), Product.class);
	          lookupResults.add(product);
	        }       
	        handler.handle(lookupResults);
		});		
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

	@Override
	public void getProducts(Handler<List<Product>> handler, String categoryName) {
		JsonObject query = new JsonObject("{\"category\": {\"name\": \""+ categoryName +"\" }}");
		mongo.find(this.PRODUCT, query, result -> {
	        // error handling
	        if (result.failed()) {
	          //todo: error handling
	        }
	        List<Product> lookupResults = new ArrayList<Product>();
	        for (JsonObject o : result.result()) {
	          Product product = new Gson().fromJson(o.toString(), Product.class);
	          lookupResults.add(product);
	        }       
	        handler.handle(lookupResults);
		});
	}
}
