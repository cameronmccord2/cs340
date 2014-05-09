package shared.definitions;

import client.models.IDevelopmentCard;

import java.awt.image.BufferedImage;

public enum DevCardType implements IDevelopmentCard {

    SOLDIER {
        /**
         * Plays soldier card - adds 1 to army size and prompts robber movement
         */
        @Override
        public void play() {

        }

        @Override
        public BufferedImage getImage() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    },

    YEAR_OF_PLENTY {
        /**
         * Plays year of plenty card - prompts user to select two resources
         */
        @Override
        public void play() {

        }

        @Override
        public BufferedImage getImage() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    },

    MONOPOLY {
        /**
         * Plays a Monopoly card - prompts user for resource type
         */
        @Override
        public void play() {

        }

        @Override
        public BufferedImage getImage() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    },

    ROAD_BUILD {
        /**
         * Plays a RoadBuilding card, prompts user for road locations
         */
        @Override
        public void play() {

        }

        @Override
        public BufferedImage getImage() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    },

    MONUMENT {
        /**
         *  Plays a Monument Card, adding 1 to the player's score
         */
        @Override
        public void play() {

        }

        @Override
        public BufferedImage getImage() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    }
}

