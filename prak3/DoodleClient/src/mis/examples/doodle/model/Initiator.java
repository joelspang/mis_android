package mis.examples.doodle.model;

import org.simpleframework.xml.Element;

public class Initiator {

	@Element(required=false)
	protected Integer userId;

	@Element
	protected String name;
	
	@Element(required=false)
	protected String eMailAddress;
	
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	
}
