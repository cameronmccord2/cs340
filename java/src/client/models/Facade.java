package client.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;

import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.communication.LogEntry;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRTradeOffer;
import shared.locations.HexLocation;

public class Facade implements IFacade {
	
	private IProxy proxy;
	private Set<ICatanModelObserver> observers;
	private String playerName;
	
	public Facade(IProxy proxy){
		this.proxy = proxy;
		this.observers = new HashSet<ICatanModelObserver>();
	}
	
	private IGame getGameModel() throws CantFindGameModelException{
        //System.out.println("GAMEID: " + this.proxy.getGameId());

        // Must have a default Integer for parseInt. getGameId() returns null
        // before a game is chosen.
        Integer gameId;
       
        if(this.proxy.getGameId() == null)
            gameId = 0;
		else
            gameId = Integer.parseInt(this.proxy.getGameId());
     
		for (IGame g : this.proxy.getGames()) {
			if(g.getGameInfo().getId() == gameId){
				return g;
			}
		}
		throw new CantFindGameModelException();
	}
	
	@Override
	public ICatanMap getCatanMap() throws CantFindGameModelException
	{
		return this.getGameModel().getMap();
	}
	
	@Override
	public IPlayer getCurrentUser() throws CantFindPlayerException, CantFindGameModelException{
		IGame g = this.getGameModel();
		for (IPlayer p : g.getPlayers()) {
			if(p.getPlayerInfo().getName().equals(this.playerName))
				return p;
		}
		throw new CantFindPlayerException("sorry");
	}
	
	@Override
	public void registerAsObserver(ICatanModelObserver observer) {
		System.out.println("registering: " + observer.getClass().toString());
		this.observers.add(observer);
	}

	@Override
	public void updatedCatanModel() {
		try {
			System.out.println(this.getCurrentState());
		} catch(CantFindGameModelException e) {
			e.printStackTrace();
		}
		
		for (ICatanModelObserver o : this.observers) {
			o.update();
		}
	}

	@Override
	public Integer getPlayerResourceCount(ResourceCard resource) throws CantFindGameModelException {
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
	public Integer getBankResourceCount(ResourceCard resource) throws CantFindGameModelException {
		Integer count = this.getGameModel().getBank().getResourceCards().get(resource);
		if(count != null)
			return count;
		return 0;
	}

	@Override
	public void setCurrentUser(String usersName) {
		this.playerName = usersName;
	}

	@Override
	public IPlayer getPlayerWithIndex(Integer playerIndex) throws CantFindPlayerException, CantFindGameModelException {
		IGame g = this.getGameModel();
		for (IPlayer p : g.getPlayers()) {
			if(p.getPlayerInfo().getName().equals(this.playerName))
				return p;
		}
		throw new CantFindPlayerException("Cant find player by index: " + playerIndex);
	}

	@Override
	public PlayerInfo[] getAllPlayerInfos() throws CantFindGameModelException {
		if(this.getGameModel().getPlayers() != null){
			PlayerInfo[] pi = new PlayerInfo[this.getGameModel().getPlayers().length];
			for (int i = 0; i < this.getGameModel().getPlayers().length; i++) {
				pi[i] = this.getGameModel().getPlayers()[i].getPlayerInfo();
			}
			return pi;
		}
		return null;
	}

	@Override
	public boolean isMyTurn() throws CantFindGameModelException {
		try {
			IPlayer currentUser = this.getCurrentUser();
			return (this.getGameModel().getTurnTracker().currentTurn == currentUser.getPlayerInfo().getPlayerIndex());
		} catch (CantFindPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public TRTradeOffer getCurrentTrade() throws CantFindGameModelException {
		return this.getGameModel().getCurrentTrade();
	}

	@Override
	public Integer getCurrentUserIndex() throws CantFindGameModelException {
		try {
			return this.getCurrentUser().getPlayerInfo().getPlayerIndex();
		} catch (CantFindPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Map<IResourceCard, Integer> getResourcesForPlayerId(Integer playerId) throws CantFindPlayerException, CantFindGameModelException {
		return this.getPlayerWithIndex(playerId).getResourceCards();
	}
	
	@Override
	public Map<IDevelopmentCard, Integer> getDevCardsForPlayerId(Integer playerId) throws CantFindPlayerException, CantFindGameModelException {
		return this.getPlayerWithIndex(playerId).getDevelopmentCards();
	}

	@Override
	public List<LogEntry> getChats() {
		try {
			IGame game = getGameModel();
			List<MessageLine> list = game.getChat().getLines();
			PlayerInfo[] players = getAllPlayerInfos(); 
			List<LogEntry> chatList = new ArrayList<LogEntry>();
			for(MessageLine l : list){
				for(PlayerInfo p : players){
					if(l.getSource().equals(p.getName())){
						LogEntry chat = new LogEntry(p.getColor(),l.getMessage());
						chatList.add(chat);
					}
				}
			}
			return chatList;
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer getWinner() throws CantFindGameModelException {
		return this.getGameModel().getWinner();
	}

	@Override
	public TurnTracker getTurnTracker() throws CantFindGameModelException {
		return this.getGameModel().getTurnTracker();
	}
	
	@Override
	public String getCurrentState() throws CantFindGameModelException{
		return this.getGameModel().getTurnTracker().getStatus();
	}

	@Override
	public IPlayer[] getPlayers() throws CantFindGameModelException {
		return this.getGameModel().getPlayers();
	}

	@Override
	public List<LogEntry> getGameHistory() {
		try {
			IGame game = getGameModel();
			List<MessageLine> list = game.getLog().getLines();
			System.out.println("game log: " + list);
			PlayerInfo[] players = getAllPlayerInfos(); 
			List<LogEntry> logList = new ArrayList<LogEntry>();
			for(MessageLine l : list){
				for(PlayerInfo p : players){
					if(l.getSource().equals(p.getName())){
						LogEntry log = new LogEntry(p.getColor(),l.getMessage());
						logList.add(log);
					}
				}
			}
			return logList;
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer getMaritimeTradeAmountForResource(ResourceType resource) throws CantFindPlayerException, CantFindGameModelException {
		
		Integer tradeRatio = 4;
		
		Collection<IPort> ports = this.getCatanMap().getPorts();
		
		List<ISettlement> cities = new ArrayList<ISettlement>();
		cities.addAll(this.getCurrentUser().getCities());
		cities.addAll(this.getCurrentUser().getSettlements());
		
		for (ISettlement s : cities) {
			VertexLocation location = (VertexLocation)s.getLocation();

			List<HexLocation> hexes = new ArrayList<HexLocation>();
			
			if(location.getDirection() == VertexDirection.NorthEast)
			{
				HexLocation hex = location.getHexLocation();
				hexes.add(hex);
				
				HexLocation aboveHex = new HexLocation(hex.getX()+1, hex.getY()-1);
				hexes.add(aboveHex);
				
				HexLocation belowHex = new HexLocation(hex.getX()+1, hex.getY());
				hexes.add(belowHex);
			}
			else if(location.getDirection() == VertexDirection.NorthWest)
			{
				HexLocation hex = location.getHexLocation();
				hexes.add(hex);
				
				HexLocation aboveHex = new HexLocation(hex.getX()-1, hex.getY());
				hexes.add(aboveHex);
				
				HexLocation belowHex = new HexLocation(hex.getX()-1, hex.getY()+1);
				hexes.add(belowHex);
			}
			
			for (IPort p : ports) {
				PortType portType = p.getPortType();
				if(portType != PortType.THREE && portType != PortType.valueOf(resource.toString().toUpperCase())){
					continue;
				}
				
				for (HexLocation h : hexes) {
					if(p.getHexLocation().getX() == h.getX() && p.getHexLocation().getY() == h.getY()){
						if(p.getExchangeRate() < tradeRatio)
							tradeRatio = p.getExchangeRate();
					}
				}
			}
		}
		return tradeRatio;
	}

    @Override
    public HexLocation getRobberLocation() throws CantFindGameModelException {
        return this.getCatanMap().getRobber().getLocation().getHexLocation();
    }
}




































