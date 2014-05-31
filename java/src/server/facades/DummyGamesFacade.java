/**
 * 
 */
package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

/**
 * This is used for dependency injection for the GameFacade for testing.
 * @author scottdaly
 *
 */
public class DummyGamesFacade implements IGamesFacade{

	public DummyGamesFacade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResponse listGames(String json, UserAttributes ua) {
		String response = "[{'title':'Default Game',"
				  + "'id':0,"
				  + "'players':[{'color':'orange','name':'Sam','id':0},"
				  			 + "{'color':'blue','name':'Brooke','id':1},"
				  			 + "{'color':'red','name':'Pete','id':10},"
				  			 + "{'color':'green','name':'Mark','id':11}]},"
				  +"{'title':'AI Game',"
				  + "'id':1,"
				  + "'players':[{'color':'orange','name':'Pete','id':10},"
				  			 + "{'color':'blue','name':'Ken','id':-2},"
				  			 + "{'color':'yellow','name':'Steve','id':-2},"
				  			 + "{'color':'white','name':'Hannah','id':-2}]},"
	  			  +"{'title':'Empty Game',"
	  			  + "'id':2,"
	  			  + "'players':[{'color':'orange','name':'Sam','id':0},"
	  			  			 + "{'color':'blue','name':'Brooke','id':1},"
	  			  			 + "{'color':'red','name':'Pete','id':10},"
	  			  			 + "{'color':'green','name':'Mark','id':11}]}]";
		return new CommandResponse(response,"200");
	}

	@Override
	public CommandResponse createGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse joinGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse loadGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse saveGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

}
