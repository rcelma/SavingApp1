package net.rcelma.a1_30_app1.data_object;

public class Contact {
	private Long id;
	private String name;
	private String phoneNumber;

	public Contact() {
	}

	public Contact(Long id, String name, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("CONTACT {ID: ");
		sb.append(id).append(" NAME: ").append(name).append(" PHONE NUMBER: ");
		sb.append(phoneNumber).append(" }");
		return  sb.toString();
	}
}