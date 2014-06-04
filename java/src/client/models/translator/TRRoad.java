package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

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
	public void validate() throws InvalidTranslatorModelException {
		this.location.validate();
		if(this.owner == null || this.owner < 0)
			throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRRoad [owner=");
		builder.append(owner);
		builder.append(", location=");
		builder.append(location);
		builder.append("]");
		return builder.toString();
	}
}
