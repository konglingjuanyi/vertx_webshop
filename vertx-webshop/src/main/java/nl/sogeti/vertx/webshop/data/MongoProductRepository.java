package nl.sogeti.vertx.webshop.data;

import java.util.List;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Product;
import nl.sogeti.vertx.webshop.util.JsonConverter;
import nl.sogeti.vertx.webshop.util.MongoClientProvider;

public class MongoProductRepository implements IProductsRepository {
	private final MongoClient mongo;
	private final String PRODUCT = "product";
	
	public MongoProductRepository(){
		this.mongo = MongoClientProvider.getClient();
	}
	
	@Override
	public void getProducts(Handler<List<Product>> handler) {
		findProducts(handler, new JsonObject());	
	}

	@Override
	public void getProducts(Handler<List<Product>> handler, String categoryName) {
		JsonObject query = new JsonObject("{\"category\": {\"name\": \""+ categoryName +"\" }}");
		findProducts(handler, query);
	}
	
	private void findProducts(Handler<List<Product>> handler, JsonObject query){
		mongo.find(this.PRODUCT, query, result -> {
	        // error handling
	        if (result.failed()) {
	          //todo: error handling
	        }
	        List<Product> lookupResults = JsonConverter.fromJsonList(result.result(), Product.class);
	        handler.handle(lookupResults);
		});
	}
}
