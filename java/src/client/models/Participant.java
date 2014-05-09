package client.models;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Participant.
 */
public abstract class Participant implements IParticipant {

	/** The development cards. */
	protected List<IDevelopmentCard> developmentCards;
	
	/** The resource cards. */
	protected List<IResourceCard> resourceCards;
	
	/**
	 * Instantiates a new participant.
	 *
	 * @param developmentCards the development cards
	 * @param resourceCards the resource cards
	 */
	public Participant(List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards){
		this.developmentCards = developmentCards;
		this.resourceCards = resourceCards;
	}
	
	/**
	 * Instantiates a new participant.
	 */
	public Participant(){
		this.developmentCards = new ArrayList<IDevelopmentCard>();
		this.resourceCards = new ArrayList<IResourceCard>();
	}
	
	/* (non-Javadoc)
	 * @see client.models.IParticipant#getDevelopmentCards()
	 */
	@Override
	public List<IDevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}
	
	/* (non-Javadoc)
	 * @see client.models.IParticipant#setDevelopmentCards(java.util.List)
	 */
	@Override
	public void setDevelopmentCards(List<IDevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}
	
	/* (non-Javadoc)
	 * @see client.models.IParticipant#getResourceCards()
	 */
	@Override
	public List<IResourceCard> getResourceCards() {
		return resourceCards;
	}
	
	/* (non-Javadoc)
	 * @see client.models.IParticipant#setResourceCards(java.util.List)
	 */
	@Override
	public void setResourceCards(List<IResourceCard> resourceCards) {
		this.resourceCards = resourceCards;
	}
}
