/**
 *
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import client.models.exceptions.InvalidTranslatorModelException;
import client.models.translator.TRResourceList;

/**
 * Holds information to discard certain cards to server
 *
 * @author scottdaly
 *
 */
public class DiscardedCards implements ICommandParams
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

	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("discardedCards") || this.playerIndex < 0 || this.discardedCards == null)
				throw new CommandParamNotValidException("type musnt be null or of length zero and must equal discardedCards, player index must be greater than zero and discarded cards mussnt be null: " + this.toString());
		try {
			this.discardedCards.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new CommandParamNotValidException("discarded cards claimed that it isnt valid: " + this.toString());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiscardedCards [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", discardedCards=");
		builder.append(discardedCards);
		builder.append("]");
		return builder.toString();
	}

}
