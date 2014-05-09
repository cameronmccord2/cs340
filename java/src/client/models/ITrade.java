package client.models;

import java.util.List;

public interface ITrade {

	public IParticipant getFrom();
	public void setFrom(IParticipant from);
	public IParticipant getTo();
	public void setTo(IParticipant to);
	public List<IResourceCard> getSend();
	public void setSend(List<IResourceCard> send);
	public List<IResourceCard> getRecieve();
	public void setRecieve(List<IResourceCard> recieve);
	public boolean isAccepted();
	public void acceptTrade();
	public boolean canDo();
}
