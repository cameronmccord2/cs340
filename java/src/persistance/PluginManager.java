package persistance;

/**
 * This is called from the server.java and takes a paramter as to which persistance model to use.
 * It then parses the config file and creates a new instance of the appropriate storage persistance class.
 * @author scottdaly
 *
 */
public class PluginManager {
	
	
	/**
	 * Parses the config file based on the persistance type
	 * @param persistanceType that is specified from the command line.
	 */
	public void parseConfig(String persistanceType){
		
	}
	
	/**
	 * Calls initialize on the persistance object in order to start connections to storages
	 */
	public void initPersistance(){
		
	}
	
	/**
	 * Returns the newly created persistance object to be passed to the Server Model Facade
	 */
	public void getPersistanceObject(){
		
	}

}
