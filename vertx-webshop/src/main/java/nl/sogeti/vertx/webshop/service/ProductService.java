package nl.sogeti.vertx.webshop.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.DummyProductRepository;
import nl.sogeti.vertx.webshop.data.IProductsRepository;
import nl.sogeti.vertx.webshop.data.MongoProductRepository;
import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public class ProductService {
	private IProductsRepository repository;
	
	public ProductService(){
		repository = new DummyProductRepository();
	}
	
	public void getProducts(RoutingContext rc){
		List<Product> products = new ArrayList<Product>();
		products = repository.getProducts();
		String jsonProducts = new Gson().toJson(products);
		rc.response().end(jsonProducts);
	}
	
	public void getProductsByCategory(RoutingContext rc){
		List<Product> products = new ArrayList<Product>();
		String categoryName = rc.request().getParam("categoryName");
		products = repository.getProducts(categoryName);
		String jsonProduct = new Gson().toJson(products);
		rc.response().end(jsonProduct);
	}
	
	public void getCategories(RoutingContext rc){
		List<Category> categories = new ArrayList<Category>();
		categories = repository.getCategories();
		String jsonProducts = new Gson().toJson(categories);
		rc.response().end(jsonProducts);
	}
}
