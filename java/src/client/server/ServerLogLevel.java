/**
 * 
 */
package client.server;

/**
 * Holds the state of the game to send to the server
 * @author scottdaly
 *
 */
public class ServerLogLevel {
	private String logLevel;

	public ServerLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the logLevel
	 */
	public String getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	
	
	
}
