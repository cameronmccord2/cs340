package shared.locations;

import java.io.Serializable;

public enum EdgeDirection implements IDirection, Serializable {

	NorthWest, North, NorthEast, SouthEast, South, SouthWest;

	private EdgeDirection opposite;
	private String serverString;

	static {
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
		
		NorthWest.serverString = "NW";
		North.serverString = "N";
		NorthEast.serverString = "NE";
		SouthEast.serverString = "SE";
		South.serverString = "S";
		SouthWest.serverString = "SW";
	}

	public EdgeDirection getOppositeDirection() {
		return opposite;
	}
	
	public String asServerString() {
		return serverString;
	}

	public static EdgeDirection getDirectionFromServerString(String direction) {
		switch(direction.toUpperCase()){
			case "NW":
				return EdgeDirection.NorthWest;
			case "N":
				return EdgeDirection.North;
			case "NE":
				return EdgeDirection.NorthEast;
			case "SE":
				return EdgeDirection.SouthEast;
			case "S":
				return EdgeDirection.South;
			case "SW":
				return EdgeDirection.SouthWest;
		}
		return EdgeDirection.South;
	}
}

