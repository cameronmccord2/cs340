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
import client.models.exceptions.CantFindPlayerException;
import client.server.CreateGame;
import client.server.ServerJoinGame;

/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, ICatanModelObserver
{
	
	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private GameInfo selectedGame;
	private IProxy proxy;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view
	 *            Join game view
	 * @param newGameView
	 *            New game view
	 * @param selectColorView
	 *            Select color view
	 * @param messageView
	 *            Message view (used to display error messages that occur while
	 *            the user is joining a game)
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
	
	public IJoinGameView getJoinGameView(){
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction(){
		return joinAction;
	}
	
	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value
	 *            The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value){	
		joinAction = value;
	}
	
	public INewGameView getNewGameView(){
		return newGameView;
	}
	
	public void setNewGameView(INewGameView newGameView){
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView(){
		return selectColorView;
	}
	
	public void setSelectColorView(ISelectColorView selectColorView){
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView(){
		return messageView;
	}
	
	public void setMessageView(IMessageView messageView){
		this.messageView = messageView;
	}
	
	@Override
	public void start()
	{
		PlayerInfo self = new PlayerInfo();
		self.setId(this.proxy.getrUser().getPlayerID());
		self.setName(this.proxy.getrUser().getName());
		
//		// This is just for testing purposes
//		PlayerInfo first = new PlayerInfo();
//		first.setId(7);
//		first.setPlayerIndex(1);
//		first.setName("First");
//		first.setColor(CatanColor.RED);
//		
//		PlayerInfo second = new PlayerInfo();
//		second.setId(11);
//		second.setPlayerIndex(2);
//		second.setName("Second");
//		second.setColor(CatanColor.GREEN);
//		
//		PlayerInfo third = new PlayerInfo();
//		third.setId(15);
//		third.setPlayerIndex(3);
//		third.setName("Third");
//		third.setColor(CatanColor.ORANGE);
//		
//		PlayerInfo fourth = new PlayerInfo();
//		fourth.setId(19);
//		fourth.setPlayerIndex(4);
//		fourth.setName("Fourth");
//		fourth.setColor(CatanColor.PURPLE);
//		
//		GameInfo one = new GameInfo();
//		one.setId(1);
//		one.setTitle("Game One");
//		one.addPlayer(first);
//		one.addPlayer(third);
//		
//		GameInfo two = new GameInfo();
//		two.setId(3);
//		two.setTitle("Game Two");
//		two.addPlayer(second);
//		two.addPlayer(third);
//		
//		GameInfo three = new GameInfo();
//		three.setId(5);
//		three.setTitle("Game Three");
//		three.addPlayer(first);
//		three.addPlayer(second);
//		three.addPlayer(self);
//		
//		GameInfo four = new GameInfo();
//		four.setId(7);
//		four.setTitle("Game Four");
//		four.addPlayer(first);
//		four.addPlayer(second);
//		four.addPlayer(third);
//		four.addPlayer(fourth);
//		
//		GameInfo[] games = {one, two, three, four};
		
		//getJoinGameView().setGames(games, self);
		
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
		getJoinGameView().showModal();
	}
	
	@Override
	public void startCreateNewGame()
	{
		getNewGameView().showModal();
	}
	
	@Override
	public void cancelCreateNewGame()
	{
		getNewGameView().closeModal();
	}
	
	@Override
	public void createNewGame()
	{
		CreateGame newGame = new CreateGame(getNewGameView().getRandomlyPlaceHexes(),
											getNewGameView().getRandomlyPlaceNumbers(),
											getNewGameView().getUseRandomPorts(),
											getNewGameView().getTitle());
		
		if(proxy.postGamesCreate(newGame).getResponseCode() == 200){
			getNewGameView().closeModal();
			this.start();
		}
	}
	
	@Override
	public void startJoinGame(GameInfo game)
	{
		selectedGame = game;
		getSelectColorView().showModal();
	}
	
	@Override
	public void cancelJoinGame()
	{
		getJoinGameView().closeModal();
	}
	
	@Override
	public void joinGame(String color)
	{
		//ServerJoinGame join = new ServerJoinGame(0, color);
		ServerJoinGame join = new ServerJoinGame(selectedGame.getId(), color);
		if(proxy.postGamesJoin(join).getJson().equals("Success")){
			// If join succeeded
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			joinAction.execute();
		}	
	}

	@Override
	public void update() {
		
	}
	
}
