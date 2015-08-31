package nl.sogeti.vertx.webshop.model;

public class UserData {
	private String userName;
	private String fullName;
	private String address;
	private String city;
	private String email;
	
	public UserData(User user){
		this.userName = user.getUserName();
		this.fullName = user.getFullName();
		this.address = user.getAddress();
		this.city = user.getCity();
		this.email = user.getEmail();
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
	public String getUserName() {
		return userName;
	}
}
