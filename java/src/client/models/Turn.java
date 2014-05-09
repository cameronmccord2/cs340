/**
 * 
 */
package client.models;

import client.data.PlayerInfo;

/**
 * The Turn class keeps track of who the current player is and the number rolled on the dice. 
 * It also initiates playing a card, a trade, building a road or settlement.
 * @author scottdaly
 *
 */
public class Turn implements ITurn{
	
	private PlayerInfo player;
	private int numberRolled;
	
	@Override
	public Card playCard(Card card) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Trade initTrade() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void buildRoad(Road road) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Settlement buildSettlement(Settlement settlement) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return the player
	 */
	public PlayerInfo getPlayer() {
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(PlayerInfo player) {
		this.player = player;
	}
	/**
	 * @return the numberRolled
	 */
	public int getNumberRolled() {
		return numberRolled;
	}
	/**
	 * @param numberRolled the numberRolled to set
	 */
	public void setNumberRolled(int numberRolled) {
		this.numberRolled = numberRolled;
	}
	
}
