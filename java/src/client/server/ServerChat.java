package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * 
 * This holds the content needed to post the chat message to the server
 * @author scottdaly
 *
 */
public class ServerChat implements ICommandParams{
	private String type;
	private int playerIndex;
	private String content;
	
	public ServerChat(String type, int playerIndex, String content) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.content = content;
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.content == null || this.content.length() == 0)
			throw new CommandParamNotValidException("Content cant be null or of length zero: " + this.toString());
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @param playerIndex the playerIndex to set
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerChat [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

	
	
}
