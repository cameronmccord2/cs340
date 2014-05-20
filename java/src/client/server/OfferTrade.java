package client.server;

import client.models.translator.TRResourceList;

/**
 * Holds the info needed to make an offer when wanting to trade on the server
 * @author scottdaly
 *
 */
public class OfferTrade {
	private String type;
	private Integer playerIndex;
	private TRResourceList offer;
	private Integer reciever;
	
	
	public OfferTrade(int from, Integer to, TRResourceList offer, String type) {
		this.playerIndex = from;
		this.reciever = to;
		this.offer = offer;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(Integer playerIndex) {
		this.playerIndex = playerIndex;
	}
	public TRResourceList getOffer() {
		return offer;
	}
	public void setOffer(TRResourceList offer) {
		this.offer = offer;
	}
	public Integer getReciever() {
		return reciever;
	}
	public void setReciever(Integer reciever) {
		this.reciever = reciever;
	}
}
