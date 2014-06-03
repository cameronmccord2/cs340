package client.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import client.data.PlayerInfo;

/**
 * The Class Player.
 */
public class Player extends Participant implements IPlayer {

	static Integer ROLL_COUNT = 2;
	/** The settlements. */
	protected List<ISettlement> settlements;

	protected List<ICity> cities;

	/** The roads. */
	protected List<IRoadSegment> roads;

	/** The player info. */
	protected PlayerInfo playerInfo;

	  protected int soldiers;
	  protected int victoryPoints;
	  protected int monuments;
	  protected boolean playedDevCard;
	  protected boolean discarded;

	/**
	 * Instantiates a new player.
	 * @param playerInfo the player info
	 */
	public Player(PlayerInfo playerInfo){
		super();
		this.playerInfo = playerInfo;
		this.settlements = new ArrayList<>();
		this.roads = new ArrayList<>();
		this.cities = new ArrayList<>();
		this.victoryPoints = 0;
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param playerInfo the player info
	 * @param developmentCards the development cards
	 * @param resourceCards the resource cards
	 * @param settlements the settlements
	 * @param roads the roads
	 */
	public Player(PlayerInfo playerInfo,
				  Map<IDevelopmentCard, Integer> developmentCards,
				  Map<IResourceCard, Integer> resourceCards,
				  List<ISettlement> settlements,
				  List<IRoadSegment> roads,
				  List<ICity> cities) {
		super(developmentCards, resourceCards);
		this.playerInfo = playerInfo;
		this.settlements = settlements;
		this.roads = roads;
		this.cities = cities;
		this.victoryPoints = 0;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#getSettlements()
	 */
	@Override
	public List<ISettlement> getSettlements() {
		return settlements;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#setSettlements(java.util.List)
	 */
	@Override
	public void setSettlements(List<ISettlement> settlements) {
		this.settlements = settlements;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#getRoads()
	 */
	@Override
	public List<IRoadSegment> getRoads() {
		return roads;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#setRoads(java.util.List)
	 */
	@Override
	public void setRoads(List<IRoadSegment> roads) {
		this.roads = roads;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#getPlayerInfo()
	 */
	@Override
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#setPlayerInfo(client.data.PlayerInfo)
	 */
	@Override
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}

	/* (non-Javadoc)
	 * @see client.models.IPlayer#robPlayer()
	 */
	@Override
	public List<IResourceCard> robPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)his.proxy.getFacade().;
	 * @see client.models.IParticipant#willAcceptTrade(client.models.ITrade)
	 */
	@Override
	public boolean willAcceptTrade(ITrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addSettlement(ISettlement settlement) {
		this.settlements.add(settlement);
	}

	@Override
	public void addRoad(IRoadSegment road) {
		this.roads.add(road);
	}

	@Override
	public List<ICity> getCities() {
		return cities;
	}

	@Override
	public void setCities(List<ICity> cities) {
		this.cities = cities;
	}

	@Override
	public int getSoldiers() {
		return soldiers;
	}

	@Override
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	};

	@Override
	public int getVictoryPoints() {
		return victoryPoints;
	}

	@Override
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	@Override
	public int getMonuments() {
		return monuments;
	}

	@Override
	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}

	@Override
	public boolean isPlayedDevCard() {
		return playedDevCard;
	}

	@Override
	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}

	@Override
	public boolean hasDiscarded() {
		return discarded;
	}

	@Override
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [settlements=");
		builder.append(settlements);
		builder.append(", \n\tcities=");
		builder.append(cities);
		builder.append(", \n\troads=");
		builder.append(roads);
		builder.append(", \n\tplayerInfo=");
		builder.append(playerInfo);
		builder.append(", \n\tsoldiers=");
		builder.append(soldiers);
		builder.append(", \n\tvictoryPoints=");
		builder.append(victoryPoints);
		builder.append(", \n\tmonuments=");
		builder.append(monuments);
		builder.append(", \n\tplayedDevCard=");
		builder.append(playedDevCard);
		builder.append(", \n\tdiscarded=");
		builder.append(discarded);
		builder.append(", \n\tdevelopmentCards=");
		builder.append(developmentCards);
		builder.append(", \n\tresourceCards=");
		builder.append(resourceCards);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean canBuildSettlement(ISettlement settlement)
	{
		return this.hasResources(Settlement.getResourceCost());
	}

	@Override
	public boolean canBuildCity(ICity city)
	{
		return this.hasResources(City.getResourceCost());
	}

	private boolean hasResources(Collection<Resource> cost) {
		for (Resource r : cost) {
			if(this.getResourceCards().get(r.getResourceType()) < r.getAmount())
				return false;
		}
		return true;
	}

	@Override
	public boolean canBuildRoad(IRoadSegment segment)
	{
		return this.hasResources(RoadSegment.getResourceCost());
	}
}
