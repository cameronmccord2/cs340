package client.models;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

import java.util.*;

import client.models.interfaces.ICatanMap;
import client.models.interfaces.IMapBuilder;

public class MapBuilder implements IMapBuilder {

	/**
	 * Worker function which loops through the provided List of hexes, assigns
	 * them a number and sticks them in the map
	 * @param map map to manipulate
	 * @param hexes list of hexes to be used in the map
	 * @param numbers list of corresponding numbers to be used in the map
	 * @param types list of corresponding types to be used in the map
	 */
	private void addLandHexes(ICatanMap map, List<Hex> hexes, List<Integer> numbers, List<HexType> types) {

		Iterator<Integer> numberIterator = numbers.iterator();
		Iterator<HexType> typeIterator = types.iterator();
		for(Hex hex: hexes)
		{
			HexType type = typeIterator.next();
			hex.setHexType(type);

			if(type != HexType.DESERT)
			{
				Integer currentNum = numberIterator.next();
				if(currentNum == 0)
					currentNum = numberIterator.next();

				hex.setHexNumber(currentNum);
			}
			else
				map.setRobber(new Robber(hex.getLocation()));

			map.addHex(hex);
		}
	}

	/**
	 * Sets up the default land hexes, assigning a number to each. If
	 * the second parameter is true, the numbers will be random
	 * @param map map to manipulate
	 * @param randomizeNumbers indicates whether to randomize numbers
	 * @param randomizeHexTypes indicates whether to randomize hex types
	 */
	@Override
	public void setupLandHexes(ICatanMap map, boolean randomizeNumbers, boolean randomizeHexTypes) {

		List<Integer> numbers = getDefaultNumbers();
		List<HexType> types = getDefaultHexTypes();

		if(randomizeNumbers)
			Collections.shuffle(numbers);

		if(randomizeHexTypes)
			Collections.shuffle(types);

		addLandHexes(map, getDefaultMapLocations(), numbers, types);

	}

	/**
	 * Gets all possible land hex types in clockwise order from the northwest
	 * @return list of all types of hexes in a valid Catan Default Map
	 */
	private List<HexType> getDefaultHexTypes() {

		// This order matters, as it is the order of the hexes in
		// a default game, layed out in concentric clockwise circles
		// from the northwest. However, this can be shuffled if not
		// for a default game

		return Arrays.asList(
			HexType.WOOD,
			HexType.SHEEP,
			HexType.WHEAT,
			HexType.SHEEP,
			HexType.WHEAT,
			HexType.ORE,
			HexType.WOOD,
			HexType.WHEAT,
			HexType.ORE,
			HexType.BRICK,
			HexType.DESERT,
			HexType.BRICK,
			HexType.ORE,
			HexType.BRICK,
			HexType.WOOD,
			HexType.SHEEP,
			HexType.SHEEP,
			HexType.WOOD,
			HexType.WHEAT
		);
	}

	/**
	 * Gets all possible land hex numbers in clockwise order from the northwest
	 * @return list of all numbers in a Default CatanMap
	 */
	private List<Integer> getDefaultNumbers() {

		// This order matters, as it is the order of the hexes in
		// a default game, layed out in concentric clockwise circles
		// from the northwest. However, this can be shuffled if not
		// for a default game

		return Arrays.asList(11,12,9,10,8,3,6,2,5,8,0,4,6,5,4,9,10,3,11);

	}


	/**
	 * Gets all possible land hex locations in clockwise order from the northwest
	 * @return list of all valid locations for a land hex in a Default CatanMap
	 */
	private List<Hex> getDefaultMapLocations() {

		// This order matters, as it is the order of the hexes in
		// a default game, laid out in concentric clockwise circles
		// from the northwest

		// Outer Ring of Default Tiles
		return Arrays.asList(
			new Hex(new HexLocation(0,-2)),
			new Hex(new HexLocation(1,-2)),
			new Hex(new HexLocation(2,-2)),
			new Hex(new HexLocation(2,-1)),
			new Hex(new HexLocation(2,0)),
			new Hex(new HexLocation(1,1)),
			new Hex(new HexLocation(0,2)),
			new Hex(new HexLocation(-1,2)),
			new Hex(new HexLocation(-2,2)),
			new Hex(new HexLocation(-2,1)),
			new Hex(new HexLocation(-2,0)),
			new Hex(new HexLocation(-1,-1)),
			new Hex(new HexLocation(0,-1)),
			new Hex(new HexLocation(1,-1)),
			new Hex(new HexLocation(1,0)),
			new Hex(new HexLocation(0,1)),
			new Hex(new HexLocation(-1,1)),
			new Hex(new HexLocation(-1,0)),
			new Hex(new HexLocation(0,0))
		);
	}

	/**
	 * Stores all Ports to the map in the default fashion
	 */
	@Override
	public void setupPorts(ICatanMap map, boolean randomize) {

		List<EdgeLocation> portLocations = getPossiblePortLocations();
		if(randomize)
			Collections.shuffle(portLocations);

		setUpPorts(map, portLocations);

	}

	private List<EdgeLocation> getPossiblePortLocations() {
		// This order matters - These are the port locations in a default game
		// clockwise from the northwest.
		return Arrays.asList(
			new EdgeLocation( new HexLocation(0,-3), EdgeDirection.South),//3:1
			new EdgeLocation( new HexLocation(2,-3), EdgeDirection.SouthWest), //sheep
			new EdgeLocation( new HexLocation(3,-2), EdgeDirection.SouthWest), //3:1
			new EdgeLocation( new HexLocation(3,0), EdgeDirection.NorthWest), //3:1
			new EdgeLocation( new HexLocation(1,2), EdgeDirection.North), //brick
			new EdgeLocation( new HexLocation(-1,3), EdgeDirection.North), //lumber
			new EdgeLocation( new HexLocation(-3,3), EdgeDirection.NorthEast), //3:1
			new EdgeLocation( new HexLocation(-3,1), EdgeDirection.SouthEast), //grain
			new EdgeLocation( new HexLocation(-2,-1), EdgeDirection.SouthEast) //ore
		);
	}

	/**
	 * Worker method which loops through all Port locations and adds them to the map
	 * @param map map to manipulate
	 * @param locations list of port locations (may or may not be randomized)
	 */
	private void setUpPorts(ICatanMap map, List<EdgeLocation> locations) {

		// The locations List might be shuffled before it gets to this point
		Iterator<EdgeLocation> it = locations.iterator();

		// This order matters, as it matches the port types in a default game
		// clockwise from the northwest
		map.addPort(new Port( it.next(), PortType.THREE ));
		map.addPort(new Port( it.next(), PortType.SHEEP ));
		map.addPort(new Port( it.next(), PortType.THREE ));
		map.addPort(new Port( it.next(), PortType.THREE ));
		map.addPort(new Port( it.next(), PortType.BRICK ));
		map.addPort(new Port( it.next(), PortType.WOOD ));
		map.addPort(new Port( it.next(), PortType.THREE ));
		map.addPort(new Port( it.next(), PortType.WHEAT ));
		map.addPort(new Port( it.next(), PortType.ORE ));

	}
}