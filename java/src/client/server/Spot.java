/**
 * 
 */
package client.server;

/**
 * Stores the x,y location and direction to play a Road Building card
 * @author scottdaly
 *
 */
public class Spot {
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
	
	
}
