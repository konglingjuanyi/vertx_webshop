package nl.sogeti.vertx.webshop.model;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;

public class User extends AbstractUser implements IValidation {
	private String userName;
	private String password;
	private String fullName;
	private String address;
	private String city;
	private String email;
	private String role;
	
	public User(String userName, String password, String fullName, String address, String city, String email){
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.email = email;
	}
	
	public User(JsonObject json){
		this.userName = json.getString("userName");
		this.password = json.getString("password");
		this.fullName = json.getString("fullName");
		this.address = json.getString("address");
		this.city = json.getString("city");
		this.email = json.getString("email");
		this.role = "user";
	}
	
	@Override
	public JsonObject principal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAuthProvider(AuthProvider authProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doIsPermitted(String permission, Handler<AsyncResult<Boolean>> resultHandler) {
		resultHandler.handle(new AsyncResult<Boolean>() {
			@Override
			public boolean succeeded() {
				if(!(permission == null || permission.isEmpty()) && !(role == null || role.isEmpty())){
					return true;
				}
				return false;
			}
			
			@Override
			public Boolean result() {
				if(permission.equals("role:" + role)){
					return true;
				}
				return false;
			}
			
			@Override
			public boolean failed() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Throwable cause() {
				return new Exception("Does not have the required permissions");
			}
		});
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		if(userName != null && !userName.isEmpty() && 
				password != null && !password.isEmpty() &&
				fullName != null && !fullName.isEmpty() &&
				address != null && !address.isEmpty() &&
				city != null && !city.isEmpty() &&
				email != null && !email.isEmpty()){
			valid = true;
		}		
		return valid;
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
