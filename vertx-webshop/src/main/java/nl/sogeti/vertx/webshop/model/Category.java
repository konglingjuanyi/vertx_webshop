package nl.sogeti.vertx.webshop.model;

public class Category implements IValidation {
	private String _id;
	private String name;
	
	public Category(String name){
		this.name = name;
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		if(name != null && !name.isEmpty()){
			valid = true;
		}
		return valid;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get_id() {
		return _id;
	}	
}
