package client.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.definitions.ResourceType;

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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Participant [developmentCards=");
		builder.append(developmentCards);
		builder.append(", resourceCards=");
		builder.append(resourceCards);
		builder.append("]");
		return builder.toString();
	}
}
