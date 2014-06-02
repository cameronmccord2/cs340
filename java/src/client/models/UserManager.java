package client.models;

import java.util.ArrayList;

import client.server.User;


public class UserManager {
	
	private ArrayList<client.models.User> users; //users from client.models

	public UserManager(){}
	
	public UserManager(ArrayList<client.models.User> users) {
		this.users = users;
	}

	public String login(client.server.User simpleUser) { //this User is from translator users
		for(client.models.User u : users){
			if(u.getUsername().equals(simpleUser.getUser())){
				return "Success";
			}
		}
		return "Failed";
	}

	public Object register(User simpleUser) {
		client.models.User newUser = new client.models.User(simpleUser.getUser(),simpleUser.getPassword(),users.size());
		users.add(newUser);
		return "Success";
	}
	
	

}
