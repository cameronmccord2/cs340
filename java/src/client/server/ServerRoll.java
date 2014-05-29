package client.server;

import server.commands.ICommandParams;

/**
 * Class to hold info for sending a rolled dice to the server
 * @author scottdaly
 *
 */
public class ServerRoll implements ICommandParams{

	private String type;
	private int playerIndex;
	private int number;
	
	public ServerRoll(String type, int playerIndex, int number) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.number = number;
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
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServerRoll [type=" + type + ", playerIndex=" + playerIndex
				+ ", number=" + number + "]";
	}
	
	
	
	
}
