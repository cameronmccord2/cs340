package client.models.translator;

public class TRResourceList implements IModelValid {
	protected Integer wood;
	protected Integer brick;
	protected Integer ore;
	protected Integer sheep;
	protected Integer wheat;
	public Integer getWood() {
		return wood;
	}
	public void setWood(Integer wood) {
		this.wood = wood;
	}
	public Integer getBrick() {
		return brick;
	}
	public void setBrick(Integer brick) {
		this.brick = brick;
	}
	public Integer getOre() {
		return ore;
	}
	public void setOre(Integer ore) {
		this.ore = ore;
	}
	public Integer getSheep() {
		return sheep;
	}
	public void setSheep(Integer sheep) {
		this.sheep = sheep;
	}
	public Integer getWheat() {
		return wheat;
	}
	public void setWheat(Integer wheat) {
		this.wheat = wheat;
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
