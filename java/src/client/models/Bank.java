package client.models;

import java.util.Map;
import java.util.Random;

import client.models.translator.TRDevCardList;
import client.models.translator.TRResourceList;
import shared.definitions.DevCardType;
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
}
