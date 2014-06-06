package server.model.translator;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.locations.*;
import client.data.PlayerInfo;
import client.models.*;
import client.models.interfaces.ICatanMap;
import client.models.interfaces.ICity;
import client.models.interfaces.IDevelopmentCard;
import client.models.interfaces.IGame;
import client.models.interfaces.IHex;
import client.models.interfaces.IParticipant;
import client.models.interfaces.IPlayer;
import client.models.interfaces.IPort;
import client.models.interfaces.IResourceCard;
import client.models.interfaces.IRoadSegment;
import client.models.interfaces.IRobber;
import client.models.interfaces.ISettlement;
import client.models.translator.*;

/**
 * This class is a static library for converting our model objects
 * into Transfer objects compatible with Gson.  It relies heavily
 * on method overloading.  There is only one method for this class
 * called toTRObject().
 * 
 * @author Craig Call
 *
 */
public class ModelTRConverter
{
	/**
	 * @param game
	 * @return A ClientModel Transfer Object
	 */
	public static ClientModel toTRObject(IGame game)
	{
		ClientModel model = new ClientModel();
		model.setMap(toTRObject(game.getMap()));
		TRParticipant bank = toTRObject(game.getBank());
		model.setBank(bank.getResources());
		model.setDeck(bank.getDevCards());
		model.setChat(toTRObject(game.getChat()));
		model.setLog(toTRObject(game.getLog()));
		model.setPlayers(toTRObject(game.getPlayers()));
		model.setTradeOffer(game.getCurrentTrade());
		model.setTurnTracker(toTRObject(game.getTurnTracker()));
		model.setVersion(game.getModelVersion().intValue());
		model.setWinner(game.getWinner().intValue());
		return model;
	}

	/**
	 * @param catanMap
	 * @return A TRMap Transfer Object
	 */
	public static TRMap toTRObject(ICatanMap catanMap)
	{
		TRMap map = new TRMap();
		Collection<ICity> cities = catanMap.getCities();
		map.setCities(toTRObject(cities.toArray(new ICity[cities.size()])));
		Collection<IHex> hexes = catanMap.getTerrainHexes();
		map.setHexes(toTRObject(hexes.toArray(new IHex[hexes.size()])));
		Collection<ISettlement> settlements = catanMap.getSettlements();
		map.setSettlements(toTRObject(settlements.toArray(new ISettlement[settlements.size()])));
		Collection<IPort> ports = catanMap.getPorts();
		map.setPorts(toTRObject(ports.toArray(new IPort[ports.size()])));
		map.setRadius(catanMap.getRadius());
		Collection<IRoadSegment> roads = catanMap.getRoads();
		map.setRoads(toTRObject(roads.toArray(new IRoadSegment[roads.size()])));
		map.setRobber(toTRObject(catanMap.getRobber()));
		return map;
	}

	/**
	 * @param hexes
	 * @return A TRHex Transfer Object Array
	 */
	public static TRHex[] toTRObject(IHex[] hexes)
	{
		TRHex[] trHexes = new TRHex[hexes.length];
		for(int i = 0; i < hexes.length; i++)
			trHexes[i] = toTRObject(hexes[i]);
		return trHexes;
	}

	/**
	 * @param hex
	 * @return A TRHex Transfer Object
	 */
	public static TRHex toTRObject(IHex hex)
	{
		TRHex trHex = new TRHex();
		trHex.setNumber(hex.getHexNumber());
		trHex.setLocation(toTRObject(hex.getLocation()));
		trHex.setResource(hex.getHexType().toString().toLowerCase());
		return trHex;
	}

	/**
	 * @param hex
	 * @return A TRHexLocation Transfer Object
	 */
	public static TRHexLocation toTRObject(HexLocation hex)
	{
		TRHexLocation trHexLocation = new TRHexLocation();
		trHexLocation.setX(new Integer(hex.getX()));
		trHexLocation.setY(new Integer(hex.getY()));
		return trHexLocation;
	}

	/**
	 * @param robber
	 * @return A TRHexLocation Transfer Object
	 */
	public static TRHexLocation toTRObject(IRobber robber)
	{
		TRHexLocation hexLocation = new TRHexLocation();
		ILocation location = robber.getLocation();
		HexLocation robberHex = location.getHexLocation();
		hexLocation.setX(new Integer(robberHex.getX()));
		hexLocation.setY(new Integer(robberHex.getY()));
		return hexLocation;
	}

	/**
	 * @param participant
	 * @return A TRParticipant Transfer Object
	 */
	public static TRParticipant toTRObject(IParticipant participant)
	{
		TRParticipant trParticipant = new TRParticipant();
		ResourceList resourceList = new ResourceList(participant.getResourceCards());
		DevCardList devCardList = new DevCardList(participant.getDevelopmentCards());
		trParticipant.setDevCards(toTRObject(devCardList));
		trParticipant.setResources(toTRObject(resourceList));
		return trParticipant;
	}

	/**
	 * @param devCardList
	 * @return A TRDevCardList Transfer Object
	 */
	public static TRDevCardList toTRObject(DevCardList devCardList)
	{
		TRDevCardList devcards = new TRDevCardList();
		Map<IDevelopmentCard, Integer> devMap = devCardList.getDevcards();
		devcards.setMonopoly(devMap.get(DevelopmentCard.MONOPOLY));
		devcards.setMonument(devMap.get(DevelopmentCard.MONUMENT));
		devcards.setRoadBuilding(devMap.get(DevelopmentCard.ROAD_BUILD));
		devcards.setSoldier(devMap.get(DevelopmentCard.SOLDIER));
		devcards.setYearOfPlenty(devMap.get(DevelopmentCard.YEAR_OF_PLENTY));
		return devcards;
	}

	/**
	 * @param resourceList
	 * @return A TRResourceList Transfer Object
	 */
	public static TRResourceList toTRObject(ResourceList resourceList)
	{
		TRResourceList resources = new TRResourceList();
		Map<IResourceCard, Integer> resourceMap = resourceList.getResources();
		resources.setBrick(resourceMap.get(ResourceCard.BRICK));
		resources.setOre(resourceMap.get(ResourceCard.ORE));
		resources.setSheep(resourceMap.get(ResourceCard.SHEEP));
		resources.setWheat(resourceMap.get(ResourceCard.WHEAT));
		resources.setWood(resourceMap.get(ResourceCard.WOOD));
		return resources;
	}

	/**
	 * @param turnTracker
	 * @return A TRTurnTracker Transfer Object
	 */
	public static TRTurnTracker toTRObject(TurnTracker turnTracker)
	{
		TRTurnTracker tracker = new TRTurnTracker();
		tracker.setCurrentTurn(turnTracker.getCurrentTurn());
		tracker.setLargestArmy(turnTracker.getLargestArmy());
		tracker.setLongestRoad(turnTracker.getLongestRoad());
		tracker.setStatus(turnTracker.getStatus());
		return tracker;
	}

	/**
	 * @param messageLine
	 * @return A TRMessageLine Transfer Object
	 */
	public static TRMessageLine toTRObject(MessageLine messageLine)
	{
		TRMessageLine line = new TRMessageLine();
		line.setMessage(messageLine.getMessage());
		line.setSource(messageLine.getSource());
		return line;
	}

	/**
	 * @param messageList
	 * @return A TRMessageList Transfer Object
	 */
	public static TRMessageList toTRObject(MessageList messageList)
	{
		TRMessageList messages = new TRMessageList();
		List<MessageLine> linesList = messageList.getLines();
		MessageLine[] mLines = linesList.toArray(new MessageLine[linesList.size()]);
		TRMessageLine[] lines = new TRMessageLine[mLines.length];
		for(int i = 0; i < mLines.length; i++)
			lines[i] = toTRObject(mLines[i]);
		messages.setLines(lines);
		return messages;
	}

	/**
	 * @param players
	 * @return A TRPlayer Transfer Object Array
	 */
	public static TRPlayer[] toTRObject(IPlayer[] players)
	{
		TRPlayer[] trPlayers = new TRPlayer[players.length];
		for(int i = 0; i < players.length; i++)
			trPlayers[i] = toTRObject(players[i]);
		return trPlayers;
	}

	/**
	 * @param player
	 * @return A TRPlayer Transfer Object
	 */
	public static TRPlayer toTRObject(IPlayer player)
	{
		TRPlayer trPlayer = new TRPlayer();
		PlayerInfo info = player.getPlayerInfo();
		trPlayer.setColor(info.getColor().toString().toLowerCase());
		trPlayer.setDiscarded(player.hasDiscarded());
		trPlayer.setName(info.getName());
		trPlayer.setPlayerID(info.getId());
		trPlayer.setPlayerIndex(info.getPlayerIndex());
		trPlayer.setPlayedDevCard(player.hasPlayedDevCard());
		trPlayer.setSettlements(player.getSettlements().size());
		trPlayer.setCities(player.getCities().size());
		trPlayer.setMonuments(player.getMonuments());
		trPlayer.setSoldiers(player.getSoldiers());
		trPlayer.setVictoryPoints(player.getVictoryPoints());
		trPlayer.setRoads(player.getRoads().size());
		TRParticipant participant = toTRObject((IParticipant)player);
		trPlayer.setResources(participant.getResources());
		
		// TODO: Fix this part
		// THESE NEED TO BE REPLACED, BUT THE IPlayer INTERFACE
		// NEEDS TO BE UPDATED!
		trPlayer.setNewDevCards(participant.getDevCards());
//		trPlayer.setOldDevCards(null);
		return trPlayer;
	}

	/**
	 * @param ports
	 * @return A TRPort Transfer Object Array
	 */
	public static TRPort[] toTRObject(IPort[] ports)
	{
		TRPort[] trPorts = new TRPort[ports.length];
		for(int i = 0; i < ports.length; i++)
			trPorts[i] = toTRObject(ports[i]);
		return trPorts;
	}

	/**
	 * @param port
	 * @return A TRMap Transfer Object
	 */
	public static TRPort toTRObject(IPort port)
	{
		TRPort trPort = new TRPort();
		ILocation location = port.getLocation();
		IDirection direction = location.getDirection();
		trPort.setLocation(toTRObject(location.getHexLocation()));
		trPort.setDirection(direction.asServerString());
		trPort.setRatio(new Integer(port.getExchangeRate()));
		PortType type = port.getPortType();
		if(!type.equals(PortType.THREE))
			trPort.setResource(type.toString().toLowerCase());
		return trPort;
	}

	/**
	 * @param segments
	 * @return A TRRoad Transfer Object Array
	 */
	public static TRRoad[] toTRObject(IRoadSegment[] segments)
	{
		TRRoad[] roads = new TRRoad[segments.length];
		for(int i = 0; i < segments.length; i++)
			roads[i] = toTRObject(segments[i]);
		return roads;
	}

	/**
	 * @param segment
	 * @return A TRRoad Transfer Object
	 */
	public static TRRoad toTRObject(IRoadSegment segment)
	{
		TRRoad road = new TRRoad();
		ILocation location = segment.getLocation();
		road.setLocation(toTRObject((EdgeLocation)location));
		IPlayer player = segment.getPlayer();
		PlayerInfo info = player.getPlayerInfo();
		road.setOwner(new Integer(info.getPlayerIndex()));
		return road;
	}

	/**
	 * @param location
	 * @return A TREdgeLocation Transfer Object
	 */
	public static TREdgeLocation toTRObject(EdgeLocation location)
	{
		TREdgeLocation edge = new TREdgeLocation();
		HexLocation hexLoc = location.getHexLocation();
		edge.setX(new Integer(hexLoc.getX()));
		edge.setY(new Integer(hexLoc.getY()));
		edge.setDirection(location.getDirection().asServerString());
		return edge;
	}

	/**
	 * @param settlements
	 * @return A TRVertexObject Transfer Object Array
	 */
	public static TRVertexObject[] toTRObject(ISettlement[] settlements)
	{
		TRVertexObject[] vSettlements = new TRVertexObject[settlements.length];
		for(int i = 0; i < settlements.length; i++)
			vSettlements[i] = toTRObject(settlements[i]);
		return vSettlements;
	}

	/**
	 * @param settlement
	 * @return A TRVertexObject Transfer Object
	 */
	public static TRVertexObject toTRObject(ISettlement settlement)
	{
		TRVertexObject vObject = new TRVertexObject();
		IPlayer player = settlement.getPlayer();
		PlayerInfo info = player.getPlayerInfo();
		vObject.setOwner(info.getPlayerIndex());
		vObject.setLocation(toTRObject((VertexLocation)settlement.getLocation()));
		return vObject;
	}

	/**
	 * @param vertex
	 * @return A TRVertexLocation Transfer Object
	 */
	public static TRVertexLocation toTRObject(VertexLocation vertex)
	{
		TRVertexLocation location = new TRVertexLocation();
		location.setX(new Integer(vertex.getHexLocation().getX()));
		location.setY(new Integer(vertex.getHexLocation().getY()));
		location.setDirection(vertex.getDirection().asServerString());
		return location;
	}
}