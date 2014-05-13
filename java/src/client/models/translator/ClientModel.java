package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class ClientModel implements IModelValid{
	  protected TRMap map;
	  protected TRPlayer[] players;
	  protected TRMessageList log;
	  protected TRMessageList chat;
	  protected TRResourceList bank;
	  protected TRTurnTracker turnTracker;
	  protected TRTradeOffer tradeOffer;
	  Long winner;
	  int version = 0;
	public TRMap getMap() {
		return map;
	}
	public void setMap(TRMap map) {
		this.map = map;
	}
	public TRPlayer[] getPlayers() {
		return players;
	}
	public void setPlayers(TRPlayer[] players) {
		this.players = players;
	}
	public TRMessageList getLog() {
		return log;
	}
	public void setLog(TRMessageList log) {
		this.log = log;
	}
	public TRMessageList getChat() {
		return chat;
	}
	public void setChat(TRMessageList chat) {
		this.chat = chat;
	}
	public TRResourceList getBank() {
		return bank;
	}
	public void setBank(TRResourceList bank) {
		this.bank = bank;
	}
	public TRTurnTracker getTurnTracker() {
		return turnTracker;
	}
	public void setTurnTracker(TRTurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}
	public TRTradeOffer getTradeOffer() {
		return tradeOffer;
	}
	public void setTradeOffer(TRTradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}
	public Long getWinner() {
		return winner;
	}
	public void setWinner(Long winner) {
		this.winner = winner;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public void isValid() throws InvalidTranslatorModelException {
		map.isValid();
		for (TRPlayer p : this.players) {
			p.isValid();
		}
		this.log.isValid();
		this.chat.isValid();
		this.bank.isValid();
		this.turnTracker.isValid();
		if(this.tradeOffer != null)
			this.tradeOffer.isValid();
		if(this.version < 0)
			throw new InvalidTranslatorModelException("Version is less than 0, toString: " + this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientModel [map=");
		builder.append(map);
		builder.append(", players=");
		builder.append(players);
		builder.append(", log=");
		builder.append(log);
		builder.append(", chat=");
		builder.append(chat);
		builder.append(", bank=");
		builder.append(bank);
		builder.append(", turnTracker=");
		builder.append(turnTracker);
		builder.append(", tradeOffer=");
		builder.append(tradeOffer);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
}
