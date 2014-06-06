package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRHex implements IModelValid {
	protected TRHexLocation location;
	protected String resource;
	protected Integer number;

	public TRHexLocation getLocation() {
		return location;
	}
	public void setLocation(TRHexLocation location) {
		this.location = location;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public void validate() throws InvalidTranslatorModelException {
		this.location.validate();
		if(resource.equals("desert"))
			resource = null;
		if(resource == null || (resource != null && (resource.equals("wood") || resource.equals("brick") || resource.equals("sheep") || resource.equals("wheat") || resource.equals("ore"))))
			return;
		throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRHex [location=");
		builder.append(location);
		builder.append(", resource=");
		builder.append(resource);
		builder.append(", number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}
}
/*
location (HexLocation),
resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What resource this
tile gives - it's only here if the tile is not desert.,
number (integer, optional): What number is on this tile. It's omitted if this is a desert hex.

*/