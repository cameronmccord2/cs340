package client.points;

import client.base.Controller;
import client.data.PlayerInfo;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;


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
	        catch (CantFindPlayerException | CantFindGameModelException e) {
	            e.printStackTrace();
	        }
        }
	}
	
	private boolean isLocalPlayer(Integer id) throws CantFindGameModelException, CantFindPlayerException {
        return id == this.proxy.getFacade().getCurrentUser().getPlayerInfo().getId();
    }
	
	private PlayerInfo getPlayerById(Integer id) {
		if(id < 0 || id > 3)
			assert false;

		PlayerInfo[] players = null;
		
		try
		{
			players = this.proxy.getFacade().getAllPlayerInfos();
		}
		catch(CantFindGameModelException e)
		{
			e.printStackTrace();
		}
		
        for(PlayerInfo player : players) {
            if(player.getId() == id)
                return player;
        }

        return null;
	}

    @Override
    public void update() {
       initFromModel();
    }
}

