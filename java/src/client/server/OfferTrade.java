package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import client.models.exceptions.InvalidTranslatorModelException;
import client.models.translator.TRResourceList;

/**
 * Holds the info needed to make an offer when wanting to trade on the server
 * @author scottdaly
 *
 */
public class OfferTrade  implements ICommandParams{
	private String type;
	private Integer playerIndex;
	private TRResourceList offer;
	private Integer receiver;
	
	
	public OfferTrade(int from, Integer to, TRResourceList offer, String type) {
		this.playerIndex = from;
		this.receiver = to;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OfferTrade [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", offer=");
		builder.append(offer);
		builder.append("]");
		return builder.toString();
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.receiver < 0 || this.offer == null)
			throw new CommandParamNotValidException("Reciever must be a valid player index and the offer mussnt be null: " + this.toString());
		try {
			this.offer.validate();
		} catch (InvalidTranslatorModelException e) {
			e.printStackTrace();
			throw new CommandParamNotValidException("The offer isnt valid, message: " + e.getLocalizedMessage() + ", toString: " + this.toString());
		}
	}
}
