package client.models;

import client.models.interfaces.ICard;

/**
 * Created by Daniel on 5/10/14.
 */
public class Card implements ICard {

	@Override
	public String getName() {
		return "Card";
	}
}
