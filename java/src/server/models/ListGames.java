package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class ListGames implements ICommandParams {

	@Override
	public void validate() throws CommandParamNotValidException {
		return;// always valid
	}

	@Override
	public String getType() {
		return "listGames";
	}

}
