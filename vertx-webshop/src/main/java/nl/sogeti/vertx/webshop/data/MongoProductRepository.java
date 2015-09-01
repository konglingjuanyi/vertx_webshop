package nl.sogeti.vertx.webshop.data;

import java.util.List;

import com.google.gson.Gson;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.sogeti.vertx.webshop.model.Product;
import nl.sogeti.vertx.webshop.util.JsonConverter;
import nl.sogeti.vertx.webshop.util.MongoClientProvider;

public class MongoProductRepository implements IProductRepository {
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
	
	public void getProducts(Handler<List<Product>> handler, String[] ids) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("{\"$or\": [");
		for(int index = 0; index < ids.length; index++){
			strBuilder.append("{\"_id\" : \"" + ids[index] + "\"}");
			if(index != ids.length - 1){
				strBuilder.append(",");
			}
		}
		strBuilder.append("]}");
		JsonObject query = new JsonObject(strBuilder.toString());
		findProducts(handler, query);
	}
	
	@Override
	public void getProduct(Handler<Product> handler, String id) {
		JsonObject query = new JsonObject().put("_id", id);
		findProduct(handler, query);
	}
	
	@Override
	public void deleteProduct(Handler<Boolean> handler, String id) {
		JsonObject query = new JsonObject().put("_id", id);
		mongo.removeOne(PRODUCT, query, result -> {
			if(result.succeeded()){
				handler.handle(new Boolean(true));
			}
			else{
				handler.handle(new Boolean(false));
			}
		});
	}
	
	@Override
	public void saveProduct(Handler<String> handler, Product product) {
		JsonObject document = new JsonObject(new Gson().toJson(product));
		mongo.insert(PRODUCT, document, result -> {
			if(result.result() != null){
				handler.handle(result.result());
			}
			else{
				result.cause().printStackTrace();
			}
		});
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
	
	private void findProduct(Handler<Product> handler, JsonObject query){
		mongo.findOne(PRODUCT, query, null, result -> {
			if(result.failed()){
				handler.handle(null);
			}
			else{
				handler.handle(JsonConverter.fromJsonObject(result.result(), Product.class));
			}
		});
	}
}
