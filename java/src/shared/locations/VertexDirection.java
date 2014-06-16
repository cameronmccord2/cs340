package shared.locations;

import java.io.Serializable;

public enum VertexDirection implements IDirection, Serializable {
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;

	private VertexDirection opposite;
	private String serverString;

	static {
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;

		West.serverString = "W";
		NorthWest.serverString = "NW";
		NorthEast.serverString = "NE";
		East.serverString = "E";
		SouthEast.serverString = "SE";
		SouthWest.serverString = "SW";
	}

	public VertexDirection getOppositeDirection() {
		return opposite;
	}

	public String asServerString()
	{
		return serverString;
	}

	public static VertexDirection getDirectionFromServerString(String direction) {
		switch (direction.toUpperCase()) {
			case "NW":
				return VertexDirection.NorthWest;
			case "NE":
				return VertexDirection.NorthEast;
			case "W":
				return VertexDirection.West;
			case "SW":
				return VertexDirection.SouthWest;
			case "SE":
				return VertexDirection.SouthEast;
			case "E":
				return VertexDirection.East;
			default:
				assert false;
				return null;
		}
	}
}

