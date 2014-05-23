package client.models.translator;

import java.util.Arrays;

import client.models.exceptions.InvalidTranslatorModelException;

public class ClientModel implements IModelValid{
   protected TRMap map;
   protected TRPlayer[] players;
   protected TRMessageList log;
   protected TRMessageList chat;
   protected TRResourceList bank;
   protected TRTurnTracker turnTracker;
   protected TRTradeOffer tradeOffer;
   protected TRDevCardList deck;
   protected int winner;
   protected int version = 0;

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
			if(p != null){
				p.isValid();
			}
		}
		this.log.isValid();
		this.chat.isValid();
		this.bank.isValid();
		this.deck.isValid();
		this.turnTracker.isValid();
		if(this.tradeOffer != null)
			this.tradeOffer.isValid();
		if(this.version < 0)
			throw new InvalidTranslatorModelException("Version is less than 0, toString: " + this.toString());
	}
	public TRDevCardList getDeck() {
		return deck;
	}
	public void setDeck(TRDevCardList deck) {
		this.deck = deck;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
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
		builder.append(", deck=");
		builder.append(deck);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + ((chat == null) ? 0 : chat.hashCode());
		result = prime * result + ((deck == null) ? 0 : deck.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + Arrays.hashCode(players);
		result = prime * result
				+ ((tradeOffer == null) ? 0 : tradeOffer.hashCode());
		result = prime * result
				+ ((turnTracker == null) ? 0 : turnTracker.hashCode());
		result = prime * result + version;
		result = prime * result + winner;
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
		ClientModel other = (ClientModel) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (chat == null) {
			if (other.chat != null)
				return false;
		} else if (!chat.equals(other.chat))
			return false;
		if (deck == null) {
			if (other.deck != null)
				return false;
		} else if (!deck.equals(other.deck))
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (!Arrays.equals(players, other.players))
			return false;
		if (tradeOffer == null) {
			if (other.tradeOffer != null)
				return false;
		} else if (!tradeOffer.equals(other.tradeOffer))
			return false;
		if (turnTracker == null) {
			if (other.turnTracker != null)
				return false;
		} else if (!turnTracker.equals(other.turnTracker))
			return false;
		if (version != other.version)
			return false;
		if (winner != other.winner)
			return false;
		return true;
	}

}
