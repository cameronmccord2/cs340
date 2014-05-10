package client.models;

// TODO: Auto-generated Javadoc

import java.awt.image.BufferedImage;

/**
 * The Interface ICard.
 */
public interface ICard {

    /**
     * Gets the image.
     *
     * @return the image
     */
    public BufferedImage getImage();

    /**
     * Gets the name of the card (Ore, Soldier, etc).
     *
     * @return the name
     */
    public String getName();

}