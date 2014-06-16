package client.data;

import java.io.Serializable;
import java.util.*;


/**
 * Used to pass game information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * 	<li>Id: Unique game ID</li>
 * 	<li>Title: Game title (non-empty string)</li>
 * 	<li>Players: List of players who have joined the game (can be empty)</li>
 * </ul>
 * 
 */
public class GameInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8685416104973601017L;
	private int id;
	private String title;
	private boolean randomTiles, randomNumbers, randomPorts;
	private List<PlayerInfo> players;

	public GameInfo() {
		setId(-1);
		setTitle("");
		randomTiles = false;
		randomNumbers = false;
		randomPorts = false;
		players = new ArrayList<PlayerInfo>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer) {
		
		players.add(newPlayer);
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

	public List<PlayerInfo> getPlayers() {
		
		return Collections.unmodifiableList(players);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameInfo [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", randomTiles=");
		builder.append(randomTiles);
		builder.append(", randomNumbers=");
		builder.append(randomNumbers);
		builder.append(", randomPorts=");
		builder.append(randomPorts);
		builder.append(", players=");
		builder.append(players);
		builder.append("]");
		return builder.toString();
	}

}

