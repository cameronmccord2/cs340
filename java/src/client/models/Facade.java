package client.models;

import java.util.HashSet;
import java.util.Set;

import client.models.exceptions.CantFindGameModel;

public class Facade implements IFacade {
	
	private IProxy proxy;
	private Set<ICatanModelObserver> observers;
	
	public Facade(IProxy proxy){
		this.proxy = proxy;
		this.observers = new HashSet<ICatanModelObserver>();
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
	
	private IGame getGameModel() throws CantFindGameModel{
		Integer gameId = Integer.parseInt(this.proxy.getGameId());
		for (IGame g : this.proxy.getGames()) {
			if(g.getGameInfo().getId() == gameId){
				return g;
			}
		}
		throw new CantFindGameModel();
	}
}
