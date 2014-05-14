package shared.locations;

import client.models.translator.TREdgeLocation;


/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation implements ILocation {

	private HexLocation hexLocation;
	private VertexDirection direction;

	public VertexLocation(HexLocation hexLoc, VertexDirection direction) {
		setHexLocation(hexLoc);
		setDirection(direction);
	}

	public VertexLocation(TREdgeLocation location) {
		this.hexLocation = new HexLocation(location.getX(), location.getY());
		this.direction = VertexDirection.getDirectionFromServerString(location.getDirection());
	}

	public HexLocation getHexLocation() {
		return hexLocation;
	}

	public void setHexLocation(HexLocation hexLocation) {
		if (hexLocation == null) {
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLocation = hexLocation;
	}

	public VertexDirection getDirection() {
		return direction;
	}

	private void setDirection(VertexDirection direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "VertexLocation [hexLoc=" + hexLocation + ", dir=" + direction + "]";
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
		VertexLocation other = (VertexLocation) obj;
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
	 * Returns a canonical (i.e., unique) value for this vertex location.
	 * Since each vertex has three different locations on a map,
	 * this method converts a vertex location to a single canonical form.
	 * This is useful for using vertex locations as map keys.
	 *
	 * @return Normalized vertex location
	 */
	public VertexLocation getNormalizedLocation() {

		// Return location that has direction NW or NE

		switch (direction) {
			case NorthWest:
			case NorthEast:
				return this;
			case West:
				return new VertexLocation(hexLocation.getNeighborLoc(EdgeDirection.SouthWest),
											VertexDirection.NorthEast);
			case SouthWest:
				return new VertexLocation(hexLocation.getNeighborLoc(EdgeDirection.South),
						VertexDirection.NorthWest);
			case SouthEast:
				return new VertexLocation(hexLocation.getNeighborLoc(EdgeDirection.South),
						VertexDirection.NorthEast);
			case East:
				return new VertexLocation(hexLocation.getNeighborLoc(EdgeDirection.SouthEast),
											VertexDirection.NorthWest);
			default:
				assert false;
				return null;
		}
	}

	@Override
	public <D extends IDirection> void setDirection(D direction)
	{
		this.setDirection(direction);
	}
}

