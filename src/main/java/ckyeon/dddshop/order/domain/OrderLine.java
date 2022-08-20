package ckyeon.dddshop.order.domain;

public class OrderLine {

	private Product product;
	private Money price;
	private int quantity;
	private Money amounts;

	public OrderLine(Product product, Money price, int quantity) {
		this.product = product;
		this.price = price;
		this.quantity = quantity;
		this.amounts = calculateAmounts();
	}

	public int getAmounts() {
		return amounts.getValue();
	}

	public Product getProduct() {
		return product;
	}

	public int getPrice() {
		return price.getValue();
	}

	public int getQuantity() {
		return quantity;
	}

	private Money calculateAmounts() {
		return price.multiply(quantity);
	}
}
