package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRTradeOffer implements IModelValid {
	protected Integer sender;
	protected Integer reciever;
	protected TRResourceList offer;
	public Integer getSender() {
		return sender;
	}
	public void setSender(Integer sender) {
		this.sender = sender;
	}
	public Integer getReciever() {
		return reciever;
	}
	public void setReciever(Integer reciever) {
		this.reciever = reciever;
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
		if(this.sender == null || this.sender < 0 || this.reciever == null || this.reciever < 0)
			throw new InvalidTranslatorModelException(this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRTradeOffer [sender=");
		builder.append(sender);
		builder.append(", reciever=");
		builder.append(reciever);
		builder.append(", offer=");
		builder.append(offer);
		builder.append("]");
		return builder.toString();
	}
}
