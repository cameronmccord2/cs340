package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;


/**
 * This stores the players returned from the get games call
 * @author scottdaly
 *
 */
public class PlayerServer implements ICommandParams{
	
	private String color;
	private String name;
	private int id;
	
	public PlayerServer(String color, String name, int id) {
		this.color = color;
		this.name = name;
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void isValid() throws CommandParamNotValidException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
