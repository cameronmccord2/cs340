package client.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface IParticipant.
 */
public interface IParticipant {

	/**
	 * Gets the development cards for this participant.
	 *
	 * @return the development cards
	 */
	List<IDevelopmentCard> getDevelopmentCards();

	/**
	 * Sets the resource cards for this participant.
	 *
	 * @param resourceCards the new resource cards
	 */
	void setResourceCards(List<IResourceCard> resourceCards);

	/**
	 * Gets the resource cards for this participant.
	 *
	 * @return the resource cards
	 */
	List<IResourceCard> getResourceCards();

	/**
	 * Sets the development cards for this participant.
	 *
	 * @param developmentCards the new development cards
	 */
	void setDevelopmentCards(List<IDevelopmentCard> developmentCards);
	
	/**
	 * Will accept trade. Asks the player if they will accept this trade.
	 *
	 * @param trade the trade
	 * @return true, if accepted
	 */
	boolean willAcceptTrade(ITrade trade);

}
