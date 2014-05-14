package client.models;

import java.util.List;
import java.util.Map;

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
	Map<IDevelopmentCard, Integer> getDevelopmentCards();

    /**
     * Sets the resource cards for this participant.
     *
     * @param resourceCards the new resource cards
     */
    void setResourceCards(List<IResourceCard> resourceCards);

    /**
     * Sets the resource cards for this participant.
     *
     * @param resourceCards the new resource cards
     */
    void setResourceCards(Map<IResourceCard, Integer> resourceCards);

	/**
	 * Gets the resource cards for this participant.
	 *
	 * @return the resource cards
	 */
	Map<IResourceCard, Integer> getResourceCards();

	/**
	 * Sets the development cards for this participant.
	 *
	 * @param developmentCards the new development cards
	 */
	void setDevelopmentCards(List<IDevelopmentCard> developmentCards);

	/**
	 * Sets the development cards for this participant.
	 *
	 * @param developmentCards the new development cards
	 */
	void setDevelopmentCards(Map<IDevelopmentCard, Integer> developmentCards);
	
	/**
	 * Will accept trade. Asks the player if they will accept this trade.
	 *
	 * @param trade the trade
	 * @return true, if accepted
	 */
	boolean willAcceptTrade(ITrade trade);

}
