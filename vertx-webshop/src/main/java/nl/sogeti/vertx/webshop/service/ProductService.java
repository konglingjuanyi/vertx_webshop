package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IProductRepository;
import nl.sogeti.vertx.webshop.data.MongoProductRepository;

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
}
