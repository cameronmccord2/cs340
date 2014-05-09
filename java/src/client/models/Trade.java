package client.models;

import java.util.List;

public abstract class Trade implements ITrade {
	protected IParticipant from;
	protected IParticipant to;
	protected List<IResourceCard> send;
	protected List<IResourceCard> recieve;
	protected boolean accepted;
	
	public Trade(IParticipant from, IParticipant to, List<IResourceCard> sendResources, List<IResourceCard> recieveResources){
		super();
		this.from = from;
		this.to = to;
		this.send = sendResources;
		this.recieve = recieveResources;
	}
	
	@Override
	public IParticipant getFrom() {
		return from;
	}
	@Override
	public void setFrom(IParticipant from) {
		this.from = from;
	}
	@Override
	public IParticipant getTo() {
		return to;
	}
	@Override
	public void setTo(IParticipant to) {
		this.to = to;
	}
	@Override
	public List<IResourceCard> getSend() {
		return send;
	}
	@Override
	public void setSend(List<IResourceCard> send) {
		this.send = send;
	}
	@Override
	public List<IResourceCard> getRecieve() {
		return recieve;
	}
	@Override
	public void setRecieve(List<IResourceCard> recieve) {
		this.recieve = recieve;
	}
	@Override
	public boolean isAccepted() {
		return accepted;
	}
	
	private void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public void acceptTrade() {
		this.setAccepted(true);
		// TODO exchange cards
	}
}
