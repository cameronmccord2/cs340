/**
 * 
 */
package client.server;

/**
 * This is the object returned when you get games list
 * @author scottdaly
 *
 */
public class GameServer {
	
	private String title;
	private int id;
	private PlayerServer[] players;
	
	public GameServer(String title, int id, PlayerServer[] players) {
		this.title = title;
		this.id = id;
		this.players = players;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlayerServer[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerServer[] players) {
		this.players = players;
	}
	
	
	

}
