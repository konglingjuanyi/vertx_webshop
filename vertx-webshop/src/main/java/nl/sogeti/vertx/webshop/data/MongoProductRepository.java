package nl.sogeti.vertx.webshop.data;

import java.util.ArrayList;
import java.util.List;

import nl.sogeti.vertx.webshop.model.Product;

public class MongoProductRepository implements IProductsRepository {
	//Eventually this code will be substituted by a MongoDB implementation
	@Override
	public List<Product> getProducts() {
		List<Product> dummyProducts = new ArrayList<Product>();
		Product purina = new Product("Purina One", 19.99, "Purina One is the number one food for puppies!");
		Product pedigree = new Product("Pedigree Dentastix", 4.99, "Let your dog have the smile it deserves.");
		dummyProducts.add(purina);
		dummyProducts.add(pedigree);
		return dummyProducts;
	}
}
