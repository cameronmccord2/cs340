package server.modelFacade;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IUserServerModelFacade {

	/**
	 * Logs in the requesting user
	 *
	 * @param json the json string of the username and password
	 * @param ua the user attributes, null here
	 * @return the string "Success"
	 */
	public String login(ICommandParams params, UserAttributes ua);
	
	/**
	 * Registers the requesting user as a game participant
	 *
	 * @param json the json of the user's username and password
	 * @param ua the user attributes, null here
	 * @return the string "Success"
	 */
	public String register(ICommandParams params, UserAttributes ua);
	
}
