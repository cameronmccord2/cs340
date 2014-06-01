package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class GetCommands implements ICommandParams {

	@Override
	public void isValid() throws CommandParamNotValidException {
		return;// nothing to check
	}

	@Override
	public String getType() {
		return "getCommands";
	}
	
}
