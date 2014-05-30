package server.facades;

import server.commands.CommandResponse;
import server.models.UserAttributes;

/**
 * The Interface IUserFacade.
 */
public interface IUserFacade extends ICommandCreationFacade {

	/**
	 * Logs in the requesting user
	 *
	 * @param json the json string of the username and password
	 * @param ua the user attributes, null here
	 * @return the string "Success"
	 */
	public CommandResponse login(String json, UserAttributes ua);
	
	/**
	 * Registers the requesting user as a game participant
	 *
	 * @param json the json of the user's username and password
	 * @param ua the user attributes, null here
	 * @return the string "Success"
	 */
	public CommandResponse register(String json, UserAttributes ua);
	
}
