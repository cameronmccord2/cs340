/**
 * 
 */
package server.facades;

import server.commands.CommandResponse;
import server.models.UserAttributes;

/**
 * This is used for dependency injection for the GameFacade for testing.
 * @author scottdaly
 *
 */
public class DummyGameFacade implements IGameFacade{

	public DummyGameFacade() {
		
	}

	@Override
	public CommandResponse getCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse runCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse reset(String json, UserAttributes ua) {
		return new CommandResponse(updateModel,"200");
	}

	@Override
	public CommandResponse getGameModel(String json, UserAttributes ua) {
		return new CommandResponse(updateModel,"200");
	}
	
	private String updateModel = "{ 'deck': { 'yearOfPlenty': 2, "
			   + "'monopoly': 2, "
			   + "'soldier': 14, "
			   + "'roadBuilding': 2, "
			   + "'monument': 5 }, "
			   
+ "'map': { 'hexes': [ { 'location': { 'x': 0, 'y': -2 } }, "
					  + "{ 'resource': 'brick', 'location': { 'x': 1, 'y': -2 }, 'number': 4 }, "
					  + "{ 'resource': 'wood', 'location': { 'x': 2, 'y': -2 }, 'number': 11 }, "
					  + "{ 'resource': 'brick', 'location': { 'x': -1, 'y': -1 }, 'number': 8 }, "
					  + "{ 'resource': 'wood', 'location': { 'x': 0, 'y': -1 }, 'number': 3 }, "
					  + "{ 'resource': 'ore', 'location': { 'x': 1, 'y': -1 }, 'number': 9 }, "
					  + "{ 'resource': 'sheep', 'location': { 'x': 2, 'y': -1 }, 'number': 12 }, "
					  + "{ 'resource': 'ore', 'location': { 'x': -2, 'y': 0 }, 'number': 5 }, "
					  + "{ 'resource': 'sheep', 'location': { 'x': -1, 'y': 0 }, 'number': 10 }, "
					  + "{ 'resource': 'wheat', 'location': { 'x': 0, 'y': 0 }, 'number': 11 }, "
					  + "{ 'resource': 'brick', 'location': { 'x': 1, 'y': 0 }, 'number': 5 }, "
					  + "{ 'resource': 'wheat', 'location': { 'x': 2, 'y': 0 }, 'number': 6 }, "
					  + "{ 'resource': 'wheat', 'location': { 'x': -2, 'y': 1 }, 'number': 2 }, "
					  + "{ 'resource': 'sheep', 'location': { 'x': -1, 'y': 1 }, 'number': 9 }, "
					  + "{ 'resource': 'wood', 'location': { 'x': 0, 'y': 1 }, 'number': 4 }, "
					  + "{ 'resource': 'sheep', 'location': { 'x': 1, 'y': 1 }, 'number': 10 }, "
					  + "{ 'resource': 'wood', 'location': { 'x': -2, 'y': 2 }, 'number': 6 }, "
					  + "{ 'resource': 'ore', 'location': { 'x': -1, 'y': 2 }, 'number': 3 }, "
					  + "{ 'resource': 'wheat', 'location': { 'x': 0, 'y': 2 }, 'number': 8 } ], "
					  
			   + "'roads': [ { 'owner': 2, 'location': { 'direction': 'S', 'x': 1, 'y': -1 } }, "
			   			  + "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': 2, 'y': -2 } }, "
			   			  + "{ 'owner': 0, 'location': { 'direction': 'S', 'x': 0, 'y': 1 } }, "
			   			  + "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -2, 'y': 1 } }, "
			   			  + "{ 'owner': 2, 'location': { 'direction': 'S', 'x': 0, 'y': 0 } }, "
			   			  + "{ 'owner': 0, 'location': { 'direction': 'SW', 'x': 2, 'y': 0 } }, "
			   			  + "{ 'owner': 1, 'location': { 'direction': 'S', 'x': -1, 'y': -1 } }, "
			   			  + "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': -1, 'y': 1 } } ], "
			   			  
			   + "'cities': [], "
			   
			   + "'settlements': [ { 'owner': 3, 'location': { 'direction': 'SE', 'x': 1, 'y': -2 } }, "
			   					+ "{ 'owner': 2, 'location': { 'direction': 'SW', 'x': 0, 'y': 0 } }, "
			   					+ "{ 'owner': 2, 'location': { 'direction': 'SW', 'x': 1, 'y': -1 } }, "
			   					+ "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -1, 'y': -1 } }, "
			   					+ "{ 'owner': 0, 'location': { 'direction': 'SE', 'x': 0, 'y': 1 } }, "
			   					+ "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -2, 'y': 1 } }, "
			   					+ "{ 'owner': 0, 'location': { 'direction': 'SW', 'x': 2, 'y': 0 } }, "
			   					+ "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': -1, 'y': 1 } } ], "
			   					
				   + "'radius': 3, "
				   
				   + "'ports': [ { 'ratio': 2, 'resource': 'wheat', 'direction': 'S', 'location': { 'x': -1, 'y': -2 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'SW', 'location': { 'x': 3, 'y': -3 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'ore', 'direction': 'S', 'location': { 'x': 1, 'y': -3 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'SE', 'location': { 'x': -3, 'y': 0 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'wood', 'direction': 'NE', 'location': { 'x': -3, 'y': 2 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'brick', 'direction': 'NE', 'location': { 'x': -2, 'y': 3 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'NW', 'location': { 'x': 2, 'y': 1 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'sheep', 'direction': 'NW', 'location': { 'x': 3, 'y': -1 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'N', 'location': { 'x': 0, 'y': 3 } } ], "
				   			  
			   + "'robber': { 'x': 0, 'y': -2 } }, "
			   
			   + "'players': [ { 'resources': { 'brick': 4, 'wood': 3, 'sheep': 4, 'wheat': 4, 'ore': 4 }, "
			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'roads': 13, "
			   				  + "'cities': 4, "
			   				  + "'settlements': 3, "
			   				  + "'soldiers': 0, "
			   				  + "'victoryPoints': 2, "
			   				  + "'monuments': 0, "
			   				  + "'playedDevCard': false, "
			   				  + "'discarded': false, "
			   				  + "'playerID': 0, "
			   				  + "'playerIndex': 0, "
			   				  + "'name': 'Sam', "
			   				  + "'color': 'orange' }, "
			   				+ "{ 'resources': { 'brick': 4, 'wood': 4, 'sheep': 4, 'wheat': 4, 'ore': 4 }, "
			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'roads': 13, "
			   				  + "'cities': 4, "
			   				  + "'settlements': 3, "
			   				  + "'soldiers': 0, "
			   				  + "'victoryPoints': 2, "
			   				  + "'monuments': 0, "
			   				  + "'playedDevCard': false, "
			   				  + "'discarded': false, "
			   				  + "'playerID': 1, "
			   				  + "'playerIndex': 1, "
			   				  + "'name': 'Brooke', "
			   				  + "'color': 'blue' }, "
			   				+ "{ 'resources': { 'brick': 4, 'wood': 4, 'sheep': 4, 'wheat': 4, 'ore': 4 }, "
			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'roads': 13, "
			   				  + "'cities': 4, "
			   				  + "'settlements': 3, "
			   				  + "'soldiers': 0, "
			   				  + "'victoryPoints': 2, "
			   				  + "'monuments': 0, "
			   				  + "'playedDevCard': false, "
			   				  + "'discarded': false, "
			   				  + "'playerID': 10, "
			   				  + "'playerIndex': 2, "
			   				  + "'name': 'Pete', "
			   				  + "'color': 'red' }, "
			   				+ "{ 'resources': { 'brick': 4, 'wood': 4, 'sheep': 4, 'wheat': 4, 'ore': 4 }, "
			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
			   				  + "'roads': 13, "
			   				  + "'cities': 4, "
			   				  + "'settlements': 3, "
			   				  + "'soldiers': 0, "
			   				  + "'victoryPoints': 2, "
			   				  + "'monuments': 0, "
			   				  + "'playedDevCard': false, "
			   				  + "'discarded': false, "
			   				  + "'playerID': 11, "
			   				  + "'playerIndex': 3, "
			   				  + "'name': 'Mark', "
			   				  + "'color': 'green' } ], "
			   				  
				   + "'log': { 'lines': [ { 'source': 'Sam', 'message': 'Sam built a road' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sam built a settlement' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sams turn just ended' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a road' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a settlement' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brookes turn just ended' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a road' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a settlement' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Petes turn just ended' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a road' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a settlement' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Marks turn just ended' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a road' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a settlement' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Marks turn just ended' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a road' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a settlement' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Petes turn just ended' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a road' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a settlement' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brookes turn just ended' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sam built a road' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sam built a settlement' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sams turn just ended' } ] }, "
				   					   
				   + "'chat': { 'lines': [] }, "
				   
				   + "'bank': { 'brick': 23, 'wood': 21, 'sheep': 20, 'wheat': 22, 'ore': 22 }, "
				   
				   + "'turnTracker': { 'status': 'Rolling', 'currentTurn': 0, 'longestRoad': -1, 'largestArmy': -1 }, "
				   
				   + "'winner': -1, "
				   
//				   + "'tradeOffer': { 'sender': 1, 'receiver': 0, 'offer': { 'brick': 1, 'wood': 2, 'sheep': -1, 'wheat': -1, 'ore': -3 } },"
				   
				   + "'version': 0 }";

}
