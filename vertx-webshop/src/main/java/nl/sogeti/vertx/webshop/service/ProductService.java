package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IProductRepository;
import nl.sogeti.vertx.webshop.data.MongoProductRepository;
import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public class ProductService {
	private IProductRepository repository;
		
	public ProductService(){
		repository = new MongoProductRepository();
	}
	
	public void getProducts(RoutingContext rc){
		String categoryName = rc.request().getParam("categoryName");
		if(categoryName != null && !categoryName.isEmpty()){
			repository.getProducts(result -> {
				String jsonProducts = new Gson().toJson(result);
				rc.response().end(jsonProducts);
			}, categoryName);
		}
		else{
			 repository.getProducts(result -> {
				 String jsonProducts = new Gson().toJson(result);
				 rc.response().end(jsonProducts);
			 });
		}
		
	}
	
	public void deleteProduct(RoutingContext rc){
		String id = rc.request().getParam("id");
		repository.deleteProduct(result -> {
			if(result == true){
				rc.response().setStatusCode(200).end();
			}
			else{
				rc.response().setStatusCode(404).end();
			}
		}, id);
	}
	
	public void saveProduct(RoutingContext rc){
		JsonObject jsonProduct = rc.getBodyAsJson();
		JsonObject jsonCategory = jsonProduct.getJsonObject("category");
		Double price = jsonProduct.getDouble("price");
		String description = jsonProduct.getString("description");
		String name = jsonProduct.getString("name");
		String categoryName = jsonCategory.getString("name");
		
		if(price == null || price == 0.0 || description == null || name == null || categoryName == null){
			rc.response().setStatusCode(400).end();
			return;
		}
		
		Category category = new Category(categoryName);
		Product product = new Product(name, 
				price, description, category);
		
		if(!product.isValid()){
			rc.response().setStatusCode(400).end();
			return;
		}
		repository.saveProduct(result -> {
			if(result != null){
				rc.response().setStatusCode(200).end();
			}
		}, product);
	}
}
