package client.turntracker;

import shared.definitions.CatanColor;
import client.base.*;
import client.data.PlayerInfo;
import client.models.ICatanModelObserver;
import client.models.IPlayer;
import client.models.IProxy;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, ICatanModelObserver {

	private IProxy proxy;
	private boolean hasLoadedPlayers;

	public TurnTrackerController(ITurnTrackerView view, IProxy proxy)
	{
		super(view);
		this.proxy = proxy;
	}
	
	@Override
	public ITurnTrackerView getView()
	{
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn()
	{
		
	}

	@Override
	public void update() {
		try {
			IPlayer[] players = this.proxy.getFacade().getPlayers();
			if(players.length > 0){
				this.getView().updateGameState(this.proxy.getFacade().getCurrentState(), true);
					for(IPlayer p : players){
						if(!this.hasLoadedPlayers)
							this.getView().initializePlayer(p.getPlayerInfo().getPlayerIndex(), p.getPlayerInfo().getName(), p.getPlayerInfo().getColor());
										
						this.getView().updatePlayer(p.getPlayerInfo().getPlayerIndex()
								, p.getVictoryPoints()
								, (p.getPlayerInfo().getPlayerIndex() == this.proxy.getFacade().getTurnTracker().getCurrentTurn())
								, (p.getPlayerInfo().getPlayerIndex() == this.proxy.getFacade().getTurnTracker().getLargestArmy())
								, (p.getPlayerInfo().getPlayerIndex() == this.proxy.getFacade().getTurnTracker().getLongestRoad()));
					}
					if(!this.hasLoadedPlayers)
						this.hasLoadedPlayers = true;
					
					this.getView().setLocalPlayerColor(this.proxy.getFacade().getCurrentUser().getPlayerInfo().getColor());
			}
			
		} catch (CantFindGameModelException | CantFindPlayerException e) {
		}
	}

}
































