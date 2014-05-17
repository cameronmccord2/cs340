/**
 * 
 */
package client.models;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.exceptions.InvalidGameModelException;
import client.models.exceptions.InvalidTranslatorModelException;
import client.models.translator.ClientModel;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.CreateGame;
import client.server.FinishedTurn;
import client.server.GameServer;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
import client.server.PlayerServer;
import client.server.RoadBuilding;
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;
import client.server.ServerChat;
import client.server.ServerJoinGame;
import client.server.ServerLogLevel;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerRobPlayer;
import client.server.ServerRoll;
import client.server.ServerSoldier;
import client.server.ServerYearofPlenty;
import client.server.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author scottdaly
 *
 */
public class DummyProxy implements IProxy{
	
	private Gson gson = new Gson();
	private Translator translator;
	private List<IGame> games;
	private String gameId = "0";
	private IFacade facade;
	
	public DummyProxy() {
		this.translator = new Translator();
		this.games = new ArrayList<IGame>();
		this.facade = new Facade(this);
	}
	
	private IGame saveGameModel(String model){
		Gson gson = new Gson();
		ClientModel cm = gson.fromJson(model, ClientModel.class);
		try {
			cm.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new RuntimeException(e.getMessage());
		}
		IGame g = null;
		try {
			g = this.translator.convertClientModelToGame(cm, this.getGameForGameId(Integer.parseInt(gameId)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < this.games.size(); i++) {
			if(this.games.get(i).getGameInfo().getId() == Integer.parseInt(this.gameId))
				this.games.set(i, g);
		}
		return g;
	}
	
	@Override
	public ServerResponse postUserLogin(User user){
		ServerResponse sr = new ServerResponse("Success",200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse postUserRegister(User user){
		ServerResponse sr = new ServerResponse("Success",200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse getGamesList(){
		String response = "[{'title':'Default Game',"
						  + "'id':0,"
						  + "'players':[{'color':'orange','name':'Sam','id':0},"
						  			 + "{'color':'blue','name':'Brooke','id':1},"
						  			 + "{'color':'red','name':'Pete','id':10},"
						  			 + "{'color':'green','name':'Mark','id':11}]},"
						  +"{'title':'AI Game',"
						  + "'id':1,"
						  + "'players':[{'color':'orange','name':'Pete','id':10},"
						  			 + "{'color':'blue','name':'Ken','id':-2},"
						  			 + "{'color':'yellow','name':'Steve','id':-2},"
						  			 + "{'color':'white','name':'Hannah','id':-2}]},"
			  			  +"{'title':'Empty Game',"
			  			  + "'id':2,"
			  			  + "'players':[{'color':'orange','name':'Sam','id':0},"
			  			  			 + "{'color':'blue','name':'Brooke','id':1},"
			  			  			 + "{'color':'red','name':'Pete','id':10},"
			  			  			 + "{'color':'green','name':'Mark','id':11}]}]";
		ServerResponse sr = new ServerResponse(response,200);
		List<GameServer> games = gson.fromJson(sr.getJson(), new TypeToken<List<GameServer>>(){}.getType());
		List<IGame> list = new ArrayList<IGame>();
		for (GameServer g : games) {
			IPlayer[] players = new IPlayer[g.getPlayers().length];
			int playerIndex = 0;
			for (PlayerServer p : g.getPlayers()) {
				PlayerInfo pi = new PlayerInfo();
				pi.setColor(CatanColor.getColorForName(p.getColor()));
				pi.setId(p.getId());
				pi.setName(p.getName());
				pi.setPlayerIndex(playerIndex);
				IPlayer player = new Player(pi);
				players[playerIndex] = player;
				playerIndex++;
			}
			Game game = new Game();
			game.setPlayers(players);
			
			GameInfo gi = new GameInfo();
			gi.setId(g.getId());
			gi.setTitle(g.getTitle());
			game.setGameInfo(gi);
			list.add(game);
		}
		this.games = list;
		return sr;
	}
	
	@Override
	public ServerResponse postGamesCreate(CreateGame game){
		ServerResponse sr = new ServerResponse("{'title':'MINE','id': 3,'players': [{},{},{},{}]}",200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse postGamesJoin(ServerJoinGame join){
		ServerResponse sr = new ServerResponse("Success",200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public IGame getGameModel(){
		ServerResponse sr = new ServerResponse(updateModel,200);
		return this.saveGameModel(sr.getJson());
	}
	
	private IGame getGameForGameId(Integer gameId) {
		for (IGame g : this.games) {
			if(g.getGameInfo().getId() == gameId)
				return g;
		}
		return null;
	}

	@SuppressWarnings("unused")
	private Integer getVersionForGameId(Integer gameId) throws InvalidGameModelException {
		for (IGame g : this.games) {
			if(g.getGameInfo().getId() == gameId)
				return g.getModelVersion();
		}
		throw new InvalidGameModelException("Couldnt find the requested game: " + gameId + " in the game list: " + this.games.toString());
	}

	@Override
	public ServerResponse postGameReset(){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSendChat(ServerChat chat){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRollNumber(ServerRoll roll){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse moveRobPlayer(ServerRobPlayer rob){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesFinishTurn(FinishedTurn turn){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuyDevCard(BuyDevCard card){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesYear_of_Plenty(ServerYearofPlenty yop){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRoad_Building(RoadBuilding rb){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSoldier(ServerSoldier ss){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonopoly(ServerMonopoly sm){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonument(ServerMonument sm){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildRoad(ServerBuildRoad br){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildSettlement(ServerBuildSettlement bs){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildCity(ServerBuildCity bc){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesOfferTrade(OfferTrade ot){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesAcceptTrade(AcceptTrade at){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMaritimeTrade(MaritimeTradeOff mTrade){
		ServerResponse sr = new ServerResponse(updateModel,200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesdiscardCards(PlayerInfo player){
		return null;
	}
	
	@Override
	public ServerResponse utilChangeLogLevel(ServerLogLevel loglevel){
		ServerResponse sr = new ServerResponse("Success",200);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	private String updateModel = "{ 'deck': { 'yearOfPlenty': 2, "
										   + "'monopoly': 2, "
										   + "'soldier': 14, "
										   + "'roadBuilding': 2, "
										   + "'monument': 5 }, "
										   
							      + "'map': { 'hexes': [ { 'location': { 'x': 0, 'y': -2 } }, "
							      					  + "{ 'resource': 'brick', 'location': { 'x': 1, 'y': -2 }, 'number': 4 }, "
							      					  + "{ 'resource': 'wood', 'location': { 'x': 2, 'y': -2 }, 'number': 11 }, "
							      					  + "{ 'resource': 'brick', 'location': { 'x': -1, 'y': -1 }, 'number': 8 }, "
							      					  + "{ 'resource': 'wood', 'location': { 'x': 0, 'y': -1 }, 'number': 3 }, "
							      					  + "{ 'resource': 'ore', 'location': { 'x': 1, 'y': -1 }, 'number': 9 }, "
							      					  + "{ 'resource': 'sheep', 'location': { 'x': 2, 'y': -1 }, 'number': 12 }, "
							      					  + "{ 'resource': 'ore', 'location': { 'x': -2, 'y': 0 }, 'number': 5 }, "
							      					  + "{ 'resource': 'sheep', 'location': { 'x': -1, 'y': 0 }, 'number': 10 }, "
							      					  + "{ 'resource': 'wheat', 'location': { 'x': 0, 'y': 0 }, 'number': 11 }, "
							      					  + "{ 'resource': 'brick', 'location': { 'x': 1, 'y': 0 }, 'number': 5 }, "
							      					  + "{ 'resource': 'wheat', 'location': { 'x': 2, 'y': 0 }, 'number': 6 }, "
							      					  + "{ 'resource': 'wheat', 'location': { 'x': -2, 'y': 1 }, 'number': 2 }, "
							      					  + "{ 'resource': 'sheep', 'location': { 'x': -1, 'y': 1 }, 'number': 9 }, "
							      					  + "{ 'resource': 'wood', 'location': { 'x': 0, 'y': 1 }, 'number': 4 }, "
							      					  + "{ 'resource': 'sheep', 'location': { 'x': 1, 'y': 1 }, 'number': 10 }, "
							      					  + "{ 'resource': 'wood', 'location': { 'x': -2, 'y': 2 }, 'number': 6 }, "
							      					  + "{ 'resource': 'ore', 'location': { 'x': -1, 'y': 2 }, 'number': 3 }, "
							      					  + "{ 'resource': 'wheat', 'location': { 'x': 0, 'y': 2 }, 'number': 8 } ], "
							      					  
					      				   + "'roads': [ { 'owner': 2, 'location': { 'direction': 'S', 'x': 1, 'y': -1 } }, "
					      				   			  + "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': 2, 'y': -2 } }, "
					      				   			  + "{ 'owner': 0, 'location': { 'direction': 'S', 'x': 0, 'y': 1 } }, "
					      				   			  + "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -2, 'y': 1 } }, "
					      				   			  + "{ 'owner': 2, 'location': { 'direction': 'S', 'x': 0, 'y': 0 } }, "
					      				   			  + "{ 'owner': 0, 'location': { 'direction': 'SW', 'x': 2, 'y': 0 } }, "
					      				   			  + "{ 'owner': 1, 'location': { 'direction': 'S', 'x': -1, 'y': -1 } }, "
					      				   			  + "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': -1, 'y': 1 } } ], "
					      				   			  
		      				   			   + "'cities': [], "
		      				   			   
		      				   			   + "'settlements': [ { 'owner': 3, 'location': { 'direction': 'SE', 'x': 1, 'y': -2 } }, "
		      				   			   					+ "{ 'owner': 2, 'location': { 'direction': 'SW', 'x': 0, 'y': 0 } }, "
		      				   			   					+ "{ 'owner': 2, 'location': { 'direction': 'SW', 'x': 1, 'y': -1 } }, "
		      				   			   					+ "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -1, 'y': -1 } }, "
		      				   			   					+ "{ 'owner': 0, 'location': { 'direction': 'SE', 'x': 0, 'y': 1 } }, "
		      				   			   					+ "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -2, 'y': 1 } }, "
		      				   			   					+ "{ 'owner': 0, 'location': { 'direction': 'SW', 'x': 2, 'y': 0 } }, "
		      				   			   					+ "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': -1, 'y': 1 } } ], "
		      				   			   					
		   			   					   + "'radius': 3, "
		   			   					   
		   			   					   + "'ports': [ { 'ratio': 2, 'resource': 'wheat', 'direction': 'S', 'location': { 'x': -1, 'y': -2 } }, "
		   			   					   			  + "{ 'ratio': 3, 'direction': 'SW', 'location': { 'x': 3, 'y': -3 } }, "
		   			   					   			  + "{ 'ratio': 2, 'resource': 'ore', 'direction': 'S', 'location': { 'x': 1, 'y': -3 } }, "
		   			   					   			  + "{ 'ratio': 3, 'direction': 'SE', 'location': { 'x': -3, 'y': 0 } }, "
		   			   					   			  + "{ 'ratio': 2, 'resource': 'wood', 'direction': 'NE', 'location': { 'x': -3, 'y': 2 } }, "
		   			   					   			  + "{ 'ratio': 2, 'resource': 'brick', 'direction': 'NE', 'location': { 'x': -2, 'y': 3 } }, "
		   			   					   			  + "{ 'ratio': 3, 'direction': 'NW', 'location': { 'x': 2, 'y': 1 } }, "
		   			   					   			  + "{ 'ratio': 2, 'resource': 'sheep', 'direction': 'NW', 'location': { 'x': 3, 'y': -1 } }, "
		   			   					   			  + "{ 'ratio': 3, 'direction': 'N', 'location': { 'x': 0, 'y': 3 } } ], "
		   			   					   			  
		   					   			   + "'robber': { 'x': 0, 'y': -2 } }, "
		   					   			   
		   					   			   + "'players': [ { 'resources': { 'brick': 0, 'wood': 1, 'sheep': 1, 'wheat': 1, 'ore': 0 }, "
		   					   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'roads': 13, "
		   					   			   				  + "'cities': 4, "
		   					   			   				  + "'settlements': 3, "
		   					   			   				  + "'soldiers': 0, "
		   					   			   				  + "'victoryPoints': 2, "
		   					   			   				  + "'monuments': 0, "
		   					   			   				  + "'playedDevCard': false, "
		   					   			   				  + "'discarded': false, "
		   					   			   				  + "'playerID': 0, "
		   					   			   				  + "'playerIndex': 0, "
		   					   			   				  + "'name': 'Sam', "
		   					   			   				  + "'color': 'orange' }, "
		   					   			   				+ "{ 'resources': { 'brick': 1, 'wood': 0, 'sheep': 1, 'wheat': 0, 'ore': 1 }, "
		   					   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'roads': 13, "
		   					   			   				  + "'cities': 4, "
		   					   			   				  + "'settlements': 3, "
		   					   			   				  + "'soldiers': 0, "
		   					   			   				  + "'victoryPoints': 2, "
		   					   			   				  + "'monuments': 0, "
		   					   			   				  + "'playedDevCard': false, "
		   					   			   				  + "'discarded': false, "
		   					   			   				  + "'playerID': 1, "
		   					   			   				  + "'playerIndex': 1, "
		   					   			   				  + "'name': 'Brooke', "
		   					   			   				  + "'color': 'blue' }, "
		   					   			   				+ "{ 'resources': { 'brick': 0, 'wood': 1, 'sheep': 1, 'wheat': 1, 'ore': 0 }, "
		   					   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'roads': 13, "
		   					   			   				  + "'cities': 4, "
		   					   			   				  + "'settlements': 3, "
		   					   			   				  + "'soldiers': 0, "
		   					   			   				  + "'victoryPoints': 2, "
		   					   			   				  + "'monuments': 0, "
		   					   			   				  + "'playedDevCard': false, "
		   					   			   				  + "'discarded': false, "
		   					   			   				  + "'playerID': 10, "
		   					   			   				  + "'playerIndex': 2, "
		   					   			   				  + "'name': 'Pete', "
		   					   			   				  + "'color': 'red' }, "
		   					   			   				+ "{ 'resources': { 'brick': 0, 'wood': 1, 'sheep': 1, 'wheat': 0, 'ore': 1 }, "
		   					   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
		   					   			   				  + "'roads': 13, "
		   					   			   				  + "'cities': 4, "
		   					   			   				  + "'settlements': 3, "
		   					   			   				  + "'soldiers': 0, "
		   					   			   				  + "'victoryPoints': 2, "
		   					   			   				  + "'monuments': 0, "
		   					   			   				  + "'playedDevCard': false, "
		   					   			   				  + "'discarded': false, "
		   					   			   				  + "'playerID': 11, "
		   					   			   				  + "'playerIndex': 3, "
		   					   			   				  + "'name': 'Mark', "
		   					   			   				  + "'color': 'green' } ], "
		   					   			   				  
			   			   				   + "'log': { 'lines': [ { 'source': 'Sam', 'message': 'Sam built a road' }, "
			   			   				   					   + "{ 'source': 'Sam', 'message': 'Sam built a settlement' }, "
			   			   				   					   + "{ 'source': 'Sam', 'message': 'Sams turn just ended' }, "
			   			   				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a road' }, "
			   			   				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a settlement' }, "
			   			   				   					   + "{ 'source': 'Brooke', 'message': 'Brookes turn just ended' }, "
			   			   				   					   + "{ 'source': 'Pete', 'message': 'Pete built a road' }, "
			   			   				   					   + "{ 'source': 'Pete', 'message': 'Pete built a settlement' }, "
			   			   				   					   + "{ 'source': 'Pete', 'message': 'Petes turn just ended' }, "
			   			   				   					   + "{ 'source': 'Mark', 'message': 'Mark built a road' }, "
			   			   				   					   + "{ 'source': 'Mark', 'message': 'Mark built a settlement' }, "
			   			   				   					   + "{ 'source': 'Mark', 'message': 'Marks turn just ended' }, "
			   			   				   					   + "{ 'source': 'Mark', 'message': 'Mark built a road' }, "
			   			   				   					   + "{ 'source': 'Mark', 'message': 'Mark built a settlement' }, "
			   			   				   					   + "{ 'source': 'Mark', 'message': 'Marks turn just ended' }, "
			   			   				   					   + "{ 'source': 'Pete', 'message': 'Pete built a road' }, "
			   			   				   					   + "{ 'source': 'Pete', 'message': 'Pete built a settlement' }, "
			   			   				   					   + "{ 'source': 'Pete', 'message': 'Petes turn just ended' }, "
			   			   				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a road' }, "
			   			   				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a settlement' }, "
			   			   				   					   + "{ 'source': 'Brooke', 'message': 'Brookes turn just ended' }, "
			   			   				   					   + "{ 'source': 'Sam', 'message': 'Sam built a road' }, "
			   			   				   					   + "{ 'source': 'Sam', 'message': 'Sam built a settlement' }, "
			   			   				   					   + "{ 'source': 'Sam', 'message': 'Sams turn just ended' } ] }, "
			   			   				   					   
	   				   					   + "'chat': { 'lines': [] }, "
	   				   					   
	   				   					   + "'bank': { 'brick': 23, 'wood': 21, 'sheep': 20, 'wheat': 22, 'ore': 22 }, "
	   				   					   
	   				   					   + "'turnTracker': { 'status': 'Rolling', 'currentTurn': 0, 'longestRoad': -1, 'largestArmy': -1 }, "
	   				   					   
	   				   					   + "'winner': -1, "
	   				   					   
	   				   					   + "'version': 0 }";

	public List<IGame> getGames() {
		return games;
	}

	@Override
	public IFacade getFacade() {
		return this.facade;
	}
}
