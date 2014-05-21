package client.points;

import client.base.*;
import client.data.PlayerInfo;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.server.FinishedTurn;


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
        	
	        try {
	            Integer points = this.proxy.getFacade().getCurrentUser().getVictoryPoints();
	            getPointsView().setPoints( points );
	            
	            Integer winnerId = this.proxy.getGameModel().getWinner();
	            
	            if( winnerId >= 0 ) {
	            	PlayerInfo winner = getPlayerById(winnerId);
	            	
	            	
	            	
	            	getFinishedView().setWinner(winner.getName(), isLocalPlayer(winnerId));
	                getFinishedView().showModal();
	            }	            
	        }
	        catch (CantFindPlayerException e) {
	            e.printStackTrace();
	        } catch (CantFindGameModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private boolean isLocalPlayer(Integer id) throws CantFindGameModelException, CantFindPlayerException {
		if(id == this.proxy.getFacade().getCurrentUser().getPlayerInfo().getId())
			return true;
		return false;
	}
	
	private PlayerInfo getPlayerById(Integer id) {
		if(id < 0 || id > 3)
			assert false;
		
		try {
	    	for(PlayerInfo player : this.proxy.getFacade().getAllPlayerInfos()) {
	    		if(player.getId() == id)
	    			return player;
	    	}
		}
		catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
			
		return null;
	}

    @Override
    public void update() {
       initFromModel();
    }
}

