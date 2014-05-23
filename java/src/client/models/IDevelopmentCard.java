package client.models;

import shared.definitions.DevCardType;

/**
 * The Interface IDevelopmentCard.
 */
public interface IDevelopmentCard extends ICard {

    /**
     * Gets the type of the card.
     *
     * @return the type
     */
    public DevCardType getType();

}
