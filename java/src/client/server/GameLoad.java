/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Holds the name of the game to laod
 * @author scottdaly
 *
 */
public class GameLoad  implements ICommandParams{
	private String name;

	public GameLoad(String name) {
		this.name = name;
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
			throw new CommandParamNotValidException("name cant be null or length zero");
	}

	@Override
	public String getType() {
		return "loadGame";
	}
	
	
}
