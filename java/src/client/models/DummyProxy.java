/**
 * 
 */
package client.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public DummyProxy() {
		this.translator = new Translator();
		this.games = new ArrayList<IGame>();
	}
	
	private void saveGameModel(String model){
		Gson gson = new Gson();
		ClientModel cm = gson.fromJson(model, ClientModel.class);
		try {
			cm.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new RuntimeException(e.getMessage());
		}
		IGame g = this.translator.convertClientModelToGame(cm, this.getGameForGameId(Integer.parseInt(gameId)));
		this.translator.convertClientModelToGame(cm, g);
	}
	
	@Override
	public ServerResponse postUserLogin(User user){
		
		//saveGameModel(sr.getJson());
		Map<String, List<String>> map = connection.getHeaderFields();
		List<String> setCookie = map.get("Set-cookie");
		cookie = setCookie.get(0);
		cookie = cookie.substring(0, cookie.length() - 8);
		return sr;
	}
	
	@Override
	public ServerResponse postUserRegister(User user){
		ServerResponse sr = doPost("/user/register", gson.toJson(user));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public GameServer[] getGamesList(){
		ServerResponse sr = doGet("/games/list");
		List<GameServer> games = gson.fromJson(sr.getJson(), new TypeToken<List<GameServer>>(){}.getType());
		GameServer[] list = new GameServer[games.size()];
		int index = 0;
		for (GameServer g : games) {
			list[index] = g;
			index++;
		}
		return list;
	}
	
	@Override
	public ServerResponse postGamesCreate(CreateGame game){
		ServerResponse sr = doPost("/games/create", gson.toJson(game));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse postGamesJoin(ServerJoinGame join){
		ServerResponse sr = doJoinPost("/games/join", gson.toJson(join));
		//saveGameModel(sr.getJson());
		Map<String, List<String>> map = connection.getHeaderFields();
		List<String> setCookie = map.get("Set-cookie");
		gameId = setCookie.get(0);
		gameId = gameId.substring(0, gameId.length() - 8);
		return sr;
	}
	
	@Override
	public void getGameModel(){
		Integer version = 0;
		try {
			version = this.getVersionForGameId(Integer.parseInt(this.gameId));
		} catch (InvalidGameModelException e1) {
			// fail silently
		}
		String requestUrl = "/game/model";
		if(version != 0)
			requestUrl += "?version=" + version;
		ServerResponse sr = this.doGet(requestUrl);
		this.saveGameModel(sr.getJson());
	}
	
	private IGame getGameForGameId(Integer gameId) {
		for (IGame g : this.games) {
			if(g.getGameInfo().getId() == gameId)
				return g;
		}
		return null;
	}

	private Integer getVersionForGameId(Integer gameId) throws InvalidGameModelException {
		for (IGame g : this.games) {
			if(g.getGameInfo().getId() == gameId)
				return g.getModelVersion();
		}
		throw new InvalidGameModelException("Couldnt find the requested game: " + gameId + " in the game list: " + this.games.toString());
	}

	@Override
	public ServerResponse postGameReset(){
		ServerResponse sr = doMasterPost("/game/reset","");
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSendChat(ServerChat chat){
		ServerResponse sr = doMasterPost("/moves/sendChat", gson.toJson(chat));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRollNumber(ServerRoll roll){
		ServerResponse sr = doMasterPost("/moves/rollNumber", gson.toJson(roll));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse moveRobPlayer(ServerRobPlayer rob){
		ServerResponse sr = doMasterPost("/moves/robPlayer", gson.toJson(rob));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesFinishTurn(FinishedTurn turn){
		ServerResponse sr = doMasterPost("/moves/finishTurn", gson.toJson(turn));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuyDevCard(BuyDevCard card){
		ServerResponse sr = doMasterPost("/moves/buyDevCard", gson.toJson(card));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesYear_of_Plenty(ServerYearofPlenty yop){
		ServerResponse sr = doMasterPost("/moves/Year_of_Plenty", gson.toJson(yop));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRoad_Building(RoadBuilding rb){
		ServerResponse sr = doMasterPost("/moves/Road_Building", gson.toJson(rb));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSoldier(ServerSoldier ss){
		ServerResponse sr = doMasterPost("/moves/Soldier", gson.toJson(ss));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonopoly(ServerMonopoly sm){
		ServerResponse sr = doMasterPost("/moves/Monopoly", gson.toJson(sm));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonument(ServerMonument sm){
		ServerResponse sr = doMasterPost("/moves/Monument", gson.toJson(sm));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildRoad(ServerBuildRoad br){
		ServerResponse sr = doMasterPost("/moves/buildRoad", gson.toJson(br));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildSettlement(ServerBuildSettlement bs){
		ServerResponse sr = doMasterPost("/moves/buildSettlement", gson.toJson(bs));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildCity(ServerBuildCity bc){
		ServerResponse sr = doMasterPost("/moves/buildCity", gson.toJson(bc));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesOfferTrade(OfferTrade ot){
		ServerResponse sr = doMasterPost("/moves/offerTrade", gson.toJson(ot));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesAcceptTrade(AcceptTrade at){
		ServerResponse sr = doMasterPost("/moves/acceptTrade", gson.toJson(at));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMaritimeTrade(MaritimeTradeOff mTrade){
		ServerResponse sr = doMasterPost("/moves/maritimeTrade", gson.toJson(mTrade));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesdiscardCards(PlayerInfo player){
		return null;
	}
	
	@Override
	public ServerResponse utilChangeLogLevel(ServerLogLevel loglevel){
		ServerResponse sr = doMasterPost("/util/changeLogLevel", gson.toJson(loglevel));
		//saveGameModel(sr.getJson());
		return sr;
	}
}
