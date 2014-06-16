package client.models;

import java.io.Serializable;

import client.models.interfaces.ICard;

/**
 * Created by Daniel on 5/10/14.
 */
public class Card implements ICard, Serializable {

	@Override
	public String getName() {
		return "Card";
	}
}
