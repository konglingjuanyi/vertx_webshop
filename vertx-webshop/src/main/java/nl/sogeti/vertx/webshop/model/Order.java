package nl.sogeti.vertx.webshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private double total;
	private List<OrderProduct> orderedProducts;
	
	public Order(){
		orderedProducts = new ArrayList<OrderProduct>();
	}
	
	public double getTotal(){
		if(total == 0){
			total = calculateTotal();
		}
		return total;
	}
	
	public List<OrderProduct> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private double calculateTotal(){
		double price = 0;
		for(OrderProduct orderedProduct : orderedProducts){
			price += orderedProduct.getTotal();
		}
		return price;
	}
}
