package client.models;

import shared.definitions.DevCardType;

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
    }
}
