package client.models;

import java.util.HashSet;
import java.util.Set;

import shared.definitions.ResourceType;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;

public class Facade implements IFacade {
	
	private IProxy proxy;
	private Set<ICatanModelObserver> observers;
	private String playerName;
	
	public Facade(IProxy proxy){
		this.proxy = proxy;
		this.observers = new HashSet<ICatanModelObserver>();
	}
	
	private IGame getGameModel() throws CantFindGameModelException{
		Integer gameId = Integer.parseInt(this.proxy.getGameId());
		for (IGame g : this.proxy.getGames()) {
			if(g.getGameInfo().getId() == gameId){
				return g;
			}
		}
		throw new CantFindGameModelException();
	}
	
	@Override
	public IPlayer getCurrentUser() throws CantFindPlayerException{
		try {
			IGame g = this.getGameModel();
			for (IPlayer p : g.getPlayers()) {
				if(p.getPlayerInfo().getName().equals(this.playerName))
					return p;
			}
			
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
		throw new CantFindPlayerException("Cant find player by name: " + this.playerName);
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
		try {
			Integer count = this.getCurrentUser().getResourceCards().get(resource);
			if(count == null)
				return 0;
			return count;
		} catch (CantFindPlayerException e) {
			throw new RuntimeException("Couldnt find player: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void setCurrentUser(String usersName) {
		this.playerName = usersName;
	}

	@Override
	public IParticipant getPlayerWithIndex(Integer playerIndex) throws CantFindPlayerException {
		try {
			IGame g = this.getGameModel();
			for (IPlayer p : g.getPlayers()) {
				if(p.getPlayerInfo().getName().equals(this.playerName))
					return p;
			}
			
		} catch (CantFindGameModelException e) {
			throw new RuntimeException("Cant find game model: " + e.getLocalizedMessage());
		}
		throw new CantFindPlayerException("Cant find player by index: " + playerIndex);
	}

	@Override
	public PlayerInfo[] getAllPlayerInfos() {
		try {
			PlayerInfo[] pi = new PlayerInfo[this.getGameModel().getPlayers().length];
			for (int i = 0; i < this.getGameModel().getPlayers().length; i++) {
				pi[i] = this.getGameModel().getPlayers()[i].getPlayerInfo();
			}
			return pi;
			
		} catch (CantFindGameModelException e) {
			throw new RuntimeException("cant find game model, getAllPlayerInfos, " + e.getLocalizedMessage());
		}
	}

	@Override
	public boolean isMyTurn() {
		try {
			IPlayer currentUser = this.getCurrentUser();
			return (this.getGameModel().getTurnTracker().currentTurn == currentUser.getPlayerInfo().getPlayerIndex());
		} catch (CantFindPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CantFindGameModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
