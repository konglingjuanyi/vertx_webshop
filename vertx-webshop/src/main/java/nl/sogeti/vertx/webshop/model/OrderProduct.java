package nl.sogeti.vertx.webshop.model;

import java.math.BigDecimal;

public class OrderProduct implements IValidation{
	private Product product;
	private int amount;
	private BigDecimal total;
	
	public OrderProduct(Product product, int amount){
		this.product = product;
		this.amount = amount;
		calculateTotal();
	}
	
	@Override
	public boolean isValid() {
		boolean result = false;
		if(product != null && product.isValid() &&
				amount > 0 && total != null && total.doubleValue() > 0.0){
			result = true;
		}
		return result;
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
	
	public BigDecimal getTotal() {
		return total;
	}

	private void calculateTotal(){
		total = product.getPrice().multiply(new BigDecimal(amount));
	}
}
