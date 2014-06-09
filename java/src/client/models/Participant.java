package client.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.models.interfaces.IDevelopmentCard;
import client.models.interfaces.IParticipant;
import client.models.interfaces.IResourceCard;
import client.models.interfaces.ITrade;
import shared.definitions.HexType;

/**
 * The Class Participant.
 */
public abstract class Participant implements IParticipant {

	/** The development cards. */
	protected Map<IDevelopmentCard, Integer> developmentCards;

	/** The resource cards. */
	protected Map<IResourceCard, Integer> resourceCards;

	/**
	 * Instantiates a new participant.
	 *
	 * @param _developmentCards the development cards
	 * @param _resourceCards the resource cards
	 */
	public Participant(Map<IDevelopmentCard, Integer> _developmentCards, Map<IResourceCard, Integer> _resourceCards){
		this.developmentCards = _developmentCards;
		this.resourceCards = _resourceCards;
	}

	/**
	 * Instantiates a new participant.
	 */
	public Participant(){
		this.developmentCards = new HashMap<IDevelopmentCard, Integer>();
		this.resourceCards = new HashMap<IResourceCard, Integer>();

		developmentCards.put(DevelopmentCard.MONOPOLY, 0);
		developmentCards.put(DevelopmentCard.MONUMENT, 0);
		developmentCards.put(DevelopmentCard.ROAD_BUILD, 0);
		developmentCards.put(DevelopmentCard.SOLDIER, 0);
		developmentCards.put(DevelopmentCard.YEAR_OF_PLENTY, 0);

		resourceCards.put(ResourceCard.BRICK, 0);
		resourceCards.put(ResourceCard.ORE, 0);
		resourceCards.put(ResourceCard.WHEAT, 0);
		resourceCards.put(ResourceCard.WOOD, 0);
		resourceCards.put(ResourceCard.SHEEP, 0);
	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#getDevelopmentCards()
	 */
	@Override
	public Map<IDevelopmentCard, Integer> getDevelopmentCards() {
		return developmentCards;
	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#setDevelopmentCards(java.util.List)
	 */

	public void setDevelopmentCards(List<IDevelopmentCard> developmentCards) {
		for (IDevelopmentCard card : developmentCards) {
			this.developmentCards.put(card,this.developmentCards.get(card)+1);
		}
	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#setDevelopmentCards(java.util.List)
	 */
	@Override
	public void setDevelopmentCards(Map<IDevelopmentCard, Integer> developmentCards) {
		this.developmentCards = developmentCards;
	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#getResourceCards()
	 */
	@Override
	public Map<IResourceCard, Integer> getResourceCards() {
		return resourceCards;
	}

	@Override
	public int getNumResourceCards() {
		int count = 0;

		for ( Integer numCards: resourceCards.values() )
			count += numCards;

		return count;

	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#setResourceCards(java.util.List)
	 */

	public void setResourceCards(List<IResourceCard> resourceCards) {
		for (IResourceCard card : resourceCards) {
			this.resourceCards.put(card,this.resourceCards.get(card)+1);
		}
	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#setResourceCards(java.util.List)
	 */
	@Override
	public void setResourceCards(Map<IResourceCard, Integer> resourceCards) {
		this.resourceCards = resourceCards;
	}

	@Override
	public void incrementResourceByCount(HexType hexType, int count) {
		ResourceCard rc = HexType.getResourceCardForHexType(hexType);
		this.getResourceCards().put(rc, this.getResourceCards().get(rc) + count);
	}

	@Override
	public boolean hasEnoughResources(HexType hexType, int countRequired) {
		ResourceCard rc = HexType.getResourceCardForHexType(hexType);
		return (this.getResourceCards().get(rc) > countRequired);
	}

	@Override
	public void decrementResourceByCount(HexType hexType, int count) {
		ResourceCard rc = HexType.getResourceCardForHexType(hexType);
		this.getResourceCards().put(rc, this.getResourceCards().get(rc) - count);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Participant [developmentCards=");
		builder.append(developmentCards);
		builder.append(", resourceCards=");
		builder.append(resourceCards);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public void decrementResourceByCount(String resource, int count) {
		Integer finalCount = this.getResourceCards().get(ResourceCard.valueOf(resource.toUpperCase())) - count;
		this.getResourceCards().put((IResourceCard)ResourceCard.valueOf(resource.toUpperCase()), finalCount);
	}

	@Override
	public void incrementResourceByCount(String resource, int count) {
		Integer finalCount = count + this.getResourceCards().get(ResourceCard.valueOf(resource.toUpperCase()));
		this.getResourceCards().put((IResourceCard)ResourceCard.valueOf(resource.toUpperCase()), finalCount);
	}

	@Override
	public void incrementResourceByCount(ResourceCard card, Integer count) {
		Integer finalCount = count + this.getResourceCards().get(card);
		this.getResourceCards().put(card, finalCount);
	}
}
