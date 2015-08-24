package nl.sogeti.vertx.webshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private BigDecimal total;
	private List<OrderProduct> orderedProducts;
	
	public Order(){
		orderedProducts = new ArrayList<OrderProduct>();
		calculateTotal();
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
