package nl.sogeti.vertx.webshop.data;

import java.util.ArrayList;
import java.util.List;

import nl.sogeti.vertx.webshop.model.Category;
import nl.sogeti.vertx.webshop.model.Product;

public class DummyProductRepository implements IProductsRepository {
	List<Product> dummyProducts = new ArrayList<Product>();
	List<Category> dummyCategories = new ArrayList<Category>();
	
	public DummyProductRepository() {
		Category food = new Category("Food");
		Category grooming = new Category("Grooming");
		Product purina = new Product("Purina One", 19.99, "Purina One is the number one food for puppies!", food);
		Product pedigree = new Product("Pedigree Dentastix", 4.99, "Let your dog have the smile it deserves.", food);
		Product coam = new Product("Turbo 2000", 29.99, "Groom your pet in to perfection.", grooming);
		dummyProducts.add(purina);
		dummyProducts.add(pedigree);
		dummyProducts.add(coam);
		dummyCategories.add(food);
		dummyCategories.add(grooming);
	}
	
	@Override
	public List<Product> getProducts() {
		return dummyProducts;
	}

	@Override
	public List<Category> getCategories() {
		return dummyCategories;
	}

	@Override
	public List<Product> getProducts(String categoryName) {
		List<Product> results = new ArrayList<Product>();
		for(Product p : dummyProducts){
			if(p.getCategory().getName().toLowerCase().equals(categoryName.toLowerCase())){
				results.add(p);
			}
		}
		return results;
	}

}
