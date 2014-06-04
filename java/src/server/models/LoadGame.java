package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class LoadGame implements ICommandParams {

	protected String name;
	
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.name != null && this.name.length() == 0)
			throw new CommandParamNotValidException("Name cant be null or of length zero");
	}

	@Override
	public String getType() {
		return "loadGame";
	}

}
