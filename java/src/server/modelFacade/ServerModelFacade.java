package server.modelFacade;

import java.util.List;

import com.google.gson.Gson;

import server.commands.ICommandParams;
import server.model.translator.ModelTRConverter;
import server.models.GameList;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;
import server.models.exceptions.InvalidUserAttributesException;
import client.models.User;
import client.models.UserManager;
import client.models.interfaces.IGame;

public class ServerModelFacade implements IServerModelFacade {

	protected UserManager userManager;
	protected GameList gameList;

	public ServerModelFacade(GameList gameList) {
		this.userManager = new UserManager();
		this.gameList = gameList;
	}

	@Override
	public ServerFacadeResponse getJsonGameModelString(ICommandParams params, UserAttributes ua) {

		Gson gson = new Gson();
		try {
			System.out.println("getting game model: " + params.toString());
			IGame game = gameList.getGameById(ua.getGameId());
			String modelJson =  gson.toJson(ModelTRConverter.toTRObject(game));
//			System.out.println("model: " + modelJson);
			
			return new ServerFacadeResponse(false, modelJson);
		} catch (InvalidUserAttributesException e) {
			e.printStackTrace();
		}
		return new ServerFacadeResponse(false, "Getting game model failed");
	}
	
	@Override
	public IGame getGameById(Integer id){
		System.out.println("gid1");
		try {
			if(gameList == null)
				System.out.println("is null");
			System.out.println("not null");
			IGame game = gameList.getGameById(id);
			System.out.println(game);
			return game;
		} catch (InvalidUserAttributesException e) {
			System.out.println("gid2");
			e.printStackTrace();
		}
		System.out.println("gid3");
		return null;
	}

	@Override
	public void setUsers(List<User> userss) {
		this.userManager.setUsers(userss);
	}
}
