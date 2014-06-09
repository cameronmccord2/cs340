package client.models;

import java.util.ArrayList;

import server.models.Login;
import server.models.Register;


public class UserManager {
	
	private ArrayList<User> users;

	public UserManager(){
		this.users = new ArrayList<>();
		User u1 = new User("Kirk","kirk",0);
		User u2 = new User("Spock","spock",1);
		User u3 = new User("McCoy","mccoy",2);
		User u4 = new User("Uhura","uhura",3);
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
	}
	
	public UserManager(ArrayList<User> users) {
		this.users = users;
	}

	public String login(Login loggingInUser) {
		for(User u : users){
			if(u.getname().equals(loggingInUser.getUser()) && u.getPassword().equals(loggingInUser.getPassword())){
				return "" + u.getPlayerID();
			}
		}
		return "-1";
	}

	public String register(Register simpleUser) {
		User newUser = new User(simpleUser.getUser(),simpleUser.getPassword(),users.size());
		users.add(newUser);
		return "" + users.size();
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	

}
