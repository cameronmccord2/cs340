package client.models;

import shared.definitions.ResourceType;

/**
 * Created by Daniel on 5/13/14.
 */
public enum ResourceCard implements IResourceCard {

    WOOD {

        @Override
        public String getName() {
            return "Wood";
        }

        @Override
        public ResourceType getType() {
            return ResourceType.WOOD;
        }
    },

    BRICK {

        @Override
        public String getName() {
            return "Brick";
        }

        @Override
        public ResourceType getType() {
            return ResourceType.BRICK;
        }
    },

    WHEAT {

        @Override
        public String getName() {
            return "Wheat";
        }

        @Override
        public ResourceType getType() {
            return ResourceType.WHEAT ;
        }
    },

    SHEEP {

        @Override
        public String getName() {
            return "Sheep";
        }

        @Override
        public ResourceType getType() {
            return ResourceType.SHEEP;
        }
    },

    ORE {

        @Override
        public String getName() {
            return "Ore";
        }

        @Override
        public ResourceType getType() {
            return ResourceType.ORE;
        }
    }
}
