package client.models.translator;

public class TRRoad implements IModelValid {
	protected Integer owner;
	protected TREdgeLocation location;
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public TREdgeLocation getLocation() {
		return location;
	}
	public void setLocation(TREdgeLocation location) {
		this.location = location;
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
