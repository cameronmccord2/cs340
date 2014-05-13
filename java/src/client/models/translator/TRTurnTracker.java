package client.models.translator;

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
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
/*
currentTurn (index): Who's turn it is (0-3), 
status (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or 
'SecondRound']: What's happening now, 
longestRoad (index, optional): The index of who has the longest road, 
largestArmy (index, optional): The index of who has the biggest army (3 or more) 

*/