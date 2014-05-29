/**
 *
 */
package client.server;

import client.models.translator.TRResourceList;

/**
 * Holds information to discard certain cards to server
 *
 * @author scottdaly
 *
 */
public class DiscardedCards extends CommandParams
{
	private String type;
	private Integer playerIndex;
	private TRResourceList discardedCards;

	public DiscardedCards(String type,
								 Integer playerIndex,
								 TRResourceList discardedCards)
	{
		this.type = type;
		this.playerIndex = playerIndex;
		this.discardedCards = discardedCards;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *           the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the playerIndex
	 */
	public Integer getPlayerIndex()
	{
		return playerIndex;
	}

	/**
	 * @param playerIndex
	 *           the playerIndex to set
	 */
	public void setPlayerIndex(Integer playerIndex)
	{
		this.playerIndex = playerIndex;
	}

	/**
	 * @return the discardedCards
	 */
	public TRResourceList getDiscardedCards()
	{
		return discardedCards;
	}

	/**
	 * @param discardedCards
	 *           the discardedCards to set
	 */
	public void setDiscardedCards(TRResourceList discardedCards)
	{
		this.discardedCards = discardedCards;
	}

}
