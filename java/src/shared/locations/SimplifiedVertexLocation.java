package shared.locations;

/**
 * SimplifiedVertexLocation contains a subset of the data contained in a complete VertexLocation.
 * The subset is determined by the information required by the Catan Server, as this class will
 * be serialized as JSON and the output will be just what the server needs.
 */
public class SimplifiedVertexLocation {

    int x;
    int y;
    String direction;

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
}
