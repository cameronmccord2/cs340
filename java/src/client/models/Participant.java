package client.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant implements IParticipant {

	protected List<IDevelopmentCard> developmentCards;
	protected List<IResourceCard> resourceCards;
	
	public Participant(List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards){
		this.developmentCards = developmentCards;
		this.resourceCards = resourceCards;
	}
	
	public Participant(){
		this.developmentCards = new ArrayList<IDevelopmentCard>();
		this.resourceCards = new ArrayList<IResourceCard>();
	}
	@Override
	public List<IDevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}
	@Override
	public void setDevelopmentCards(List<IDevelopmentCard> developmentCards) {
		this.developmentCards = developmentCards;
	}
	@Override
	public List<IResourceCard> getResourceCards() {
		return resourceCards;
	}
	@Override
	public void setResourceCards(List<IResourceCard> resourceCards) {
		this.resourceCards = resourceCards;
	}
}
