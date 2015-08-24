package nl.sogeti.vertx.webshop.model;

import java.math.BigDecimal;

public class OrderProduct {
	private Order order;
	private Product product;
	private int amount;
	private BigDecimal total;
	
	public OrderProduct(Product product, Order order, int amount){
		this.product = product;
		this.order = order;
		this.amount = amount;
		calculateTotal();
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	private void calculateTotal(){
		total = product.getPrice().multiply(new BigDecimal(amount));
	}
}
