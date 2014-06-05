package client.models.interfaces;

public interface IMapBuilder {

	/**
	 * Adds Default Land Hexes to the provided ICatanMap
	 */
	void setupLandHexes(ICatanMap map, boolean randomizeNumbers, boolean randomizeHexTypes);

	/**
	 * Stores all Ports to the map in the default fashion
	 */
	public void setupPorts(ICatanMap map, boolean randomize);
}
