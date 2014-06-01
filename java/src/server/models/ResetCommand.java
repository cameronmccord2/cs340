package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class ResetCommand implements ICommandParams {

	@Override
	public void isValid() throws CommandParamNotValidException {
		return;// always valid
	}

	@Override
	public String getType() {
		return "reset";
	}

	
}
