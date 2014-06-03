package client.models;

import java.util.ArrayList;

import server.models.Login;
import server.models.Register;


public class UserManager {
	
	private ArrayList<User> users;

	public UserManager(){
		this.users = new ArrayList<>();
	}
	
	public UserManager(ArrayList<User> users) {
		this.users = users;
	}

	public String login(Login loggingInUser) {
		for(User u : users){
			if(u.getUsername().equals(loggingInUser.getUser())){
				return "Login Success";
			}
		}
		return "Failed";
	}

	public String register(Register simpleUser) {
		User newUser = new User(simpleUser.getUser(),simpleUser.getPassword(),users.size());
		users.add(newUser);
		return "Register Success";
	}
	
	

}
