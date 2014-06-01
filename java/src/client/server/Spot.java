/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Stores the x,y location and direction to play a Road Building card
 * @author scottdaly
 *
 */
public class Spot implements ICommandParams {
	private int x;
	private int y;
	private String direction;
	public Spot(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.direction == null || this.direction.length() == 0)
			throw new CommandParamNotValidException("direction cant be null or of length zero: " + this.toString());
	}
	@Override
	public String getType() {
		return "spot";// will never get used
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spot [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", direction=");
		builder.append(direction);
		builder.append("]");
		return builder.toString();
	}
	
	
}
