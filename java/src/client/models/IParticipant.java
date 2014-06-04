package client.models;

import java.util.Collection;
import java.util.Map;

import shared.definitions.HexType;

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
	void setDevelopmentCards(Map<IDevelopmentCard, Integer> developmentCards);

	/**
	 * Will accept trade. Asks the player if they will accept this trade.
	 *
	 * @param trade the trade
	 * @return true, if accepted
	 */
	boolean willAcceptTrade(ITrade trade);

	/**
	 * Get Number of Resource Cards held by the player.
	 *
	 * @return number of resource cards
	 */
	int getNumResourceCards();
	
	boolean hasEnoughResources(HexType hexType, int countRequired);
	
	void decrementResourceByCount(HexType hexType, int countRequired);

	void incrementResourceByCount(HexType hexType, int count);

	void deductResources(Collection<Resource> cost);

}
