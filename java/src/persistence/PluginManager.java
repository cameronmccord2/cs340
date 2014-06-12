package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import client.models.User;

/**
 * This is called from the server.java and takes a parameter as to which persistence model to use.
 * It then parses the config file and creates a new instance of the appropriate storage persistence class.
 * @author scottdaly
 *
 */
public class PluginManager {
	
	private IPlugin plugin;
	private HashMap<String, ArrayList<String>> map;
	
	public PluginManager(){}
	/**
	 * Parses the config file based on the persistence type.
	 * 
	 * Parse file will contain the following information: path, name of plugin, name of plugin class
	 * 
	 * @param persistanceType that is specified from the command line.
	 */
	public void parseConfig(String configFile) {
		
	}
	
	/** 
	 * Parse file will contain the following information: name of plugin, path, name of plugin class
	 */
	@SuppressWarnings("resource")
	public void parseConfig() {
		File configFile = new File("config.txt");
		map = new HashMap<>();
		try {
			Scanner scanner = new Scanner(configFile);
			while(scanner.hasNext()){
				String persistenceType = scanner.next();
				ArrayList<String> pathAndClass = new ArrayList<>();
				pathAndClass.add(scanner.next());
				pathAndClass.add(scanner.next());
				map.put(persistenceType, pathAndClass);
			}
		} catch (FileNotFoundException e) {
			System.out.println("error reading config file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Calls initialize on the persistence object in order to start connections to storages
	 */
	public void getPersistenceObject() {
		
	}
		
	@SuppressWarnings("rawtypes")
	public IPlugin initPersistence(String persistenceType){
		Class c = null;
		try{
			String path = map.get(persistenceType).get(0);
			String className = map.get(persistenceType).get(1);
			c = Class.forName(path + "." + className);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		plugin = null;
		try{
			plugin = (IPlugin)c.newInstance();
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
		if(plugin == null){
			System.out.println("problem creating new plugin object in plugin manager");
		}
		return plugin;
	}

}
