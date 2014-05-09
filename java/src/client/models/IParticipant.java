package client.models;

import java.util.List;

public interface IParticipant {

	List<IDevelopmentCard> getDevelopmentCards();

	void setResourceCards(List<IResourceCard> resourceCards);

	List<IResourceCard> getResourceCards();

	void setDevelopmentCards(List<IDevelopmentCard> developmentCards);
	
	boolean willAcceptTrade(ITrade trade);

}
