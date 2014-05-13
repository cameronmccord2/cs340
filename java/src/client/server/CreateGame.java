/**
 * 
 */
package client.server;

/**
 * This class holds the needed attributes to be sent off to the server to create a new game
 * @author scottdaly
 *
 */
public class CreateGame {
	
	private boolean randomTiles, randomNumbers, randomPorts;
	private String title;
	
	public CreateGame(boolean randomTiles, boolean randomNumbers,
			boolean randomPorts, String title) {
		this.randomTiles = randomTiles;
		this.randomNumbers = randomNumbers;
		this.randomPorts = randomPorts;
		this.title = title;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
