package client.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import client.models.interfaces.IBank;
import client.models.interfaces.IDevelopmentCard;
import client.models.interfaces.IResourceCard;
import client.models.interfaces.ITrade;
import client.models.translator.TRDevCardList;
import client.models.translator.TRResourceList;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bank.
 */
public class Bank extends Participant implements IBank, Serializable {

	/**
	 * Instantiates a new bank.
	 */
	public Bank(){
		super();
	}

	/**
	 * Constructor for use with Translator Classes
	 */
	public Bank(TRDevCardList deck, TRResourceList bank) {
		this();

		developmentCards.put(DevelopmentCard.MONOPOLY, deck.getMonopoly());
		developmentCards.put(DevelopmentCard.MONUMENT, deck.getMonument());
		developmentCards.put(DevelopmentCard.ROAD_BUILD, deck.getRoadBuilding());
		developmentCards.put(DevelopmentCard.SOLDIER, deck.getSoldier());
		developmentCards.put(DevelopmentCard.YEAR_OF_PLENTY, deck.getYearOfPlenty());

		resourceCards.put(ResourceCard.BRICK, bank.getBrick());
		resourceCards.put(ResourceCard.ORE, bank.getOre());
		resourceCards.put(ResourceCard.WHEAT, bank.getWheat());
		resourceCards.put(ResourceCard.WOOD, bank.getWood());
		resourceCards.put(ResourceCard.SHEEP, bank.getSheep());
	}
	
	/**
	 * Instantiates a new bank.
	 *
	 * @param developmentCards the development cards
	 * @param resourceCards the resource cards
	 */
	public Bank(Map<IDevelopmentCard, Integer> developmentCards, Map<IResourceCard, Integer> resourceCards){
		super(developmentCards, resourceCards);
	}

	/**
	 * Sets up a bank participant ready for a new game to begin
	 */
	@Override
	public void setUpNewBank(){
		developmentCards.put(DevelopmentCard.YEAR_OF_PLENTY,2);
		developmentCards.put(DevelopmentCard.ROAD_BUILD,2);
		developmentCards.put(DevelopmentCard.MONOPOLY,2);
		developmentCards.put(DevelopmentCard.MONUMENT,5);
		developmentCards.put(DevelopmentCard.SOLDIER,14);

		resourceCards.put(ResourceCard.SHEEP,19);
		resourceCards.put(ResourceCard.ORE,19);
		resourceCards.put(ResourceCard.WOOD,19);
		resourceCards.put(ResourceCard.WHEAT,19);
		resourceCards.put(ResourceCard.BRICK,19);

	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#willAcceptTrade(client.models.ITrade)
	 */
	@Override
	public boolean willAcceptTrade(ITrade trade) {
		return true;
	}

	/**
	 * Checks if the Bank is able to provide a Resource of the type indicated
	 *
	 * @param type
	 * @return
	 */
	public boolean canDrawResource(ResourceCard type) {
		if(resourceCards.containsKey(type) && resourceCards.get(type) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Draw a Resource of the type specified
	*/
	public ResourceCard drawResource(ResourceCard type) {
		if(canDrawResource(type)) {
			// TODO return resource type and decrement count
		}
		return null;
	}

	public Integer countResourceCards(ResourceType type) {
		for (IResourceCard card : resourceCards.keySet()) {
			if(card.getType() == type)
				return resourceCards.get(card);
		}
		//assert false;
		return 0;
	}

	/**
	 * Checks if the Bank is able to provide a Development Card
	 *
	 * @return
	 */
	public boolean canDrawDevCard() {
		if(countDevCards() > 0)
			return true;
		return false;
	}

	/**
	 * Counts the total number of Dev Cards available in the Dev Card Deck
	 * @return
	 */
	public Integer countDevCards() {
		int totalCards = 0;
		for (int i : developmentCards.values()) {
			totalCards += i;
		}
		return totalCards;
	}

	public Integer countDevCards(DevCardType type) {
		for (IDevelopmentCard card : developmentCards.keySet()) {
			if(card.getType() == type)
				return developmentCards.get(card);
		}
		assert false;
		return 0;
	}

	public DevelopmentCard drawRandomDevCard() {
		if( ! canDrawDevCard() )
			return null;

		Random r = new Random();
		int cardIndex = r.nextInt(countDevCards());
		DevelopmentCard currentType = null;
		int cardCount = 0;

		for (IDevelopmentCard card : developmentCards.keySet()) {
			currentType = (DevelopmentCard) card;
			cardCount += countDevCards(currentType.getType());

			if(cardCount >= cardIndex) {
				developmentCards.put(currentType, developmentCards.get(currentType) -1 );
				break;
			}
		}

		return currentType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bank [developmentCards=");
		builder.append(developmentCards);
		builder.append(", resourceCards=");
		builder.append(resourceCards);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public void deductResources(Collection<Resource> cost) {
		Iterator<Resource> it = cost.iterator();
		Map<IResourceCard, Integer> cards = this.getResourceCards();

		while(it.hasNext()) {
			Resource type = it.next();
			ResourceCard card = ResourceCard.valueOf(type.getResourceType().toString());
			cards.put( card, cards.get(card) - type.getAmount() );
		}

		this.setResourceCards(cards);
	}

	@Override
	public boolean hasEnoughResources(String outputResource, int countRequired) {
		switch(outputResource.toLowerCase()){
		case "wood":
			return (this.getResourceCards().get(ResourceCard.WOOD) >= countRequired);
		case "brick":
			return (this.getResourceCards().get(ResourceCard.BRICK) >= countRequired);
		case "ore":
			return (this.getResourceCards().get(ResourceCard.ORE) >= countRequired);
		case "wheat":
			return (this.getResourceCards().get(ResourceCard.WHEAT) >= countRequired);
		case "sheep":
			return (this.getResourceCards().get(ResourceCard.SHEEP) >= countRequired);
		}
		return false;
	}
}





























