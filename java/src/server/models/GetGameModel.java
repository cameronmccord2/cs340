package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class GetGameModel implements ICommandParams {

	protected Integer modelVersion;
	
	public GetGameModel(String json) {
		if(json.length() > 0)
			this.modelVersion = Integer.parseInt(json);
		this.modelVersion = 0;
	}

	public GetGameModel(Integer gameId) {
		this.modelVersion = 0;
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		return;// always valid
	}

	@Override
	public String getType() {
		return "getJsonGameModelString";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetGameModel [modelVersion=");
		builder.append(modelVersion);
		builder.append("]");
		return builder.toString();
	}

}
