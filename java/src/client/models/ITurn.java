package client.models;


/**
 * The Turn interface
 * @author scottdaly
 *
 */
public interface ITurn {
	
//	TurnTracker
	
	/**
	 * This is called when a player wants to use a card of any type as part of their turn
	 * @param Card that is being played by the player
	 * @return the Card is being played or used
	 */
	Card playCard(Card card);
	
	/**
	 * This is called when a player wants to initialize a trade sequence and it will create a Trade object
	 * @return an empty trade object that the controller will populate
	 */
	Trade initTrade();
	
	/**
	 * This is called when a player wants to build a road by passing in a Road object
	 * @param The Road object that wants to be created
	 */
	void buildRoad(Road road);
	
	/**
	 * This is called when a player wants to build a settlement by passing in a Settlement object
	 * @param The Settlement object that wants to be created
	 */
	Settlement buildSettlement(Settlement settlement);
}
