package client.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface IParticipant.
 */
public interface IParticipant {

	/**
	 * Gets the development cards.
	 *
	 * @return the development cards
	 */
	List<IDevelopmentCard> getDevelopmentCards();

	/**
	 * Sets the resource cards.
	 *
	 * @param resourceCards the new resource cards
	 */
	void setResourceCards(List<IResourceCard> resourceCards);

	/**
	 * Gets the resource cards.
	 *
	 * @return the resource cards
	 */
	List<IResourceCard> getResourceCards();

	/**
	 * Sets the development cards.
	 *
	 * @param developmentCards the new development cards
	 */
	void setDevelopmentCards(List<IDevelopmentCard> developmentCards);
	
	/**
	 * Will accept trade.
	 *
	 * @param trade the trade
	 * @return true, if successful
	 */
	boolean willAcceptTrade(ITrade trade);

}
