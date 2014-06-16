package persistence;

import java.util.List;

import client.models.Game;
import client.models.interfaces.IGame;


public class PersistenceLoader
{
	private IPlugin plugin;

	public PersistenceLoader(IPlugin plugin)
	{
		this.plugin = plugin;
	}

	public IGame loadGame(Integer gameId)
	{
		IGame game = null;
		if(gameId == null)
			game = new Game();
		else
		{
			for(IGame g : loadGames())
			{
				if(g.getGameInfo().getId() == gameId.intValue())
				{
					game = g;
					break;
				}
			}
		}
		return game;
	}

	public List<IGame> loadGames()
	{
		return plugin.getGames();
	}
}
