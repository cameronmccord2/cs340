package shared.locations;

public enum EdgeDirection implements IDirection {

	NorthWest, North, NorthEast, SouthEast, South, SouthWest;

    private EdgeDirection opposite;

    static {
        NorthWest.opposite = SouthEast;
        North.opposite = South;
        NorthEast.opposite = SouthWest;
        SouthEast.opposite = NorthWest;
        South.opposite = North;
        SouthWest.opposite = NorthEast;
    }

    public EdgeDirection getOppositeDirection() {
        return opposite;
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

	@Override
	public Class<?> getDirectionType()
	{
		return EdgeDirection.class;
	}
}

