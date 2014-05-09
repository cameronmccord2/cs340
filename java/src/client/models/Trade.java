package client.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Trade.
 */
public abstract class Trade implements ITrade {
	
	/** The from. */
	protected IParticipant from;
	
	/** The to. */
	protected IParticipant to;
	
	/** The send. */
	protected List<IResourceCard> send;
	
	/** The recieve. */
	protected List<IResourceCard> recieve;
	
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
	public Trade(IParticipant from, IParticipant to, List<IResourceCard> sendResources, List<IResourceCard> recieveResources){
		super();
		this.from = from;
		this.to = to;
		this.send = sendResources;
		this.recieve = recieveResources;
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
	 * @see client.models.ITrade#getSend()
	 */
	@Override
	public List<IResourceCard> getSend() {
		return send;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#setSend(java.util.List)
	 */
	@Override
	public void setSend(List<IResourceCard> send) {
		this.send = send;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#getRecieve()
	 */
	@Override
	public List<IResourceCard> getRecieve() {
		return recieve;
	}
	
	/* (non-Javadoc)
	 * @see client.models.ITrade#setRecieve(java.util.List)
	 */
	@Override
	public void setRecieve(List<IResourceCard> recieve) {
		this.recieve = recieve;
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
}
