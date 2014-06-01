package server.models;

import client.server.User;

public class Login extends User {

	public Login(String user, String password) {
		super(user, password);
	}
	
	public Login(){
		super();
	}
	
	@Override
	public String getType() {
		return "login";
	}

}
