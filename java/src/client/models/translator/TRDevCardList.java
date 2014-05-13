package client.models.translator;

public class TRDevCardList  implements IModelValid {
	protected Integer monopoly;
	protected Integer monument;
	protected Integer roadBuilding;
	protected Integer soldier;
	protected Integer yearOfPlenty;
	public Integer getMonopoly() {
		return monopoly;
	}
	public void setMonopoly(Integer monopoly) {
		this.monopoly = monopoly;
	}
	public Integer getMonument() {
		return monument;
	}
	public void setMonument(Integer monument) {
		this.monument = monument;
	}
	public Integer getRoadBuilding() {
		return roadBuilding;
	}
	public void setRoadBuilding(Integer roadBuilding) {
		this.roadBuilding = roadBuilding;
	}
	public Integer getSoldier() {
		return soldier;
	}
	public void setSoldier(Integer soldier) {
		this.soldier = soldier;
	}
	public Integer getYearOfPlenty() {
		return yearOfPlenty;
	}
	public void setYearOfPlenty(Integer yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}