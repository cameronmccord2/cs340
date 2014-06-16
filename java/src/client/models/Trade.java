package client.models;

import java.io.Serializable;
import java.util.Map;

import client.models.interfaces.IParticipant;
import client.models.interfaces.IResourceCard;
import client.models.interfaces.ITrade;

/**
 * The Class Trade.
 */
public abstract class Trade implements ITrade, Serializable {
	
	/** The from. */
	protected IParticipant from;
	
	/** The to. */
	protected IParticipant to;
	
	/** The send. */
	protected Map<IResourceCard, Integer> send;
	
	/** The recieve. */
	protected Map<IResourceCard, Integer> recieve;
	
	/** The accepted. */
	protected boolean accepted;
	
	/**
	 * Instantiates a new trade.
	 *
	 * @param from the from
	 * @param to the to
	 * @param sendResources the send resources
	 * @param recieveResources the recieve resources
	 */
	public Trade(IParticipant from, IParticipant to, Map<IResourceCard, Integer> sendResources, Map<IResourceCard, Integer> recieveResources){
		super();
		this.from = from;
		this.to = to;
		this.send = sendResources;
		this.recieve = recieveResources;
	}
	
	public Trade(IParticipant from2, IParticipant to2) {
		super();
		this.from = from2;
		this.to = to2;
	}

	/* (non-Javadoc)
	 * @see client.models.ITrade#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return from;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#setFrom(client.models.IParticipant)
	 */
	@Override
	public void setFrom(IParticipant from) {
		this.from = from;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#getTo()
	 */
	@Override
	public IParticipant getTo() {
		return to;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#setTo(client.models.IParticipant)
	 */
	@Override
	public void setTo(IParticipant to) {
		this.to = to;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#isAccepted()
	 */
	@Override
	public boolean isAccepted() {
		return accepted;
	}
	
	/**
	 * Sets the accepted.
	 *
	 * @param accepted the new accepted
	 */
	private void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	/* (non-Javadoc)
	 * @see client.models.ITrade#acceptTrade()
	 */
	@Override
	public void acceptTrade() {
		this.setAccepted(true);
		// TODO exchange cards
	}

	public Map<IResourceCard, Integer> getSend() {
		return send;
	}

	public void setSend(Map<IResourceCard, Integer> send) {
		this.send = send;
	}

	public Map<IResourceCard, Integer> getRecieve() {
		return recieve;
	}

	public void setRecieve(Map<IResourceCard, Integer> recieve) {
		this.recieve = recieve;
	}
}
