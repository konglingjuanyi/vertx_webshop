package nl.sogeti.vertx.webshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements IValidation {
	private BigDecimal total;
	private List<OrderProduct> orderedProducts;
	private String shippingAddress;
	private String shippingCity;
	
	public Order(){
		orderedProducts = new ArrayList<OrderProduct>();
		calculateTotal();
	}
	
	@Override
	public boolean isValid(){
		boolean valid = false;
		if(total != null && total.doubleValue() > 0.0 && 
				shippingAddress != null && !shippingAddress.isEmpty() &&
				shippingCity != null && !shippingCity.isEmpty() &&
				orderedProducts.size() > 0){
			valid = true;
		}
		return valid;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public BigDecimal getTotal(){
		return total;
	}
	
	public List<OrderProduct> getOrderedProducts() {
		return orderedProducts;
	}

	private void calculateTotal(){
		BigDecimal total = new BigDecimal(0.0);
		total.setScale(2, BigDecimal.ROUND_HALF_UP);
		for(OrderProduct orderedProduct : orderedProducts){
			total = total.multiply(orderedProduct.getTotal());
		}
		this.total = total;
	}
}
