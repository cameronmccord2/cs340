package persistence;

import java.util.List;

import client.models.Game;
import client.models.interfaces.IGame;

/**
 * Now that I think about it, we really don't need this class. It is
 * a level of abstraction that is unnecessary.  We just need to call
 * the proper methods on the IPlugin object after starting our server
 * and then pass them in to the proper classes to load.
 *
 * @author craig
 *
 */
public class PersistenceLoader
{
	private IPlugin plugin;

	public PersistenceLoader(IPlugin plugin)
	{
		this.plugin = plugin;
	}

	public IGame loadGame(int gameId)
	{
		return plugin.getGameById(gameId);
	}

	public List<IGame> loadGames()
	{
		return plugin.getGames();
	}
}
