package shared.locations;

public enum VertexDirection implements IDirection{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;

	private VertexDirection opposite;

	static {
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
	}

	public VertexDirection getOppositeDirection() {
		return opposite;
	}

	public static VertexDirection getDirectionForServerString(String direction) {
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

	@Override
	public Class<?> getDirectionType()
	{
		return VertexDirection.class;
	}
}

