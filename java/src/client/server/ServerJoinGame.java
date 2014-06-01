/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Stores the info needed to join a game on the server
 * @author scottdaly
 *
 */
public class ServerJoinGame implements ICommandParams{
	
	private int id;
	private String color;
	
	public ServerJoinGame(int id, String color) {
		this.id = id;
		this.color = color;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.color == null || this.color.length() == 0)
			throw new CommandParamNotValidException("Color must not be null or of length zero: " + this.toString());
	}

	@Override
	public String getType() {
		return "joinGame";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerJoinGame [id=");
		builder.append(id);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}
	
	

}
