package client.turntracker;

import client.base.*;
import client.models.ICatanModelObserver;
import client.models.IPlayer;
import client.models.IProxy;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.server.FinishedTurn;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, ICatanModelObserver {

	private IProxy proxy;

	public TurnTrackerController(ITurnTrackerView view, IProxy proxy)
	{
		super(view);
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
	}
	
	@Override
	public ITurnTrackerView getView()
	{
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn()
	{
		try {
			this.proxy.movesFinishTurn(new FinishedTurn("finishTurn", this.proxy.getFacade().getCurrentUserIndex()));
		} catch (CantFindGameModelException e) {

		}
	}

	@Override
	public void update() {
		try {
			IPlayer[] players = this.proxy.getFacade().getPlayers();
			if(players.length > 0){
				for(IPlayer p : players){
					System.out.println("initializing player: " + p.getPlayerInfo().getName() + ", " + p.getPlayerInfo().getColor().toString());
						this.getView().initializePlayer(p.getPlayerInfo().getPlayerIndex(), p.getPlayerInfo().getName(), p.getPlayerInfo().getColor());
									
					this.getView().updatePlayer(p.getPlayerInfo().getPlayerIndex()
							, p.getVictoryPoints()
							, (p.getPlayerInfo().getPlayerIndex() == this.proxy.getFacade().getTurnTracker().getCurrentTurn())
							, (p.getPlayerInfo().getPlayerIndex() == this.proxy.getFacade().getTurnTracker().getLargestArmy())
							, (p.getPlayerInfo().getPlayerIndex() == this.proxy.getFacade().getTurnTracker().getLongestRoad()));
				}
				
				this.getView().setLocalPlayerColor(this.proxy.getFacade().getCurrentUser().getPlayerInfo().getColor());
				if(this.proxy.getFacade().isMyTurn() && this.proxy.getFacade().getCurrentState().equals("Playing"))
					this.getView().updateGameState("Finish Turn", true);
				else
					this.getView().updateGameState("Waiting for other players", false);
			}
			
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			
		}
	}

}
































