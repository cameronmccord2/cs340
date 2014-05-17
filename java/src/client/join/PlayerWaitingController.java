package client.join;

import client.base.*;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
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

}

