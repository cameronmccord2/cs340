package shared.locations;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * SimplifiedVertexLocation contains a subset of the data contained in a complete VertexLocation.
 * The subset is determined by the information required by the Catan Server, as this class will
 * be serialized as JSON and the output will be just what the server needs.
 */
public class SimplifiedVertexLocation implements ICommandParams {

	private int x;
	private int y;
	private String direction;

	public SimplifiedVertexLocation() {
		x = 0;
		y = 0;
		direction = "NW";
	}
	public SimplifiedVertexLocation(VertexLocation vertex) {
		x = vertex.getHexLocation().getX();
		y = vertex.getHexLocation().getY();
		switch(vertex.getDirection().toString().toUpperCase()){
			case "NORTHWEST":
				direction = "NW"; break;
			case "WEST":
				direction = "W"; break;
			case "NORTHEAST":
				direction = "NE"; break;
			case "SOUTHEAST":
				direction = "SE"; break;
			case "EAST":
				direction = "E"; break;
			case "SOUTHWEST":
				direction = "SW"; break;
		}
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.direction == null || this.direction.length() == 0)
			throw new CommandParamNotValidException("direction cant be null or of length zero: " + this.toString());
	}
	@Override
	public String getType() {
		return "none";//will never be used
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimplifiedVertexLocation [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", direction=");
		builder.append(direction);
		builder.append("]");
		return builder.toString();
	}
}
