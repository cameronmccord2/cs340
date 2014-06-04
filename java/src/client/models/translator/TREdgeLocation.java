package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TREdgeLocation implements IModelValid {
	protected Integer x;
	protected Integer y;
	protected String direction;
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Override
	public void validate() throws InvalidTranslatorModelException {
		if(direction.equals("NW") || direction.equals("N") || direction.equals("NE") || direction.equals("SW") || direction.equals("S") || direction.equals("SE"))
			if(x != null && y != null)
				return;
		throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TREdgeLocation [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", direction=");
		builder.append(direction);
		builder.append("]");
		return builder.toString();
	}
}
