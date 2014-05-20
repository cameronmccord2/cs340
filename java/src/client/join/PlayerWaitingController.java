package client.join;

import client.base.Controller;
import client.data.PlayerInfo;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.exceptions.CantFindGameModelException;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, ICatanModelObserver {

	private IProxy proxy;
	
	public PlayerWaitingController(IPlayerWaitingView view, IProxy proxy) {

		super(view);
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		//fetch players from Game model
		PlayerInfo[] players;
		try {
			players = this.proxy.getFacade().getAllPlayerInfos();
			getView().setPlayers(players);
			getView().showModal();
			if(players.length == 4){
				getView().closeModal();
			}
		} catch (CantFindGameModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addAI() {

		// TEMPORARY
		
	}

	@Override
	public void update() {
		PlayerInfo[] players;
		try {
			players = this.proxy.getFacade().getAllPlayerInfos();
			getView().setPlayers(players);
			if(players.length == 4){
				getView().closeModal();
			}
		} catch (CantFindGameModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

