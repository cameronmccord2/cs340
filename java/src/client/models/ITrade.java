package client.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITrade.
 */
public interface ITrade {

	/**
	 * Gets the from participant for the trade.
	 *
	 * @return the from participant
	 */
	public IParticipant getFrom();
	
	/**
	 * Sets the from participant.
	 *
	 * @param from the new from participant
	 */
	public void setFrom(IParticipant from);
	
	/**
	 * Gets the to participant.
	 *
	 * @return the to participant
	 */
	public IParticipant getTo();
	
	/**
	 * Sets the to participant.
	 *
	 * @param to the new to participant
	 */
	public void setTo(IParticipant to);
	
	/**
	 * Gets the resource cards from the from participant.
	 *
	 * @return the send resource cards
	 */
	public List<IResourceCard> getSend();
	
	/**
	 * Sets the send resource cards.
	 *
	 * @param send the new send resource cards
	 */
	public void setSend(List<IResourceCard> send);
	
	/**
	 * Gets the recieve resource cards.
	 *
	 * @return the recieve resource cards
	 */
	public List<IResourceCard> getRecieve();
	
	/**
	 * Sets the recieve resource cards.
	 *
	 * @param recieve the new recieve resource cards
	 */
	public void setRecieve(List<IResourceCard> recieve);
	
	/**
	 * Checks if the trade is accepted.
	 *
	 * @return true, if the trade has been accepted
	 */
	public boolean isAccepted();
	
	/**
	 * Accept trade. This accepts the trade.
	 */
	public void acceptTrade();
	
	/**
	 * Can do tests to see if the trade is valid and ok to do.
	 *
	 * @return true, if the trade is ok to do
	 */
	public boolean canDo();
}
