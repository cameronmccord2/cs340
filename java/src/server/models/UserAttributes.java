package server.models;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

/**
 * Holds the user's gameId and username for proper manipulation of the model
 * @author scottdaly
 *
 */
public class UserAttributes {
	
	protected Integer username;
	protected String password;
	protected Integer gameId;
	
	public UserAttributes(Integer username, String password, Integer gameId){
		this.username = username;
		this.gameId = gameId;
		this.password = password;
	}
	
	public UserAttributes(){
		this(null, null, null);
	}

	public UserAttributes(String json) {
		Gson gson = new Gson();
		Login cookies = gson.fromJson(json, Login.class);
	}

	/**
	 * This returns an Integer object (which can be null) representing the
	 * unique id for the user.
	 * 
	 * @return	The unique user id (not the relative turn in a game) for
	 * 			this user or null if there is no cookie.
	 */
	public Integer getusername() {
		return username;
	}

	/**
	 * 
	 * @param username	The unique id for the user or null if there is no cookie.
	 */
	public void setusername(Integer username) {
		this.username = username;
	}

	/**
	 * This returns an Integer object (which can be null) representing the
	 * unique id for the game.
	 * 
	 * @return	The unique game id for the game the user is connecting to
	 * 			or null if there is no cookie.
	 */
	public Integer getGameId() {
		return gameId;
	}

	/**
	 * 
	 * @param gameId	The unique id for the game that the user is joining.
	 */
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAttributes other = (UserAttributes) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAttributes [username=");
		builder.append(username);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append("]");
		return builder.toString();
	}
}
