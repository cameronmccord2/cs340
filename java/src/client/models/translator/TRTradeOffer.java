package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRTradeOffer implements IModelValid {
	protected Integer sender;
	protected Integer receiver;
	protected TRResourceList offer;
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
	public void isValid() throws InvalidTranslatorModelException {
		this.offer.isValid();
		if(this.sender == null || this.sender < 0 || this.receiver == null || this.receiver < 0)
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
