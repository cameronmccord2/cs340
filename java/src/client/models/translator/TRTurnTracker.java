package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRTurnTracker implements IModelValid {
	protected Integer currentTurn;
	protected String status;
	protected Integer longestRoad;
	protected Integer largestArmy;
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
	public void validate() throws InvalidTranslatorModelException {
		if(this.currentTurn < 0 || this.status == null || this.status.length() < 0)
			throw new InvalidTranslatorModelException(this.toString());
		if(this.status.equals("Rolling") || this.status.equals("Robbing") || this.status.equals("Playing") || this.status.equals("Discarding")
				|| this.status.equals("FirstRound") || this.status.equals("SecondRound"))
			return;
		throw new InvalidTranslatorModelException(this.toString());
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
/*
currentTurn (index): Who's turn it is (0-3), 
status (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or 
'SecondRound']: What's happening now, 
longestRoad (index, optional): The index of who has the longest road, 
largestArmy (index, optional): The index of who has the biggest army (3 or more) 

*/