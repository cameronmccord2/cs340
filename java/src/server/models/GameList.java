package server.models;

import java.util.ArrayList;

import server.models.exceptions.InvalidUserAttributesException;
import client.models.Game;
import client.models.IGame;

public class GameList {
	
	private ArrayList<Game> games;

	public GameList(){
		super();
		this.games = new ArrayList<>();
	}

	public IGame getGameById(Integer gameId) throws InvalidUserAttributesException {
		for (IGame game : this.games) {
			if(game.getGameInfo().getId() == gameId)
				return game;
		}
		throw new InvalidUserAttributesException("Game cant be located by id: " + gameId);
	}

}
