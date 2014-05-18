package client.join;

import client.base.Controller;
import client.models.ICatanModelObserver;
import client.models.IProxy;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, ICatanModelObserver {

	private IProxy proxy;
	
	public PlayerWaitingController(IPlayerWaitingView view, IProxy proxy) {

		super(view);
		this.proxy.getFacade().registerAsObserver(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		//fetch players from Game model
		//getView().setPlayers(value);
		getView().showModal();
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}
	
	public void updatePlayers(){
		//getView().setPlayers(value);
		//check if 4 are in the list then close and go to game
		getView().closeModal();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

