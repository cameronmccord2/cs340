/**
 * 
 */
package client.models;

import shared.locations.EdgeLocation;

/**
 * Needed for our server implementation
 * @author scottdaly
 *
 */
public class VertexObject {
	private int owner;
	private EdgeLocation location;
	
	public VertexObject(int owner, EdgeLocation location) {
		this.owner = owner;
		this.location = location;
	}

	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

	/**
	 * @return the location
	 */
	public EdgeLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
	
	
	
}
