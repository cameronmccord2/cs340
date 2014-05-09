package client.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITrade.
 */
public interface ITrade {

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public IParticipant getFrom();
	
	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(IParticipant from);
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public IParticipant getTo();
	
	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(IParticipant to);
	
	/**
	 * Gets the send.
	 *
	 * @return the send
	 */
	public List<IResourceCard> getSend();
	
	/**
	 * Sets the send.
	 *
	 * @param send the new send
	 */
	public void setSend(List<IResourceCard> send);
	
	/**
	 * Gets the recieve.
	 *
	 * @return the recieve
	 */
	public List<IResourceCard> getRecieve();
	
	/**
	 * Sets the recieve.
	 *
	 * @param recieve the new recieve
	 */
	public void setRecieve(List<IResourceCard> recieve);
	
	/**
	 * Checks if is accepted.
	 *
	 * @return true, if is accepted
	 */
	public boolean isAccepted();
	
	/**
	 * Accept trade.
	 */
	public void acceptTrade();
	
	/**
	 * Can do.
	 *
	 * @return true, if successful
	 */
	public boolean canDo();
}
