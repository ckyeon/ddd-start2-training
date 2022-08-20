package ckyeon.dddshop.order.domain;

import java.util.List;

public class Order {

	private OrderNo id;
	private List<OrderLine> orderLines;
	private ShippingInfo shippingInfo;
	private OrderState state;
	private Money totalAmounts;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Order.class) {
			return false;
		}
		Order other = (Order) obj;
		if (this.id == null) {
			return false;
		}
		return this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
		setOrderLines(orderLines);
		setShippingInfo(shippingInfo);
	}

	public void changeShipped() {
	}

	public void changeShippingInfo(ShippingInfo newShippingInfo) {
		verifyNotYetShipped();
		setShippingInfo(newShippingInfo);
	}

	public void cancel() {
		verifyNotYetShipped();
		this.state = OrderState.CANCELED;
	}

	public void completePayment() {
	}

	public OrderNo getId() {
		return id;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public ShippingInfo getShippingInfo() {
		return shippingInfo;
	}

	public OrderState getState() {
		return state;
	}

	public Money getTotalAmounts() {
		return totalAmounts;
	}

	private void setOrderLines(List<OrderLine> orderLines) {
		verifyAtLeastOneOrMoreOrderLines(orderLines);
		this.orderLines = orderLines;
		calculateTotalAmounts();
	}

	private void setShippingInfo(ShippingInfo shippingInfo) {
		if (shippingInfo == null) {
			throw new IllegalArgumentException("no ShippingInfo");
		}
		this.shippingInfo = shippingInfo;
	}

	private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
		if (orderLines == null || orderLines.isEmpty()) {
			throw new IllegalArgumentException("no OrderLine");
		}
	}

	private void calculateTotalAmounts() {
		int sum = orderLines.stream()
			.mapToInt(OrderLine::getAmounts)
			.sum();
		this.totalAmounts = new Money(sum);
	}

	private void verifyNotYetShipped() {
		if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING) {
			throw new IllegalStateException("already shipped");
		}
	}
}
