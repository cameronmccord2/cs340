package client.models;

import java.util.ArrayList;
import java.util.List;

import client.data.PlayerInfo;

public class Player extends Participant implements IPlayer {
	
	protected List<ISettlement> settlements;
	protected List<IRoad> roads;
	protected PlayerInfo playerInfo;
	
	public Player(PlayerInfo playerInfo){
		super();
		this.playerInfo = playerInfo;
		this.settlements = new ArrayList<ISettlement>();
		this.roads = new ArrayList<IRoad>();
	}
	
	public Player(PlayerInfo playerInfo, List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards, List<ISettlement> settlements, List<IRoad> roads){
		super(developmentCards, resourceCards);
		this.playerInfo = playerInfo;
		this.settlements = settlements;
		this.roads = roads;
	}
	
	@Override
	public List<ISettlement> getSettlements() {
		return settlements;
	}
	@Override
	public void setSettlements(List<ISettlement> settlements) {
		this.settlements = settlements;
	}
	@Override
	public List<IRoad> getRoads() {
		return roads;
	}
	@Override
	public void setRoads(List<IRoad> roads) {
		this.roads = roads;
	}
	@Override
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	@Override
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
	@Override
	public List<IResourceCard> robPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean willAcceptTrade(ITrade trade) {
		// TODO Auto-generated method stub
		return false;
	}
}
