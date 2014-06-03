package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRPlayer implements IModelValid
{
	// ALL OF THESE SHOULD HAVE VISIBILITY MODIFIERS
	TRResourceList resources;
	TRDevCardList oldDevCards;
	TRDevCardList newDevCards;
	int roads;
	int cities;
	int settlements;
	int soldiers;
	int victoryPoints;
	int monuments;
	boolean playedDevCard;
	boolean discarded;
	int playerID;
	int playerIndex;
	String name;
	protected String color;
	  
	@Override
	public void isValid() throws InvalidTranslatorModelException{
		if(resources == null || this.oldDevCards == null || this.newDevCards == null)
			throw new InvalidTranslatorModelException(this.toString());
		this.resources.isValid();
		this.oldDevCards.isValid();
		this.newDevCards.isValid();

		if(this.color == null ||
		   this.color.length() == 0 ||
		   this.roads < 0 ||
		   this.cities < 0 ||
		   this.settlements < 0 ||
		   this.soldiers < 0 ||
		   this.victoryPoints < 0 ||
		   this.monuments < 0 ||
//		   this.playerID < 0 ||
		   this.name == null ||
		   this.name.length() == 0)
				throw new InvalidTranslatorModelException(this.toString());
	}
	  
	public TRResourceList getResources() {
		return resources;
	}
	public void setResources(TRResourceList resources) {
		this.resources = resources;
	}
	public TRDevCardList getOldDevCards() {
		return oldDevCards;
	}
	public void setOldDevCards(TRDevCardList oldDevCards) {
		this.oldDevCards = oldDevCards;
	}
	public TRDevCardList getNewDevCards() {
		return newDevCards;
	}
	public void setNewDevCards(TRDevCardList newDevCards) {
		this.newDevCards = newDevCards;
	}
	public int getRoads() {
		return roads;
	}
	public void setRoads(int roads) {
		this.roads = roads;
	}
	public int getCities() {
		return cities;
	}
	public void setCities(int cities) {
		this.cities = cities;
	}
	public int getSettlements() {
		return settlements;
	}
	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}
	public int getSoldiers() {
		return soldiers;
	}
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}
	public int getVictoryPoints() {
		return victoryPoints;
	}
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	public int getMonuments() {
		return monuments;
	}
	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}
	public boolean isPlayedDevCard() {
		return playedDevCard;
	}
	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}
	public boolean isDiscarded() {
		return discarded;
	}
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRPlayer [resources=");
		builder.append(resources);
		builder.append(", oldDevCards=");
		builder.append(oldDevCards);
		builder.append(", newDevCards=");
		builder.append(newDevCards);
		builder.append(", roads=");
		builder.append(roads);
		builder.append(", cities=");
		builder.append(cities);
		builder.append(", settlements=");
		builder.append(settlements);
		builder.append(", soldiers=");
		builder.append(soldiers);
		builder.append(", victoryPoints=");
		builder.append(victoryPoints);
		builder.append(", monuments=");
		builder.append(monuments);
		builder.append(", playedDevCard=");
		builder.append(playedDevCard);
		builder.append(", discarded=");
		builder.append(discarded);
		builder.append(", playerID=");
		builder.append(playerID);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", name=");
		builder.append(name);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}
}
