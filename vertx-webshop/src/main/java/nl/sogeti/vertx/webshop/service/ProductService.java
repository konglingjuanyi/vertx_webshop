package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IProductsRepository;
import nl.sogeti.vertx.webshop.data.MongoProductRepository;

public class ProductService {
	private IProductsRepository repository;
		
	public ProductService(MongoClient mongo){
		repository = new MongoProductRepository(mongo);
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
}
