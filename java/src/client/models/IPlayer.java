package client.models;

import java.util.List;

import client.data.PlayerInfo;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlayer.
 */
public interface IPlayer extends IParticipant {

	/**
	 * Gets the settlements.
	 *
	 * @return the settlements
	 */
	List<ISettlement> getSettlements();

	/**
	 * Sets the settlements.
	 *
	 * @param settlements the new settlements
	 */
	void setSettlements(List<ISettlement> settlements);

	/**
	 * Gets the roads.
	 *
	 * @return the roads
	 */
	List<IRoad> getRoads();

	/**
	 * Sets the roads.
	 *
	 * @param roads the new roads
	 */
	void setRoads(List<IRoad> roads);

	/**
	 * Gets the player info.
	 *
	 * @return the player info
	 */
	PlayerInfo getPlayerInfo();

	/**
	 * Sets the player info.
	 *
	 * @param playerInfo the new player info
	 */
	void setPlayerInfo(PlayerInfo playerInfo);
	
	/**
	 * Rob player.
	 *
	 * @return the list
	 */
	List<IResourceCard> robPlayer();

}
