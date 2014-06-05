package server.model.translator;

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
		map.setCities(null);
		map.setHexes(null);
		map.setSettlements(null);
		map.setPorts(null);
		map.setRadius(catanMap.getRadius());
		map.setRoads(null);
		map.setRobber(null);
		return map;
	}

	public static TRHex[] toTRObject(IHex[] hexes)
	{
		TRHex[] trHexes = new TRHex[hexes.length];
		return trHexes;
	}

	public static TRHex toTRObject(IHex hex)
	{
		TRHex trHex = new TRHex();
		return trHex;
	}

	public static TRHexLocation toTRObject(HexLocation hex)
	{
		TRHexLocation trHexLocation = new TRHexLocation();
		return trHexLocation;
	}

	public static TRBank toTRObject(IBank bank)
	{
		TRBank trBank = new TRBank();
		return trBank;
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

	public static TRVertexObject toTRObject(ISettlement settlement)
	{
		TRVertexObject vObject = new TRVertexObject();
		IPlayer player = settlement.getPlayer();
		PlayerInfo info = player.getPlayerInfo();
		vObject.setOwner(info.getPlayerIndex());
		vObject.setLocation(toTRObject((VertexLocation)settlement.getLocation()));
		return vObject;
	}

	public static TRVertexObject toTRObject(ICity city)
	{
		return toTRObject((ISettlement)city);
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