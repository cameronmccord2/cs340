package server.models;

import com.sun.net.httpserver.HttpExchange;

/**
 * Holds the user's gameId and userId for proper manipulation of the model
 * @author scottdaly
 *
 */
public class UserAttributes {
	
	protected Integer userId;
	protected Integer gameId;
	
	public UserAttributes(Integer userId, Integer gameId){
		this.userId = userId;
		this.gameId = gameId;
	}
	
	public UserAttributes(){
		this(null, null);
	}

	public UserAttributes(HttpExchange exchange) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This returns an Integer object (which can be null) representing the
	 * unique id for the user.
	 * 
	 * @return	The unique user id (not the relative turn in a game) for
	 * 			this user or null if there is no cookie.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId	The unique id for the user or null if there is no cookie.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAttributes [userId=");
		builder.append(userId);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append("]");
		return builder.toString();
	}
}
