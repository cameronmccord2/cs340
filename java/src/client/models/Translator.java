/**
 * 
 */
package client.models;
import client.models.translator.ClientModel;

/**
 * This class receives either JSON or Java objects and converts it to the other format
 * @author scottdaly
 *
 */
public class Translator {

	public Translator() {}
	
	public IGame convertClientModelToGame(ClientModel cm){
		Game g = new Game();
		// TODO
		return g;
	}
}
