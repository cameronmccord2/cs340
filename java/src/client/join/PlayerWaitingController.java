package client.join;

import client.base.Controller;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.interfaces.ICatanModelObserver;
import client.models.interfaces.IProxy;


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
//		if(!gameStarted)
//		{
			//fetch players from Game model
			PlayerInfo[] players;
			try {
				if(this.proxy.getFacade().getAllPlayerInfos() != null){
					players = this.proxy.getFacade().getAllPlayerInfos();
					getView().setPlayers(players);
					getView().showModal();
					if(players.length == 4){
						if(this.getView().isModalShowing())
							getView().closeModal();
					}
				}
				else{
					if(!this.getView().isModalShowing())
						getView().showModal();
				}

			} catch (CantFindGameModelException e) {
				e.printStackTrace();
			}
//		}
	}

	@Override
	public void addAI() {

	}

	@Override
	public void update() {
//		if(!gameStarted)
//		{
			PlayerInfo[] players;
			try {
				if(this.proxy.getFacade().getAllPlayerInfos() != null){
					players = this.proxy.getFacade().getAllPlayerInfos();
					getView().setPlayers(players);
					//if(!getView().isModalShowing())
						getView().showModal();
					if(players.length == 4){
						if(this.getView().isModalShowing())
							getView().closeModal();
					}
				}

			} catch (CantFindGameModelException e) {
				e.printStackTrace();
			}
//		}
	}

}

