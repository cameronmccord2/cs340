package client.models.translator;

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
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
