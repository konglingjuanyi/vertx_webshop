package nl.sogeti.vertx.webshop.model;

import java.math.BigDecimal;

public class Product implements IValidation{
	private String name;
	private BigDecimal price;
	private String description;
	private long id;
	private Category category;
	
	public Product(String name, double price, String description, Category category){
		this.name = name;
		setPrice(price);
		this.description = description;
		this.category = category;
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		if(name != null && !name.isEmpty() &&
				price != null && price.doubleValue() > 0.0 &&
				description != null && !description.isEmpty() &&
				category != null && category.isValid()){
			valid = true;
		}
		return valid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(double price) {
		BigDecimal thePrice = new BigDecimal(price);
		thePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.price = thePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
