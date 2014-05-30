package server.commands;

/**
 * The Interface ITestCommand.
 */
public interface ITestCommand {
	
	/**
	 * Runs the appropriate commands to see that the command was successful
	 *
	 * @return true, if successful
	 */
	public boolean wasCommandSuccessful();
}
