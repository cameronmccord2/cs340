package client.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

public enum DevelopmentCard implements IDevelopmentCard {

	SOLDIER {
		@Override
		public String getName() {
			return "Knight";
		}

		@Override
		public DevCardType getType() {
			return DevCardType.SOLDIER;
		}
	},

	YEAR_OF_PLENTY {

		@Override
		public String getName() {
			return "Year of Plenty";
		}

		@Override
		public DevCardType getType() {
			return DevCardType.YEAR_OF_PLENTY;
		}
	},

	MONOPOLY {

		@Override
		public String getName() {
			return "Monopoly";
		}

		@Override
		public DevCardType getType() {
			return DevCardType.MONOPOLY;
		}
	},

	ROAD_BUILD {

		@Override
		public String getName() {
			return "Road Building";
		}

		@Override
		public DevCardType getType() {
			return DevCardType.ROAD_BUILD;
		}
	},

	MONUMENT {

		@Override
		public String getName() {
			return "Monument";
		}

		@Override
		public DevCardType getType() {
			return DevCardType.MONUMENT;
		}
	};

	public static Collection<Resource> getResourceCost() {

		HashSet<Resource> cost = new HashSet<>();
		cost.add(new Resource(ResourceType.ORE, 1));
		cost.add(new Resource(ResourceType.SHEEP, 1));
		cost.add(new Resource(ResourceType.WHEAT, 1));
		
		return cost;
	}
}
