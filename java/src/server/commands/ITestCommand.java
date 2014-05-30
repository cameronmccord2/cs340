package server.commands;

/**
 * The Interface ITestCommand.
 */
public interface ITestCommand {
	
	/**
	 * Runs the appropriate commands to see that the command was sucessful
	 *
	 * @return true, if successful
	 */
	public boolean wasCommandSuccessful();
}
