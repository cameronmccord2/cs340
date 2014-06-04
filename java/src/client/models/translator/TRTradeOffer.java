package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;
import client.server.OfferTrade;

public class TRTradeOffer implements IModelValid {
	protected Integer sender;
	protected Integer receiver;
	protected TRResourceList offer;
	public TRTradeOffer(){
		
	}
	
	public TRTradeOffer(OfferTrade ot) {
		this.sender = ot.getPlayerIndex();
		this.receiver = ot.getReceiver();
		this.offer = ot.getOffer();
	}

	public Integer getSender() {
		return sender;
	}
	public void setSender(Integer sender) {
		this.sender = sender;
	}
	public TRResourceList getOffer() {
		return offer;
	}
	public void setOffer(TRResourceList offer) {
		this.offer = offer;
	}
	@Override
	public void validate() throws InvalidTranslatorModelException {
		this.offer.validate();
		if(this.sender == null || this.sender < 0 || this.receiver == null || this.receiver < 0 || this.sender == this.receiver)
			throw new InvalidTranslatorModelException(this.toString());
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRTradeOffer [sender=");
		builder.append(sender);
		builder.append(", receiver=");
		builder.append(receiver);
		builder.append(", offer=");
		builder.append(offer);
		builder.append("]");
		return builder.toString();
	}
}
