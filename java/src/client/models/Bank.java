package client.models;

import java.util.HashMap;
import java.util.Map;

import client.models.translator.TRDevCardList;
import client.models.translator.TRResourceList;
import shared.definitions.ResourceType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bank.
 */
public class Bank extends Participant implements IBank {

	/**
	 * Instantiates a new bank.
	 */
	public Bank(){
		super();
	}

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
        developmentCards.put(DevelopmentCard.MONUMENT,2);
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
     * @param card
     * @return
     */
    public boolean canDrawResource(ResourceType card) {
        if(resourceCards.get(card) > 0)
            return true;
        return false;
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

    public DevelopmentCard drawRandomCard() {
        // TODO select random card and decrement its count
        return null;
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
}
