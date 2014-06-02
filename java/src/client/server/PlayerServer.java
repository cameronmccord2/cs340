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
		if(this.name == null || this.name.length() == 0 || this.color == null || this.color.length() == 0 || id < 0)
			throw new CommandParamNotValidException("name, color musnt be null or of length zero, id cant be zero: " + this.toString());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayerServer [color=");
		builder.append(color);
		builder.append(", name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
