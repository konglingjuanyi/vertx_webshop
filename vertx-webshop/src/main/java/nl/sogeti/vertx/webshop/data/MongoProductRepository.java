package nl.sogeti.vertx.webshop.data;

import java.util.List;

import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public class MongoProductRepository implements IProductsRepository {
	//Eventually this code will be substituted by a MongoDB implementation
	
	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProducts(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}
}
