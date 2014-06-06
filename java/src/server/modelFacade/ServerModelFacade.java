package server.modelFacade;

import com.google.gson.Gson;

import server.commands.ICommandParams;
import server.model.translator.ModelTRConverter;
import server.models.GameList;
import server.models.UserAttributes;
import server.models.exceptions.InvalidUserAttributesException;
import client.models.UserManager;

public class ServerModelFacade implements IServerModelFacade {

	protected UserManager userManager;
	protected GameList gameList;

	public ServerModelFacade(){
		this.userManager = new UserManager();
		this.gameList = new GameList();
	}
	
	public String getJsonGameModelString(ICommandParams params, UserAttributes ua) {

		Gson gson = new Gson();
		try {
			String modelJson =  gson.toJson(ModelTRConverter.toTRObject(gameList.getGameById(ua.getGameId())));
			System.out.println("model: " + modelJson);
			return modelJson;
		} catch (InvalidUserAttributesException e) {
			e.printStackTrace();
		}
		return null;
	}
}