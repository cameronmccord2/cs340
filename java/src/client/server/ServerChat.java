package client.server;

/**
 * 
 * This holds the content needed to post the chat message to the server
 * @author scottdaly
 *
 */
public class ServerChat {
	private String type;
	private int playerIndex;
	private String content;
	
	public ServerChat(String type, int playerIndex, String content) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.content = content;
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
	
	
}
