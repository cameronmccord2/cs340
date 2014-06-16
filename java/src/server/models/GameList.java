package server.models;

import java.util.ArrayList;
import java.util.List;

import client.models.Bank;
import client.models.CatanMap;
import client.models.Game;
import client.models.MessageList;
import client.models.Player;
import client.models.RoadSegment;
import client.models.Settlement;
import client.models.TurnTracker;
import client.models.interfaces.*;
import server.models.exceptions.InvalidUserAttributesException;
import shared.definitions.CatanColor;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.models.translator.TRTradeOffer;
import client.server.GameServer;
import client.server.PlayerServer;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.locations.*;

public class GameList {
	
	private ArrayList<Game> games;
	private int playerID;
	private Game selectedGame;
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
		PlayerInfo p4 = new PlayerInfo(3,3,"Uhura",CatanColor.RED);
		gInfo.addPlayer(p1);
		gInfo.addPlayer(p2);
		gInfo.addPlayer(p3);
		gInfo.addPlayer(p4);
		newGame.setGameInfo(gInfo);
		
		//init map
		CatanMap newMap = new CatanMap();
		newMap.setupNewMap(gInfo.isRandomTiles(), gInfo.isRandomNumbers(), gInfo.isRandomPorts());
		newGame.setMap(newMap);
		
		//set players
		Player player1 = new Player(p1);
		Player player2 = new Player(p2);
		Player player3 = new Player(p3);
		Player player4 = new Player(p4);
		ArrayList<IPlayer> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		newGame.setPlayers(players.toArray(new IPlayer[players.size()]));
		
		newGame.setVersion(0);
		newGame.setModelVersion(0);
		
		//init the bank
		Bank newBank = new Bank();
		newBank.setUpNewBank();
		newGame.setBank(newBank);
		
		newGame.setChat(new MessageList());
		newGame.setLog(new MessageList());
		
		//set turn tracker
		TurnTracker tt = new TurnTracker();
		tt.setCurrentTurn(0);
		//tt.setStatus("FirstRound");
		tt.setStatus("Rolling");
		tt.setLongestRoad(-1);
		tt.setLargestArmy(-1);
		newGame.setTurnTracker(tt);
		
		newGame.setWinner(-1);
		newGame.setCurrentTrade(null);

		// Default Game, set default pieces
		// Doesn't belong here, but we have less than an hour to turn in something that can run...
		try {
			player1.setVictoryPoints(2);
			player2.setVictoryPoints(2);
			player3.setVictoryPoints(2);
			player4.setVictoryPoints(2);

			player1.incrementResourceByCount(HexType.ORE,10);
			player1.incrementResourceByCount(HexType.WHEAT,10);
			player1.incrementResourceByCount(HexType.WOOD,10);
			player1.incrementResourceByCount(HexType.SHEEP,10);
			player1.incrementResourceByCount(HexType.BRICK,10);

			IRoadSegment segment1a = new RoadSegment();
			IRoadSegment segment1b = new RoadSegment();
			segment1a.setPlayer(player1);
			segment1b.setPlayer(player1);
			segment1a.setLocation(new EdgeLocation(new HexLocation(-2,1), EdgeDirection.SouthEast));
			segment1b.setLocation(new EdgeLocation(new HexLocation(-1, 2), EdgeDirection.South));
			newGame.getMap().placeInitialRoadSegment(segment1a);
			newGame.getMap().placeInitialRoadSegment(segment1b);


			IRoadSegment segment2a = new RoadSegment();
			IRoadSegment segment2b = new RoadSegment();
			segment2a.setPlayer(player2);
			segment2b.setPlayer(player2);
			segment2a.setLocation(new EdgeLocation(new HexLocation(0,2), EdgeDirection.North));
			segment2b.setLocation(new EdgeLocation(new HexLocation(0, -2), EdgeDirection.South));
			newGame.getMap().placeInitialRoadSegment(segment2a);
			newGame.getMap().placeInitialRoadSegment(segment2b);


			IRoadSegment segment3a = new RoadSegment();
			IRoadSegment segment3b = new RoadSegment();
			segment3a.setPlayer(player3);
			segment3b.setPlayer(player3);
			segment3a.setLocation(new EdgeLocation(new HexLocation(0,-1), EdgeDirection.SouthEast));
			segment3b.setLocation(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthEast));
			newGame.getMap().placeInitialRoadSegment(segment3a);
			newGame.getMap().placeInitialRoadSegment(segment3b);


			IRoadSegment segment4a = new RoadSegment();
			IRoadSegment segment4b = new RoadSegment();
			segment4a.setPlayer(player4);
			segment4b.setPlayer(player4);
			segment4a.setLocation(new EdgeLocation(new HexLocation(2,-1), EdgeDirection.North));
			segment4b.setLocation(new EdgeLocation(new HexLocation(2, 0), EdgeDirection.NorthWest));
			newGame.getMap().placeInitialRoadSegment(segment4a);
			newGame.getMap().placeInitialRoadSegment(segment4b);

			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(-2,1), VertexDirection.SouthEast),player1));
			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(-1,2), VertexDirection.SouthEast),player1));

			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(0,2), VertexDirection.NorthEast),player2));
			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(0,-2), VertexDirection.SouthWest),player2));

			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(0,-1), VertexDirection.SouthEast),player3));
			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(0,0), VertexDirection.SouthEast),player3));

			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(2,-1), VertexDirection.NorthEast),player4));
			newGame.getMap().placeInitialSettlement(new Settlement(new VertexLocation(new HexLocation(2,0), VertexDirection.NorthWest),player4));

		} catch (InvalidLocationException e) {
			e.printStackTrace();
		}

		games.add(newGame);
	}

	public IGame getGameById(Integer gameId) throws InvalidUserAttributesException {
		//System.out.println(gameId);
		//System.out.println(this.games.toString());
		for (IGame game : this.games) {
			if(game.getGameInfo().getId() == gameId.intValue())
				return game;
		}
		throw new InvalidUserAttributesException("Game cant be located by id: " + gameId);
	}
	
	public ArrayList<GameServer> getGameInfoList(){
		ArrayList<GameServer> gInfos = new ArrayList<>();
		for(Game g : this.games){
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
		for(Game g : this.games){
			gInfo = g.getGameInfo();
			selectedGame = g;
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
		selectedGame.addPlayer(new Player(player));
		return gInfo.getId();
	}
	
	public void addGame(Game newGame){
		this.games.add(newGame);
	}
	
	public ArrayList<Game> getGames(){
		System.out.println(this.games.toString());
		return this.games;
	}

	public void setGames(List<IGame> gamess) {
		this.games = new ArrayList<>();
		for (IGame g : gamess) {
			this.games.add((Game) g);
		}
	}
}
