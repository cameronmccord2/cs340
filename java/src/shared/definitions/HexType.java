package shared.definitions;

import client.models.ResourceCard;

public enum HexType {

	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT, WATER;

	public static ResourceCard getResourceCardForHexType(HexType hexType) {
		switch(hexType){
		case BRICK:
			return ResourceCard.BRICK;
		case ORE:
			return ResourceCard.ORE;
		case SHEEP:
			return ResourceCard.SHEEP;
		case WHEAT:
			return ResourceCard.WHEAT;
		case WOOD:
			return ResourceCard.WOOD;
		case WATER:
		case DESERT:
		default:
			break;
		}
		return null;
	}
}

