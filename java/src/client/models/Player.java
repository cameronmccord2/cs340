package client.models;

import java.util.ArrayList;
import java.util.List;

import client.data.PlayerInfo;

/**
 * The Class Player.
 */
public class Player extends Participant implements IPlayer {
	
	/** The settlements. */
	protected List<ISettlement> settlements;
	
	protected List<ICity> cities;
	
	/** The roads. */
	protected List<IRoad> roads;
	
	/** The player info. */
	protected PlayerInfo playerInfo;
	
	/**
	 * Instantiates a new player.
	 * @param playerInfo the player info
	 */
	public Player(PlayerInfo playerInfo){
		super();
		this.playerInfo = playerInfo;
		this.settlements = new ArrayList<ISettlement>();
		this.roads = new ArrayList<IRoad>();
		this.cities = new ArrayList<ICity>();
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
	public Player(PlayerInfo playerInfo, List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards, List<ISettlement> settlements, List<IRoad> roads, List<ICity> cities){
		super(developmentCards, resourceCards);
		this.playerInfo = playerInfo;
		this.settlements = settlements;
		this.roads = roads;
		this.cities = cities;
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
	public List<IRoad> getRoads() {
		return roads;
	}
	
	/* (non-Javadoc)
	 * @see client.models.IPlayer#setRoads(java.util.List)
	 */
	@Override
	public void setRoads(List<IRoad> roads) {
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

	/* (non-Javadoc)
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
	public void addRoad(IRoad road) {
		this.roads.add(road);
	}

	public List<ICity> getCities() {
		return cities;
	}

	public void setCities(List<ICity> cities) {
		this.cities = cities;
	}
}
