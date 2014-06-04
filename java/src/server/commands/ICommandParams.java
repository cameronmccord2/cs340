package server.commands;

import server.commands.exceptions.CommandParamNotValidException;

/**
 * Generic class to hold the parameters that were sent in on the request to change the model.
 *
 * @author scottdaly
 */
public interface ICommandParams
{
	public void validate() throws CommandParamNotValidException;
	
//	public default boolean isValid() { return true; }
	
	public String getType();
}
