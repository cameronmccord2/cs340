package client.server;

import server.commands.ICommandParams;

/**
 * Holds info needed to finish a turn on the server
 * @author scottdaly
 *
 */
public class FinishedTurn  implements ICommandParams{
	private String type;
	private int playerIndex;
	public FinishedTurn(String type, int playerIndex) {
		this.type = type;
		this.playerIndex = playerIndex;
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
	
	
}
