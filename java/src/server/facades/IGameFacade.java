package server.facades;

import server.models.UserAttributes;

/**
 * The Interface IGameFacade.
 */
public interface IGameFacade extends IServerModelFacade {
	
	/**
	 * Gets the list of commands that have been execuded for the specified game.
	 *
	 * @param json the json
	 * @param ua the ua
	 * @return the commands
	 */
	public String getCommands(String json, UserAttributes ua);
	
	/**
	 * Run commands that are in the format that the getCommands endponit returns. Allows replaying of events.
	 *
	 * @param json the json
	 * @param ua the ua
	 * @return the string
	 */
	public String runCommands(String json, UserAttributes ua);
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param json the json
	 * @param ua the ua
	 * @return the string
	 */
	public String reset(String json, UserAttributes ua);
	
	/**
	 * Gets the game model that needs to be sent to the client for gameplay.
	 *
	 * @param json the json
	 * @param ua the ua
	 * @return the game model
	 */
	public String getGameModel(String json, UserAttributes ua);
	
}
