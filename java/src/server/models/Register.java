package server.models;

import client.server.User;

public class Register extends User {

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
}
