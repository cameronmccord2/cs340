package server.models;

import client.server.UserCommandParams;

public class Login extends UserCommandParams {

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
