package client.points;

import client.base.Controller;
import client.data.PlayerInfo;
import client.models.*;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;

import java.util.Collection;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController,
															ICatanModelObserver
{
	private IGameFinishedView finishedView;
	private IProxy proxy;

	/**
	 * PointsController constructor
	 *
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView, IProxy proxy)
	{

		super(view);
		this.proxy = proxy;

		setFinishedView(finishedView);

		initFromModel();
		this.proxy.getFacade().registerAsObserver(this);
	}

	public IPointsView getPointsView()
	{
		return (IPointsView)super.getView();
	}

	public IGameFinishedView getFinishedView()
	{
		return finishedView;
	}


	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel() {

		if(this.proxy.getGameId() != null) {

			IFacade facade = this.proxy.getFacade();

			try {

				getPointsView().setPoints( facade.getCurrentUser().getVictoryPoints() );

				Integer winnerId = facade.getWinner();

				if( winnerId >= 0 ) {
					PlayerInfo winner = getPlayerById(winnerId);
					getFinishedView().setWinner(winner.getName(), isLocalPlayer(winnerId));
					getFinishedView().showModal();
				}

			} catch (CantFindGameModelException | CantFindPlayerException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	private int calculateSettlementPoints() {

		int points = 0;
		IGame game = this.proxy.getGameModel();
		Collection<ISettlement> settlements = game.getMap().getSettlements();

		try {
			for( ISettlement settlement: settlements) {
				if(settlement.getPlayer() == this.proxy.getFacade().getCurrentUser())
					points += settlement.getPointValue();
			}
		} catch (CantFindGameModelException e) {
		   e.printStackTrace();
		} catch (CantFindPlayerException e) {
		  e.printStackTrace();
		}

		return points;
	}
	*/


	private boolean isLocalPlayer(Integer id) throws CantFindGameModelException, CantFindPlayerException {
		return id == this.proxy.getFacade().getCurrentUser().getPlayerInfo().getId();
	}



	private PlayerInfo getPlayerById(Integer id) {

		try
		{
			PlayerInfo[] players = this.proxy.getFacade().getAllPlayerInfos();
			for(PlayerInfo player : players) {
				if(player.getId() == id)
					return player;
			}
		}
		catch(CantFindGameModelException e)
		{
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public void update() {
	   initFromModel();
	}
}

