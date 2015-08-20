package nl.sogeti.vertx.webshop.model;

public class User {
	private String userName;
	private String password;
	private String fullName;
	private String address;
	private String city;
	private String email;
	
	public User(String userName, String password, String fullName, String address, String city, String email){
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
}
