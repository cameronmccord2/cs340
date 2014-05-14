/**
 * 
 */
package client.models;

import client.data.PlayerInfo;

/**
 * The Turn class is similar to the TurnTracker returned from the server. Marks who's turn it is and the status.
 * @author scottdaly
 *
 */
public class Turn{
	
	private int currentTurn;
	private String status;
	
	public Turn(int currentTurn, String status) {
		this.currentTurn = currentTurn;
		this.status = status;
	}

	/**
	 * @return the currentTurn
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @param currentTurn the currentTurn to set
	 */
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
