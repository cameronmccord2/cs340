/**
 * 
 */
package client.server;

import server.commands.ICommandParams;

/**
 * This class holds the needed attributes to be sent off to the server to create a new game
 * @author scottdaly
 *
 */
public class CreateGame  implements ICommandParams{
	
	private boolean randomTiles, randomNumbers, randomPorts;
	private String name;
	
	public CreateGame(boolean randomTiles, boolean randomNumbers,
			boolean randomPorts, String name) {
		this.randomTiles = randomTiles;
		this.randomNumbers = randomNumbers;
		this.randomPorts = randomPorts;
		this.name = name;
	}
	public boolean isRandomTiles() {
		return randomTiles;
	}
	public void setRandomTiles(boolean randomTiles) {
		this.randomTiles = randomTiles;
	}
	public boolean isRandomNumbers() {
		return randomNumbers;
	}
	public void setRandomNumbers(boolean randomNumbers) {
		this.randomNumbers = randomNumbers;
	}
	public boolean isRandomPorts() {
		return randomPorts;
	}
	public void setRandomPorts(boolean randomPorts) {
		this.randomPorts = randomPorts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreateGame [randomTiles=" + randomTiles + ", randomNumbers="
				+ randomNumbers + ", randomPorts=" + randomPorts + ", name="
				+ name + "]";
	}
	
	

}
