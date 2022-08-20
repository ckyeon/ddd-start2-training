package ckyeon.dddshop.order.domain;

public class Receiver {

	private String name;
	private String phoneNumber;

	public Receiver(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof Receiver other)) {
			return false;
		}
		return this.name.equals(other.name) && this.phoneNumber.equals(other.phoneNumber);
	}
}
