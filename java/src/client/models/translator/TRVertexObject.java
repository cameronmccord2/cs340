package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRVertexObject implements IModelValid {
	protected Integer owner;
	protected TRVertexLocation location;

	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public TRVertexLocation getLocation() {
		return location;
	}
	public void setLocation(TRVertexLocation location) {
		this.location = location;
	}
	@Override
	public void validate() throws InvalidTranslatorModelException {
//		this.location.isValid();
//		if(this.owner == null || this.owner < 0)
//			throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRVertexObject [owner=");
		builder.append(owner);
		builder.append(", location=");
		builder.append(location);
		builder.append("]");
		return builder.toString();
	}
}
