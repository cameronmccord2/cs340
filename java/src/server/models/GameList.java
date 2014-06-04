package server.models;

import java.util.ArrayList;

import server.models.exceptions.InvalidUserAttributesException;
import shared.definitions.CatanColor;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.models.Game;
import client.models.IGame;
import client.models.IPlayer;
import client.models.MessageList;
import client.models.Player;
import client.models.TurnTracker;
import client.models.translator.TRTradeOffer;
import client.server.GameServer;
import client.server.PlayerServer;

public class GameList {
	
	private ArrayList<Game> games;
	private int playerID;
	private GameInfo gInfo;

	public GameList(){
		super();
		this.games = new ArrayList<>();
		
		//create practice game
		Game newGame = new Game();
		
		//add GameInfo
		GameInfo gInfo = new GameInfo();
		gInfo.setId(42);
		gInfo.setTitle("Star Trek");
		PlayerInfo p1 = new PlayerInfo(0,0,"Kirk",CatanColor.YELLOW);
		PlayerInfo p2 = new PlayerInfo(1,1,"Spock",CatanColor.BLUE);
		PlayerInfo p3 = new PlayerInfo(2,2,"McCoy",CatanColor.ORANGE);
		//PlayerInfo p4 = new PlayerInfo(3,3,"Uhura",CatanColor.RED);
		gInfo.addPlayer(p1);
		gInfo.addPlayer(p2);
		gInfo.addPlayer(p3);
		//gInfo.addPlayer(p4);
		newGame.setGameInfo(gInfo);
		
		//init map
		//newGame.setMap(map);
		
		//set players
		Player player1 = new Player(p1);
		Player player2 = new Player(p2);
		Player player3 = new Player(p3);
		ArrayList<IPlayer> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		newGame.setPlayers(players.toArray(new IPlayer[players.size()]));
		
		newGame.setVersion(0);
		
		//init the bank
		//newGame.setBank(bank);
		
		newGame.setChat(new MessageList());
		newGame.setLog(new MessageList());
		
		//set turn tracker
		TurnTracker tt = new TurnTracker();
		tt.setCurrentTurn(0);
		tt.setStatus("FirstRound");
		tt.setLongestRoad(-1);
		tt.setLargestArmy(-1);
		newGame.setTurnTracker(tt);
		
		newGame.setWinner(-1);
		newGame.setCurrentTrade(new TRTradeOffer());
		
		games.add(newGame);
	}

	public IGame getGameById(Integer gameId) throws InvalidUserAttributesException {
		for (IGame game : this.games) {
			if(game.getGameInfo().getId() == gameId.intValue())
				return game;
		}
		throw new InvalidUserAttributesException("Game cant be located by id: " + gameId);
	}
	
	public ArrayList<GameServer> getGameInfoList(){
		ArrayList<GameServer> gInfos = new ArrayList<>();
		for(Game g : games){
			ArrayList<PlayerServer> players = new ArrayList<>();
			for(PlayerInfo p :g.getGameInfo().getPlayers()){
				PlayerServer ps = new PlayerServer(p.getColor().toString(),p.getName(),p.getId());
				players.add(ps);
			}
			GameInfo gInfo = g.getGameInfo();
			GameServer add = new GameServer(gInfo.getTitle(), gInfo.getId(), players.toArray(new PlayerServer[players.size()]));
			gInfos.add(add);
		}	
		return gInfos;
	}	
	
	public void setCurrentUserChecking(int playerID){
		this.playerID = playerID;
	}
	
	public GameInfo getGInfo(){
		return gInfo;
	}
	
	public int checkForPlayer(int gameId){
		for(Game g : games){
			gInfo = g.getGameInfo();
			if(gInfo.getId() == gameId){
				for(PlayerInfo p : gInfo.getPlayers()){
					if(p.getId() == this.playerID){
						return gInfo.getId();												
					}
				}
				//a new player needs to be added to this game
				return -10;
			}
		}
		return -1;
	}

	public int addPlayer(PlayerInfo player){
		gInfo.addPlayer(player);
		return gInfo.getId();
	}
	
	public ArrayList<Game> getGames(){
		return games;
	}
}
