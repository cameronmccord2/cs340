package server.facades;

import persistence.IPlugin;

import com.google.gson.Gson;

import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.DiscardedCards;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
import client.server.RoadBuilding;
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;
import client.server.ServerChat;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerRobPlayer;
import client.server.ServerRoll;
import client.server.ServerSoldier;
import client.server.ServerYearofPlenty;
import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.FinishTurn;
import server.models.UserAttributes;

public class MovesFacade extends CommandCreationFacade implements IMovesFacade{
	
	private IPlugin plugin;

	public MovesFacade(IServerModelFacade modelFacade, IPlugin plugin) {
		super(modelFacade);
		this.plugin = plugin;
	}

	@Override
	public CommandResponse buyDevCard(String json, UserAttributes ua) {
		Gson gson = new Gson();
		BuyDevCard params = gson.fromJson(json, BuyDevCard.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse yearOfPlenty(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerYearofPlenty params = gson.fromJson(json, ServerYearofPlenty.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse soldier(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerSoldier params = gson.fromJson(json, ServerSoldier.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse monopoly(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerMonopoly params = gson.fromJson(json, ServerMonopoly.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse monument(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerMonument params = gson.fromJson(json, ServerMonument.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse offerTrade(String json, UserAttributes ua) {
		Gson gson = new Gson();
		OfferTrade params = gson.fromJson(json, OfferTrade.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse acceptTrade(String json, UserAttributes ua) {
		Gson gson = new Gson();
		AcceptTrade params = gson.fromJson(json, AcceptTrade.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse maritimeTrade(String json, UserAttributes ua) {
		Gson gson = new Gson();
		MaritimeTradeOff params = gson.fromJson(json, MaritimeTradeOff.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse discardCards(String json, UserAttributes ua) {
		Gson gson = new Gson();
		DiscardedCards params = gson.fromJson(json, DiscardedCards.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse rollNumber(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerRoll params = gson.fromJson(json, ServerRoll.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse sendChat(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerChat params = gson.fromJson(json, ServerChat.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse finishTurn(String json, UserAttributes ua) {
		Gson gson = new Gson();
		FinishTurn params = gson.fromJson(json, FinishTurn.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse robPlayer(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerRobPlayer params = gson.fromJson(json, ServerRobPlayer.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse roadBuilding(String json, UserAttributes ua) {
		Gson gson = new Gson();
		RoadBuilding params = gson.fromJson(json, RoadBuilding.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse buildRoad(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerBuildRoad params = gson.fromJson(json, ServerBuildRoad.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse buildSettlement(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerBuildSettlement params = gson.fromJson(json, ServerBuildSettlement.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse buildCity(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerBuildCity params = gson.fromJson(json, ServerBuildCity.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

}
