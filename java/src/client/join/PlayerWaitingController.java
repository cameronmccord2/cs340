package client.join;

import client.base.Controller;
import client.data.PlayerInfo;
import client.models.ICatanModelObserver;
import client.models.IFacade;
import client.models.IProxy;
import client.models.exceptions.CantFindGameModelException;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, ICatanModelObserver {

	private IProxy proxy;
	private boolean gameStarted;
	
	public PlayerWaitingController(IPlayerWaitingView view, IProxy proxy) {

		super(view);
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
		gameStarted = false;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
//		if(!gameStarted)
//		{
    		//fetch players from Game model
    		PlayerInfo[] players;
    		try {
    			IFacade facade = this.proxy.getFacade();
    			players = facade.getAllPlayerInfos();
    			getView().setPlayers(players);
    			getView().showModal();
    			if(players.length == 4){
    				gameStarted = true;
    				if(this.getView().isModalShowing())
    					getView().closeModal();
//    				proxy.getGameModel().startGame();
    			}
    		} catch (CantFindGameModelException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//		}
	}

	@Override
	public void addAI() {

		// TEMPORARY
		
	}

	@Override
	public void update() {
//		if(!gameStarted)
//		{
    		PlayerInfo[] players;
    		try {
    			players = this.proxy.getFacade().getAllPlayerInfos();
    			getView().setPlayers(players);
//    			System.out.println(1);
    			if(players.length == 4){
    				gameStarted = true;
    				if(this.getView().isModalShowing())
    					getView().closeModal();
//    				proxy.getGameModel().startGame();
    			}
    		} catch (CantFindGameModelException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//		}
	}

}

