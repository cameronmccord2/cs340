package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRHexLocation  implements IModelValid{
	protected Integer x;
	protected Integer y;
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
	@Override
	public void isValid() throws InvalidTranslatorModelException {
		if(x == null || y == null)
			throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRHexLocation [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
}
