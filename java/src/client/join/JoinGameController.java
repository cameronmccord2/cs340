package client.join;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import client.base.Controller;
import client.base.IAction;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.misc.IMessageView;
import client.models.ICatanModelObserver;
import client.models.IGame;
import client.models.IPlayer;
import client.models.IProxy;
import client.models.Poller;
import client.models.ServerResponse;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.server.CreateGame;
import client.server.ServerJoinGame;

/**
 * Implementation for the join game controller
 */
@SuppressWarnings({"unused"})
public class JoinGameController extends Controller implements IJoinGameController, ICatanModelObserver
{

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private GameInfo selectedGame;
	private IProxy proxy;
	private PlayerInfo self;

	/**
	 * JoinGameController constructor
	 *
	 * @param view
	 *			Join game view
	 * @param newGameView
	 *			New game view
	 * @param selectColorView
	 *			Select color view
	 * @param messageView
	 *			Message view (used to display error messages that occur while
	 *			the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView,
									  ISelectColorView selectColorView,
									  IMessageView messageView, IProxy proxy)
	{
		super(view);
		this.proxy = proxy;
		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
		this.proxy.getFacade().registerAsObserver(this);
	}

	public IJoinGameView getJoinGameView()
	{
		return (IJoinGameView)super.getView();
	}

	/**
	 * Returns the action to be executed when the user joins a game
	 *
	 * @return The action to be executed when the user joins a game
	 */

	public IAction getJoinAction()
	{
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 *
	 * @param value
	 *			The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value)
	{
		joinAction = value;
	}

	public INewGameView getNewGameView()
	{
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView)
	{
		this.newGameView = newGameView;
	}

	public ISelectColorView getSelectColorView()
	{
		return selectColorView;
	}

	public void setSelectColorView(ISelectColorView selectColorView)
	{
		this.selectColorView = selectColorView;
	}

	public IMessageView getMessageView()
	{
		return messageView;
	}

	public void setMessageView(IMessageView messageView)
	{
		this.messageView = messageView;
	}

	@Override
	public void start()
	{
		self = new PlayerInfo();
		self.setId(this.proxy.getrUser().getPlayerID());
		self.setName(this.proxy.getrUser().getName());

		List<IGame> allGames = this.proxy.getGamesList();
		List<GameInfo> games = new ArrayList<GameInfo>();
		for (IGame g : allGames) {
			GameInfo serverGame = new GameInfo();
			serverGame.setId(g.getGameInfo().getId());
			serverGame.setTitle(g.getGameInfo().getTitle());
			if(g.getPlayers() != null){
				for (IPlayer p : g.getPlayers()){
					serverGame.addPlayer(p.getPlayerInfo());
				}
			}

			games.add(serverGame);
		}

		getJoinGameView().setGames(games, self);

		try {
			if(this.proxy.getFacade().getCatanMap() == null)
			{
				if(!getJoinGameView().isModalShowing())
					getJoinGameView().showModal();
			}
		} catch (CantFindGameModelException e) {
			if(!getJoinGameView().isModalShowing()){
				getJoinGameView().showModal();
			}
		}

	}

	@Override
	public void startCreateNewGame()
	{
		if(!getNewGameView().isModalShowing())
			getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame()
	{
		if(getNewGameView().isModalShowing())
			getNewGameView().closeModal();
	}

	@Override
	public void createNewGame()
	{
		CreateGame newGame = new CreateGame(getNewGameView().getRandomlyPlaceHexes(),
											getNewGameView().getRandomlyPlaceNumbers(),
											getNewGameView().getUseRandomPorts(),
											getNewGameView().getTitle());

		if(proxy.postGamesCreate(newGame).getResponseCode() == 200)
		{
			if(getNewGameView().isModalShowing())
				getNewGameView().closeModal();
			this.start();
		}
	}

	@Override
	public void startJoinGame(GameInfo game)
	{

		selectedGame = game;

		if(getJoinGameView().isModalShowing())
			getJoinGameView().closeModal();

		for(PlayerInfo p : game.getPlayers())
		{
			if(p.getId() != self.getId())
				getSelectColorView().setColorEnabled(p.getColor(), false);
			else {
				joinGame(p.getColor());
				return;
			}
		}

		if(!getSelectColorView().isModalShowing())
			getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame()
	{
		for(CatanColor color : CatanColor.values())
			getSelectColorView().setColorEnabled(color, true);

		if(getSelectColorView().isModalShowing())
			getSelectColorView().closeModal();
		if(!getJoinGameView().isModalShowing())
			getJoinGameView().showModal();
	}

	@Override
	public void joinGame(CatanColor color)
	{
		if(getSelectColorView().isModalShowing())
			getSelectColorView().closeModal();
		ServerJoinGame join = new ServerJoinGame(selectedGame.getId(), color.toString().toLowerCase());
		ServerResponse sr = this.proxy.postGamesJoin(join);
		System.out.println("sr: " + sr.getJson());
		if(sr.getJson().equals("Success"))
		{
			// If join succeeded
			joinAction.execute();
			
			if(getSelectColorView().isModalShowing())
				getSelectColorView().closeModal();
			if(getJoinGameView().isModalShowing())
				getJoinGameView().closeModal();
		}
	}

	@Override
	public void update()
	{
		if(getSelectColorView().isModalShowing())
			getSelectColorView().closeModal();
		if(getJoinGameView().isModalShowing())
			getJoinGameView().closeModal();
	}

}
