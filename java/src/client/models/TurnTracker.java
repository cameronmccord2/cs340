package client.models;

import client.models.translator.TRTurnTracker;

public class TurnTracker {
	protected Integer currentTurn;
	protected String status;
	protected Integer longestRoad;
	protected Integer largestArmy;
	
	public TurnTracker(TRTurnTracker turnTracker) {
		this.currentTurn = turnTracker.getCurrentTurn();
		this.status = turnTracker.getStatus();
		this.longestRoad = turnTracker.getLongestRoad();
		this.largestArmy = turnTracker.getLargestArmy();
	}
	public Integer getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(Integer currentTurn) {
		this.currentTurn = currentTurn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLongestRoad() {
		return longestRoad;
	}
	public void setLongestRoad(Integer longestRoad) {
		this.longestRoad = longestRoad;
	}
	public Integer getLargestArmy() {
		return largestArmy;
	}
	public void setLargestArmy(Integer largestArmy) {
		this.largestArmy = largestArmy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRTurnTracker [currentTurn=");
		builder.append(currentTurn);
		builder.append(", status=");
		builder.append(status);
		builder.append(", longestRoad=");
		builder.append(longestRoad);
		builder.append(", largestArmy=");
		builder.append(largestArmy);
		builder.append("]");
		return builder.toString();
	}
}
