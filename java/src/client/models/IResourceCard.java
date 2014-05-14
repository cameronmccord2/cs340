package client.models;

import shared.definitions.ResourceType;

public interface IResourceCard extends ICard {

    /**
     * Gets the type of the card.
     *
     * @return the type
     */
    public ResourceType getType();
}
