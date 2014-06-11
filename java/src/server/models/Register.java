package server.models;

import client.server.UserCommandParams;

public class Register extends UserCommandParams {

	public Register(String username, String password){
		super(username, password);
	}
	
	public Register(){
		super();
	}
	
	@Override
	public String getType() {
		return "register";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Register [getType()=" + getType() + ", getUser()=" + getUser()
				+ ", getPassword()=" + getPassword() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
