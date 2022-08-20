package ckyeon.dddshop.order.domain;

public class ShippingInfo {

	private Address address;
	private Receiver receiver;

	public ShippingInfo(Address address, Receiver receiver) {
		this.address = address;
		this.receiver = receiver;
	}

	public Address getAddress() {
		return address;
	}

	public Receiver getReceiver() {
		return receiver;
	}
}
