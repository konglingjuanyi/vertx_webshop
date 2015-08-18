package nl.sogeti.vertx.webshop.model;

public class OrderProduct {
	private Order order;
	private Product product;
	private int amount;
	private double total;
	
	public OrderProduct(Product product, Order order){
		this.product = product;
		this.order = order;
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
	
	public double getTotal() {
		if(total == 0){
			total = calculateTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private double calculateTotal(){
		return product.getPrice() * amount;
	}
}
