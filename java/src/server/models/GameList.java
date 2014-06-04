package server.models;

import java.util.ArrayList;
import java.util.List;

import server.models.exceptions.InvalidUserAttributesException;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.models.Game;
import client.models.IGame;
import client.server.GameServer;
import client.server.PlayerServer;

public class GameList {
	
	private ArrayList<Game> games;

	public GameList(){
		super();
		this.games = new ArrayList<>();
		Game newGame = new Game();
		GameInfo gInfo = new GameInfo();
		gInfo.setId(100);
		gInfo.setTitle("MINE");
		newGame.setGameInfo(gInfo);
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

}
