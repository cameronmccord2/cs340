/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * 
 * Holds the game id and name to save
 * @author scottdaly
 *
 */
public class SaveGame implements ICommandParams{
	
	private int id;
	private String name;
	
	public SaveGame(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public SaveGame(){
		
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.name == null || this.name.length() == 0)
			throw new CommandParamNotValidException("name must not be null or of length zero: " + this.toString());
	}

	@Override
	public String getType() {
		return "saveGame";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveGame [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	

}
