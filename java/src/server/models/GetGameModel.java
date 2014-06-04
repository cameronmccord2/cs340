package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class GetGameModel implements ICommandParams {

	protected Integer modelVersion;
	
	public GetGameModel(String json) {
		this.modelVersion = Integer.parseInt(json);
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		return;// always valid
	}

	@Override
	public String getType() {
		return "getJsonGameModelString";
	}

}
