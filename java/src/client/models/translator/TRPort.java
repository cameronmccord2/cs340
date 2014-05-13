package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRPort implements IModelValid {
	protected String resource;
	protected TRHexLocation location;
	protected String direction;
	protected Integer ratio;
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public TRHexLocation getLocation() {
		return location;
	}
	public void setLocation(TRHexLocation location) {
		this.location = location;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Integer getRatio() {
		return ratio;
	}
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	@Override
	public void isValid() throws InvalidTranslatorModelException {
		this.location.isValid();
		if(this.resource == null || this.resource.length() == 0 || this.direction == null || this.direction.length() == 0 || this.ratio < 2 || this.ratio > 3)
			throw new InvalidTranslatorModelException(this.toString());
		if((this.resource.equals("Wood") || this.resource.endsWith("Brick") || this.resource.equals("Sheep") || 
				this.resource.equals("Wheat") || this.resource.equals("Ore")) && 
				(this.direction.equals("NW") || this.direction.equals("N") || this.direction.equals("NE") || this.direction.equals("E") ||
						this.direction.equals("SE") || this.direction.equals("SW")))
			return;
		throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRPort [resource=");
		builder.append(resource);
		builder.append(", location=");
		builder.append(location);
		builder.append(", direction=");
		builder.append(direction);
		builder.append(", ratio=");
		builder.append(ratio);
		builder.append("]");
		return builder.toString();
	}
}
/*
resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What type 
resource this port trades for. If it's omitted, then it's for any resource., 
location (HexLocation): Which hex this port is on. This shows the (ocean/non-existent) hex to 
draw the port on., 
direction (string) = ['NW' or 'N' or 'NE' or 'E' or 'SE' or 'SW']: Which edge this port is on., 
ratio (integer): The ratio for trade in (ie, if this is 2, then it's a 2:1 port. 

*/