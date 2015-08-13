package nl.sogeti.vertx.webshop.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IProductsRepository;
import nl.sogeti.vertx.webshop.data.MongoProductRepository;
import nl.sogeti.vertx.webshop.model.Product;

public class ProductService {
	private IProductsRepository repository;
	
	public ProductService(){
		repository = new MongoProductRepository();
	}
	
	public void getProducts(RoutingContext rc){
		List<Product> products = new ArrayList<Product>();
		products = repository.getProducts();
		String jsonProducts = new Gson().toJson(products);
		rc.response().end(jsonProducts);
	}
}
