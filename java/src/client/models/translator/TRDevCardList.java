package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

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
	public void isValid() throws InvalidTranslatorModelException {
		if(monopoly < 0 || monument < 0 || roadBuilding < 0 || soldier < 0 || yearOfPlenty < 0)
			throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRDevCardList [monopoly=");
		builder.append(monopoly);
		builder.append(", monument=");
		builder.append(monument);
		builder.append(", roadBuilding=");
		builder.append(roadBuilding);
		builder.append(", soldier=");
		builder.append(soldier);
		builder.append(", yearOfPlenty=");
		builder.append(yearOfPlenty);
		builder.append("]");
		return builder.toString();
	}
}