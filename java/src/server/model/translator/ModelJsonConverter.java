package server.model.translator;

import java.util.Collection;
import java.util.Map;

import shared.definitions.DevCardType;
import shared.locations.*;
import client.data.PlayerInfo;
import client.models.*;
import client.models.translator.*;

public class ModelJsonConverter
{
	public static ClientModel toTRObject(IGame game)
	{
		ClientModel model = new ClientModel();
		model.setMap(toTRObject(game.getMap()));
		TRBank bank = toTRObject(game.getBank());
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

	public static TRHex[] toTRObject(IHex[] hexes)
	{
		TRHex[] trHexes = new TRHex[hexes.length];
		for(int i = 0; i < hexes.length; i++)
			trHexes[i] = toTRObject(hexes[i]);
		return trHexes;
	}

	public static TRHex toTRObject(IHex hex)
	{
		TRHex trHex = new TRHex();
		trHex.setNumber(hex.getHexNumber());
		trHex.setLocation(toTRObject(hex.getLocation()));
		trHex.setResource(hex.getHexType().toString().toLowerCase());
		return trHex;
	}

	public static TRHexLocation toTRObject(HexLocation hex)
	{
		TRHexLocation trHexLocation = new TRHexLocation();
		trHexLocation.setX(new Integer(hex.getX()));
		trHexLocation.setY(new Integer(hex.getY()));
		return trHexLocation;
	}

	public static TRHexLocation toTRObject(IRobber robber)
	{
		TRHexLocation hexLocation = new TRHexLocation();
		ILocation location = robber.getLocation();
		HexLocation robberHex = location.getHexLocation();
		hexLocation.setX(new Integer(robberHex.getX()));
		hexLocation.setY(new Integer(robberHex.getY()));
		return hexLocation;
	}

	public static TRBank toTRObject(IBank bank)
	{
		TRBank trBank = new TRBank();
		ResourceList resourceList = new ResourceList(bank.getResourceCards());
		DevCardList devCardList = new DevCardList(bank.getDevelopmentCards());
		trBank.setDevCards(toTRObject(devCardList));
		trBank.setResources(toTRObject(resourceList));
		return trBank;
	}

	public static TRDevCardList toTRObject(DevCardList devCardList)
	{
		TRDevCardList devcards = new TRDevCardList();
		Map<IDevelopmentCard, Integer> devMap = devCardList.getDevcards();
		devcards.setMonopoly(devMap.get(DevCardType.MONOPOLY));
		devcards.setMonument(devMap.get(DevCardType.MONUMENT));
		devcards.setRoadBuilding(devMap.get(DevCardType.ROAD_BUILD));
		devcards.setSoldier(devMap.get(DevCardType.SOLDIER));
		devcards.setYearOfPlenty(devMap.get(DevCardType.YEAR_OF_PLENTY));
		return devcards;
	}

	public static TRResourceList toTRObject(ResourceList resourceList)
	{
		TRResourceList resources = new TRResourceList();
		Map<IResourceCard, Integer> resourceMap = resourceList.getResources();
//		resources.setBrick(resourceMap.get(Resour))
		return resources;
	}

	public static TRTurnTracker toTRObject(TurnTracker turnTracker)
	{
		TRTurnTracker tracker = new TRTurnTracker();
		return tracker;
	}

	public static TRMessageLine toTRObject(MessageLine messageLine)
	{
		TRMessageLine line = new TRMessageLine();
		return line;
	}

	public static TRMessageList toTRObject(MessageList messageList)
	{
		TRMessageList messages = new TRMessageList();
		return messages;
	}

	public static TRPlayer[] toTRObject(IPlayer[] players)
	{
		TRPlayer[] trPlayers = new TRPlayer[players.length];
		for(int i = 0; i < players.length; i++)
			trPlayers[i] = toTRObject(players[i]);
		return trPlayers;
	}

	public static TRPlayer toTRObject(IPlayer player)
	{
		TRPlayer trPlayer = new TRPlayer();
		return trPlayer;
	}

	public static TRPort[] toTRObject(IPort[] ports)
	{
		TRPort[] trPorts = new TRPort[ports.length];
		for(int i = 0; i < ports.length; i++)
			trPorts[i] = toTRObject(ports[i]);
		return trPorts;
	}

	public static TRPort toTRObject(IPort port)
	{
		TRPort trPort = new TRPort();
		return trPort;
	}

	public static TRRoad[] toTRObject(IRoadSegment[] segments)
	{
		TRRoad[] roads = new TRRoad[segments.length];
		for(int i = 0; i < segments.length; i++)
			roads[i] = toTRObject(segments[i]);
		return roads;
	}

	public static TRRoad toTRObject(IRoadSegment segment)
	{
		TRRoad road = new TRRoad();
		return road;
	}

	public static TRVertexObject[] toTRObject(ISettlement[] settlements)
	{
		TRVertexObject[] vSettlements = new TRVertexObject[settlements.length];
		for(int i = 0; i < settlements.length; i++)
			vSettlements[i] = toTRObject(settlements[i]);
		return vSettlements;
	}

	public static TRVertexObject toTRObject(ISettlement settlement)
	{
		TRVertexObject vObject = new TRVertexObject();
		IPlayer player = settlement.getPlayer();
		PlayerInfo info = player.getPlayerInfo();
		vObject.setOwner(info.getPlayerIndex());
		vObject.setLocation(toTRObject((VertexLocation)settlement.getLocation()));
		return vObject;
	}

	public static TRVertexLocation toTRObject(VertexLocation vertex)
	{
		TRVertexLocation location = new TRVertexLocation();
		location.setX(new Integer(vertex.getHexLocation().getX()));
		location.setY(new Integer(vertex.getHexLocation().getY()));
		location.setDirection(vertex.getDirection().getServerString());
		return location;
	}
}