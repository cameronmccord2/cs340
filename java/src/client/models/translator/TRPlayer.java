package client.models.translator;

public class TRPlayer implements IModelValid {
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
	  Long playerID;
	  int playerIndex;
	  String name;
	  protected String color;
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
	public Long getPlayerID() {
		return playerID;
	}
	public void setPlayerID(Long playerID) {
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
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
