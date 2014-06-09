/**
 * 
 */
package server.modelFacade;

import java.util.ArrayList;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.models.Bank;
import client.models.CatanMap;
import client.models.Game;
import client.models.MessageList;
import client.models.TurnTracker;
import client.models.interfaces.IPlayer;
import client.models.translator.TRTradeOffer;
import client.server.CreateGame;
import client.server.GameServer;
import client.server.ServerJoinGame;

import com.google.gson.Gson;

import server.commands.ICommandParams;
import server.models.GameList;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;
import shared.definitions.CatanColor;

public class GamesServerModelFacade extends ServerModelFacade implements IGamesServerModelFacade 
{
	public GamesServerModelFacade(GameList gameList) {
		super(gameList);
	}

	@Override
	public ServerFacadeResponse listGames(ICommandParams params, UserAttributes ua) 
	{
		ArrayList<GameServer> gInfos = gameList.getGameInfoList();
		Gson gson = new Gson();
		return new ServerFacadeResponse(false, gson.toJson(gInfos));
	}

	@Override
	public ServerFacadeResponse createGame(ICommandParams params, UserAttributes ua) 
	{
		//create a new default game
		CreateGame cGame = (CreateGame)params;
		
		Game newGame = new Game();
		
		//set up GameInfo object on Game object
		GameInfo gInfo = new GameInfo();
		gInfo.setId(gameList.getGames().size());
		gInfo.setTitle(cGame.getName());
		gInfo.setRandomNumbers(cGame.isRandomNumbers());
		gInfo.setRandomPorts(cGame.isRandomPorts());
		gInfo.setRandomTiles(cGame.isRandomTiles());
		newGame.setGameInfo(gInfo);
		
		//init map
		CatanMap newMap = new CatanMap();
		newMap.setupNewMap(cGame.isRandomNumbers(), cGame.isRandomPorts(), cGame.isRandomTiles());
		newGame.setMap(newMap);
		
		newGame.setPlayers(new IPlayer[4]);
		newGame.setVersion(0);
		
		Bank newBank = new Bank();
		newBank.setUpNewBank();
		newGame.setBank(newBank);
		
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
		newGame.setCurrentTrade(null);
		
		gameList.addGame(newGame);
System.out.println(gameList.getGames().toString());
		return new ServerFacadeResponse(false, "Success");
	}

	@Override
	public ServerFacadeResponse joinGame(ICommandParams params, UserAttributes ua) 
	{
		ServerJoinGame info = (ServerJoinGame)params;
		gameList.setCurrentUserChecking(ua.getPlayerID());
		int temp = gameList.checkForPlayer(info.getId());
		if(temp == -10)
		{
			//create a new player for the game
			GameInfo gInfo = gameList.getGInfo();
			PlayerInfo newPlayer = new PlayerInfo(ua.getPlayerID(),gInfo.getPlayers().size(),ua.getusername(), CatanColor.getColorForName(info.getColor()));
			return new ServerFacadeResponse(false, "" + gameList.addPlayer(newPlayer));
		}
		else
		{
			return new ServerFacadeResponse(false, "" + temp);
		}
	}

	@Override
	public ServerFacadeResponse loadGame(ICommandParams params, UserAttributes ua) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerFacadeResponse saveGame(ICommandParams params, UserAttributes ua) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
