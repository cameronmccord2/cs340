package client.models;

import java.util.HashSet;
import java.util.Set;

import shared.definitions.ResourceType;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModel;

public class Facade implements IFacade {
	
	private IProxy proxy;
	private Set<ICatanModelObserver> observers;
	private String playerName;
	
	public Facade(IProxy proxy){
		this.proxy = proxy;
		this.observers = new HashSet<ICatanModelObserver>();
	}
	
	private IGame getGameModel() throws CantFindGameModel{
		Integer gameId = Integer.parseInt(this.proxy.getGameId());
		for (IGame g : this.proxy.getGames()) {
			if(g.getGameInfo().getId() == gameId){
				return g;
			}
		}
		throw new CantFindGameModel();
	}
	
	private IPlayer getCurrentUser(){
		try {
			IGame g = this.getGameModel();
			for (IPlayer p : g.getPlayers()) {
				if(p.getPlayerInfo().getName().equals(this.playerName))
					return p;
			}
			
		} catch (CantFindGameModel e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void registerAsObserver(ICatanModelObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void updatedCatanModel() {
		for (ICatanModelObserver o : this.observers) {
			o.update();
		}
	}

	@Override
	public Integer getPlayerResourceCount(ResourceType resource) {
		return this.getCurrentUser().getResourceCards().get(resource);
	}

	@Override
	public void setCurrentUser(String usersName) {
		this.playerName = usersName;
	}
	
	
}
