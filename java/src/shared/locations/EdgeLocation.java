package shared.locations;

import client.models.translator.TREdgeLocation;


/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation implements ILocation{

	private HexLocation hexLocation;
	private EdgeDirection direction;

	public EdgeLocation(HexLocation hexLoc, EdgeDirection direction) {
		setHexLocation(hexLoc);
		setDirection(direction);
	}

	public EdgeLocation(TREdgeLocation location) {
		this.direction = EdgeDirection.getDirectionFromServerString(location.getDirection());
		this.hexLocation = new HexLocation(location.getX(), location.getY());
	}

	public HexLocation getHexLocation() {
		return hexLocation;
	}

	private void setHexLocation(HexLocation hexLocation) {
		if (hexLocation == null) {
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLocation = hexLocation;
	}

	public EdgeDirection getDirection() {
		return direction;
	}

	private void setDirection(EdgeDirection direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "EdgeLocation [hexLoc=" + hexLocation + ", dir=" + direction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result
				+ ((hexLocation == null) ? 0 : hexLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeLocation other = (EdgeLocation) obj;
		if (direction != other.direction)
			return false;
		if (hexLocation == null) {
			if (other.hexLocation != null)
				return false;
		} else if (!hexLocation.equals(other.hexLocation))
			return false;
		return true;
	}

	/**
	 * Returns a canonical (i.e., unique) value for this edge location.
	 * Since each edge has two different locations on a map,
	 * this method converts a hex location to a single canonical form.
	 * This is useful for using hex locations as map keys.
	 *
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation() {

		// Return an EdgeLocation that has direction NW, N, or NE

		switch (direction) {
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(hexLocation.getNeighborLoc(direction),
										direction.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}
}

