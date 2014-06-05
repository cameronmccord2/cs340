package server.model.translator;

import java.util.Collection;
import java.util.Map;

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
		map.setHexes(null);
		Collection<ISettlement> settlements = catanMap.getSettlements();
		map.setSettlements(toTRObject(settlements.toArray(new ISettlement[settlements.size()])));
		map.setPorts(null);
		map.setRadius(catanMap.getRadius());
		map.setRoads(null);
		map.setRobber(null);
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

		return devcards;
	}

	public static TRResourceList toTRObject(ResourceList resourceList)
	{
		TRResourceList resources = new TRResourceList();
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

	public static TRPort toTRObject(IPort port)
	{
		TRPort trPort = new TRPort();
		return trPort;
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