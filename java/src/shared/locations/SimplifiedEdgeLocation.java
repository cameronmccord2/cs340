package shared.locations;

/**
 * SimplifiedEdgeLocation contains a subset of the data contained in a complete EdgeLocation.
 * The subset is determined by the information required by the Catan Server, as this class will
 * be serialized as JSON and the output will be just what the server needs.
 */
public class SimplifiedEdgeLocation {
    int x;
    int y;
    String direction;

    public SimplifiedEdgeLocation() {
        x = 0;
        y = 0;
        direction = "N";
    }
    public SimplifiedEdgeLocation(EdgeLocation edge) {
        x = edge.getHexLocation().getX();
        y = edge.getHexLocation().getY();
        switch(edge.getDirection().toString().toUpperCase()){
            case "NORTHWEST":
                direction = "NW"; break;
            case "NORTH":
                direction = "N"; break;
            case "NORTHEAST":
                direction = "NE"; break;
            case "SOUTHEAST":
                direction = "SE"; break;
            case "SOUTH":
                direction = "S"; break;
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
}
