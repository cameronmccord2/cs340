package server.models;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

public class FinishTurn implements ICommandParams {
	
	private String type;
	private Integer playerIndex;

	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
	}

	@Override
	public String getType() {
		return this.type;
	}

	public Integer getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(Integer playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FinishTurn [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append("]");
		return builder.toString();
	}

}
