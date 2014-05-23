package client.models;

import java.util.List;

import client.data.PlayerInfo;

/**
 * The Interface IPlayer.
 */
public interface IPlayer extends IParticipant {

	/**
	 * Gets the settlements for this player.
	 *
	 * @return the settlements for this player
	 */
	List<ISettlement> getSettlements();

	/**
	 * Sets the settlements for this player
	 *
	 * @param settlements the new settlements
	 */
	void setSettlements(List<ISettlement> settlements);

	/**
	 * Adds the settlement to the player.
	 *
	 * @param settlement the settlement to add
	 */
	void addSettlement(ISettlement settlement);

	/**
	 * Gets the roads for the player.
	 *
	 * @return the roads for the player
	 */
	List<IRoad> getRoads();

	/**
	 * Adds the road to the player.
	 *
	 * @param road the road to add
	 */
	void addRoad(IRoad road);

	/**
	 * Sets the roads for the player.
	 *
	 * @param roads the new roads list for the player
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

	List<ICity> getCities();

	void setCities(List<ICity> cities);

	int getSoldiers();

	void setSoldiers(int soldiers);

	int getVictoryPoints();

	void setVictoryPoints(int victoryPoints);

	int getMonuments();

	void setMonuments(int monuments);

	boolean isPlayedDevCard();

	void setPlayedDevCard(boolean playedDevCard);

	boolean isDiscarded();

	void setDiscarded(boolean discarded);

	boolean canBuildSettlement(ISettlement settlement);

	boolean canBuildCity(ICity city);

	boolean canBuildRoad(IRoadSegment segment);

}
