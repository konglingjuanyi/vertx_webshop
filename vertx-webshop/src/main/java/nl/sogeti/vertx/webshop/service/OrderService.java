package nl.sogeti.vertx.webshop.service;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nl.sogeti.vertx.webshop.data.IOrderRepository;
import nl.sogeti.vertx.webshop.data.IProductRepository;
import nl.sogeti.vertx.webshop.data.MongoOrderRepository;
import nl.sogeti.vertx.webshop.data.MongoProductRepository;
import nl.sogeti.vertx.webshop.model.Order;
import nl.sogeti.vertx.webshop.model.OrderProduct;
import nl.sogeti.vertx.webshop.model.Product;

public class OrderService {
	private IOrderRepository repository;
	private IProductRepository productRepository;

	public OrderService(){
		repository = new MongoOrderRepository();
		productRepository = new MongoProductRepository();
	}
	
	public void addOrder(RoutingContext rc){
		JsonObject json = rc.getBodyAsJson();
		Order order = new Order(json.getString("shippingAddress"), json.getString("shippingCity"));
		
		JsonArray orderedProductsJson = json.getJsonArray("orderedProducts");
		String[] productIds = new String[orderedProductsJson.size()];
		//Get all ids to get all the corresponding product data
		for(int index = 0; index < orderedProductsJson.size(); index++){
			JsonObject jsonOrderProduct = orderedProductsJson.getJsonObject(index);
			String productId = jsonOrderProduct.getString("productId");
			productIds[index] = productId;
		}
		productRepository.getProducts(products -> {
			for(Product product : products){
				JsonObject tempOrderedProduct = new JsonObject();
				//Match the product with the orderedproduct so we can save the amount
				for(int index = 0; index < orderedProductsJson.size(); index++){
					tempOrderedProduct = orderedProductsJson.getJsonObject(index);
					if(product.get_id().equals(tempOrderedProduct.getString("productId"))){
						order.addOrderedProduct(new OrderProduct(product, tempOrderedProduct.getInteger("amount")));
					}
				}
			}
			repository.addOrder(result -> {
				if(!order.isValid()){
					rc.response().setStatusCode(400).end();
					return;
				}
				//The operation returned an id and thus it succeeded
				if(result != null){
					rc.response().setStatusCode(200).end();
				}
			}, order);
		}, productIds);
	}
}
