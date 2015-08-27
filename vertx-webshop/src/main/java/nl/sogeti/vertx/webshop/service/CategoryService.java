package nl.sogeti.vertx.webshop.service;

import com.google.gson.Gson;

import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.ICategoryRepository;
import nl.sogeti.vertx.webshop.data.MongoCategoryRepository;

public class CategoryService {
	private ICategoryRepository repository;
	
	public CategoryService(){
		repository = new MongoCategoryRepository();
	}
	
	public void getCategories(RoutingContext rc){
		repository.getCategories(result -> {
			 String jsonCategories = new Gson().toJson(result);
			 rc.response().end(jsonCategories);
		});
	}
}
